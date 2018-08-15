package com.baidu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.dao.ArticleDao;
import com.baidu.entity.Article;

public class ControllerHome extends HttpServlet {

	private String index;
	private String id;

	/**
	 * Constructor of the object.
	 */
	public ControllerHome() {
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

		request.setCharacterEncoding("utf-8");

		if (request.getParameter("index") == null
				&& request.getParameter("id") == null
				&& request.getParameter("action") == null) {
			ArticleDao ad = new ArticleDao();
			ArrayList<Article> arts = null;
			arts = ad.selectFiveArticles("0");
			if (arts.size() > 0 && arts != null) {
				int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
				request.setAttribute("middle", middle);
				request.setAttribute("arts", arts);
				request.getRequestDispatcher("/homepage.jsp?page=1").forward(
						request, response);
			}
		}

		if (request.getParameter("index") != null) {
			this.index = request.getParameter("index");
			ArticleDao ad = new ArticleDao();
			ArrayList<Article> arts = null;
			System.out.println(index);
			arts = ad.selectFiveArticles(((Integer.parseInt(index) - 1) * 5)
					+ "");
			if (arts.size() > 0 && arts != null) {
				int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
				request.setAttribute("middle", middle);
				request.setAttribute("arts", arts);
				request.getRequestDispatcher(
						"/homepage.jsp?page=" + (Integer.parseInt(index)))
						.forward(request, response);
			}
		}
		if (request.getParameter("id") != null) {
			System.out.println("点击的记录的ID为:" + request.getParameter("id"));
			this.id = request.getParameter("id");
			ArticleDao ad = new ArticleDao();
			Article a = ad.selectArticle(Integer.parseInt(id));
			Integer aLast = null;
			Integer aNext = null;

			ArrayList<Integer> artsId = ad.selectAllArticlesOrderByUpdateTime();
			for (int i = 0; i < artsId.size(); i++) {
				if (artsId.get(i) == Integer.parseInt(id)) {
					if (i <= 0) {
						aLast = artsId.get(i);
					} else {
						aLast = artsId.get(i - 1);
						System.out.println("aLast:" + aLast);
					}
					if (i >= artsId.size() - 1) {
						aNext = artsId.get(i);
					} else {
						aNext = artsId.get(i + 1);
						System.out.println("aNext:" + aNext);
					}
				}
			}

			if (a != null) {
				System.out.println("a不为空::::" + a.toString());

				String aLastTitle = ad.selectArticle(aLast).getTitle();
				String aNextTitle = ad.selectArticle(aNext).getTitle();
				request.setAttribute("a", a);
				request.setAttribute("aLast", aLast);
				request.setAttribute("aNext", aNext);
				request.setAttribute("aLastTitle", aLastTitle);
				request.setAttribute("aNextTitle", aNextTitle);
			}
			request.getRequestDispatcher("/detailedpage.jsp?").forward(request,
					response);
		}
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equals("back")) {
				ArticleDao ad = new ArticleDao();
				ArrayList<Article> arts = null;
				arts = ad.selectFiveArticles("0");
				if (arts.size() > 0 && arts != null) {
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.setAttribute("arts", arts);
					request.getRequestDispatcher("/homepage.jsp?page=1")
							.forward(request, response);
				}
			}
		}
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

		doGet(request, response);
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
