package com.baidu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.dao.ArticleDao;
import com.baidu.dao.UserDao;
import com.baidu.encryption.EncrypMD5;
import com.baidu.entity.Article;

public class DoLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoLogin() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = null;
		String password = null;
		if(request.getParameter("username")!=null&&request.getParameter("password")!=null){
			username = request.getParameter("username");
			try {//加密
				password = new String(EncrypMD5.eccrypt(request.getParameter("password")));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			UserDao ud = new UserDao();
			String temp = ud.queryPassword(username);
			if(temp!=null){
				if(password.equals(temp)){
					ArticleDao ad = new ArticleDao();
					ArrayList<Article> arts = ad.selectFiveArticles("0");
					request.setAttribute("search", null);
					request.setAttribute("arts", arts);
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.setAttribute("username", username);
					request.getSession().setAttribute("user", ud.queryID(username));
					request.getRequestDispatcher("/index.jsp?index=1").forward(request, response);
				}else{
					request.setAttribute("message", "密码错误");
					request.getRequestDispatcher("/login_failure.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("message", "用户不存在");
				request.getRequestDispatcher("/login_failure.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "选项不能为空");
			request.getRequestDispatcher("/login_failure.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
