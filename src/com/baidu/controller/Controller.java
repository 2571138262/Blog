package com.baidu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.dao.ArticleDao;
import com.baidu.dao.UserDao;
import com.baidu.entity.Article;

public class Controller extends HttpServlet {

	private String action; // 锟斤拷示锟斤拷锟斤拷亩锟斤拷锟�
	private String index;// 页数

	/**
	 * Constructor of the object.
	 */
	public Controller() {
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

		request.setCharacterEncoding("utf-8");
		Integer user = (Integer) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/blog/login.jsp");
		} else {
			UserDao ud = new UserDao();
			request.setAttribute("username", ud.queryUsername(user));
			if (request.getParameter("search") != null) {
				this.action = request.getParameter("search");
				if (action.equals("")) {
					ArticleDao ad = new ArticleDao();
					ArrayList<Article> arts = ad.selectFiveArticles("0");
					request.setAttribute("search", null);
					request.setAttribute("arts", arts);
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.getRequestDispatcher("/index.jsp?index=1").forward(
							request, response);
				} else {
					ArticleDao ad = new ArticleDao();
					ArrayList<Article> arts = ad.query(action);
					request.setAttribute("search", action);
					request.setAttribute("arts", arts);
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.getRequestDispatcher("/index.jsp?index=1").forward(
							request, response);
				}
			} else {
				System.out.println("空");
			}
			if (request.getParameter("action") != null) {
				this.action = request.getParameter("action");
				if (action.equals("creNew")) {
					request.getRequestDispatcher("/post.jsp").forward(request,
							response);
				}
				if (action.equals("delete")) {
					ArticleDao ad = new ArticleDao();
					ad.deleteArticle(Integer.parseInt(request
							.getParameter("id")));
					String index = request.getParameter("index");
					ArrayList<Article> arts = ad.selectFiveArticles((Integer
							.parseInt(index) - 1) * 5 + "");
					if (arts.size() > 0 && arts != null) {
						request.setAttribute("search", null);
						request.setAttribute("arts", arts);
						int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
						request.setAttribute("middle", middle);
						request.getRequestDispatcher(
								"/index.jsp?index=" + (Integer.parseInt(index)))
								.forward(request, response);
					} else {
						arts = ad
								.selectFiveArticles((Integer.parseInt(index) - 2)
										* 5 + "");
						if (arts.size() > 0 && arts != null) {
							request.setAttribute("search", null);
							request.setAttribute("arts", arts);
							int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
							request.setAttribute("middle", middle);
							request.getRequestDispatcher(
									"/index.jsp?index="
											+ (Integer.parseInt(index) - 1))
									.forward(request, response);
						} else {
							request.setAttribute("search", null);
							request.setAttribute("arts", null);
							int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
							request.setAttribute("middle", middle);
							request.getRequestDispatcher("/index.jsp?index=1")
									.forward(request, response);
						}
					}
				}
				if (action.equals("update")) {
					ArticleDao ad = new ArticleDao();
					Article a = ad.selectArticle(Integer.parseInt(request
							.getParameter("id")));
					request.setAttribute("a", a);
					// request.setAttribute("index",
					// request.getParameter("index"));
					request.getRequestDispatcher("/post.jsp").forward(request,
							response);
				}

				if (action.equals("return")) {
					ArticleDao ad = new ArticleDao();
					ArrayList<Article> arts = ad.selectFiveArticles("0");
					request.setAttribute("search", null);
					request.setAttribute("arts", arts);
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.getRequestDispatcher("/index.jsp?index=1").forward(
							request, response);
				}
			}
			if (request.getParameter("index") != null) {
				this.index = request.getParameter("index");
				ArticleDao ad = new ArticleDao();
				ArrayList<Article> arts = null;
				arts = ad
						.selectFiveArticles(((Integer.parseInt(index) - 1) * 5)
								+ "");
				if (arts.size() > 0 && arts != null) {
					request.setAttribute("search", null);
					request.setAttribute("arts", arts);
					int middle = (ad.selectAllArticles().size() - 1) / 5 + 1;
					request.setAttribute("middle", middle);
					request.getRequestDispatcher(
							"/index.jsp?index=" + (Integer.parseInt(index)))
							.forward(request, response);
				}
			}
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
