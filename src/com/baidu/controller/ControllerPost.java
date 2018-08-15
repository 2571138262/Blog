package com.baidu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.baidu.dao.ArticleDao;
import com.baidu.dao.UserDao;
import com.baidu.entity.Article;

public class ControllerPost extends HttpServlet {

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB;

	/**
	 * Constructor of the object.
	 */
	public ControllerPost() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// --------------------
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		Integer user = (Integer) request.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("=========================");
			response.sendRedirect("/blog/login.jsp");
		} else {
			UserDao ud = new UserDao();
			request.setAttribute("username", ud.queryUsername(user));
			HashMap<String, String> map = null;

			try {
				map = getPara(request, response);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArticleDao ad = new ArticleDao();
			Article a = new Article();

			String id = null;
			String title = null, author = null, content = null, type = null, poster = null;
			boolean flag = false;

			String index = null;

			id = request.getParameter("id");
			// index = request.getParameter("index");

			if (map != null && map.size() > 0) {
				title = map.get("title");
				author = map.get("author");
				type = map.get("type");
				flag = map.get("ishow").equals("true") ? true : false;
				content = map.get("content");
				poster = map.get("poster");
				System.out.println("图片的地址为" + poster);
			}

			if (id != null) {// 修改
				a.setId(Integer.parseInt(id));
				a.setTitle(title);
				a.setAuthor(author);
				a.setContent(content);
				a.setPoster(poster);
				a.setType(type);
				a.setUpdate_time(new Date(System.currentTimeMillis()));
				a.setFlag(flag);

				if (ad.updateArticle(a)) {
					ArrayList<Article> arts = ad.selectFiveArticles("0");
					if (arts.size() > 0 && arts != null) {
						request.setAttribute("search", null);
						request.setAttribute("arts", arts);
						int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
						request.setAttribute("middle", middle);
						request.getRequestDispatcher("/index.jsp?index=1")
								.forward(request, response);
					} else {
						request.getRequestDispatcher("/login.jsp").forward(
								request, response);
					}
				}
			}
			if (id == null) {// 新增
				a.setId(0);
				a.setTitle(title);
				a.setAuthor(author);
				a.setContent(content);
				a.setPoster(poster);
				a.setType(type);
				a.setCreate_time(new Date(System.currentTimeMillis()));
				a.setUpdate_time(new Date(System.currentTimeMillis()));
				a.setFlag(flag);

				if (ad.addArticle(a)) {

					ArrayList<Article> arts = ad.selectFiveArticles("0");
					request.setAttribute("search", null);
					request.setAttribute("arts", arts);
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.getRequestDispatcher("/index.jsp?index=1").forward(
							request, response);
				}
			}
		}

	}

	public HashMap<String, String> getPara(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			FileUploadException {

		response.setContentType("text/html;charset=UTF-8");
		String errorMessage = null;
		HashMap<String, String> map = new HashMap<String, String>();
		DiskFileItemFactory fac = null;
		ServletFileUpload upload = null;
		try {
			fac = new DiskFileItemFactory();
			upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("utf-8");
			List<FileItem> fileList = null;
			fileList = upload.parseRequest(request);

			if (fileList != null && fileList.size() > 0) {
				for (FileItem f : fileList) {
					if (f.isFormField()) {// 是普通域
						String name = f.getFieldName();
						String value = f.getString("utf-8");
						if (name != null) {
							System.out.println(name + ":" + value);
							map.put(name, value);
						}
					}
				}
			}

			String savePath = getServletContext().getRealPath("/upload");
			System.out.println("save at:" + savePath);
			File f1 = new File(savePath);
			if (!f1.exists()) {
				f1.mkdirs();
			}

			Iterator<FileItem> it = fileList.iterator();
			String name = "";
			String poster = "/upload/";
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					name = item.getName();
					File file = new File(savePath + File.separatorChar + name);
					if (file.exists()) {
						errorMessage = "该文件已经存在！请重命名后再上传。";
					}
					if (name.equals("")) {
						System.out.println("上床的文件为空");
						name = "blog.png";
						poster = "/upload/blog.png";
					} else {
						System.out.println("上传的文件不为空");
						poster = poster + name;
					}
					map.put("poster", poster);
					item.write(file);
					item.delete(); // 释放输出流
				}
			}
			// if (errorMessage != null){
			// request.setAttribute("id", request.getParameter("id"));
			// request.setAttribute("index", request.getParameter("index"));
			// request.setAttribute("errorMessage", errorMessage);
			// request.getRequestDispatcher("/post.jsp").forward(request,
			// response);
			// }
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {

		}
	}

	public String upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			PrintWriter writer = response.getWriter();
			writer.println("Error: 表单必须包含 enctype=multipart/form-data");
			writer.flush();
			return null;
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值(包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("utf-8");

		// 构造临时路径来存储上传文件
		// 这个路径相对当前应用的目录
		String uploadPath = getServletContext().getRealPath("/")
				+ File.separator + UPLOAD_DIRECTORY;

		// 如果目录不存在则创建目录
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			String poster = "/upload/";

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						// posterUrl

						if (fileName.equals("")) {
							System.out.println("上床的文件为空");
							fileName = "blog.png";
							poster = "/upload/blog.png";
						} else {
							System.out.println("上传的文件不为空");
							poster = poster + fileName;
						}
						String filePath = uploadPath + File.separator
								+ fileName;
						File storeFile = new File(filePath);
						// 在控制台上输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("message", "图片上传成功了");

						// request.setAttribute("poster", poster);
						// 跳转到post.jsp
						// request.getRequestDispatcher("/post.jsp").forward(
						// request, response);
						// getServletContext().getRequestDispatcher("/post.jsp")
						// .forward(request, response);
					}
				}
			}
			return poster;
		} catch (Exception e) {
			request.setAttribute("message", "错误信息: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
