package com.baidu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.dao.UserDao;
import com.baidu.entity.Users;

public class DoRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoRegister() {
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
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null
				&& request.getParameter("checkpass") != null
				&& !request.getParameter("username").equals("")
				&& !request.getParameter("password").equals("")
				&& !request.getParameter("checkpass").equals("")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String checkpass = request.getParameter("checkpass");
			if (password.equals(checkpass)) {
				Users u = new Users();
				u.setUsername(username);
				u.setPassword(password);
				UserDao ud = new UserDao();
				if (ud.addNewUser(u)) {
					response.sendRedirect("/blog/login.jsp");
				} else {
					request.setAttribute("message1", "ע��ʧ��");
					request.getRequestDispatcher("/register.jsp").forward(
							request, response);
				}
			} else {
				request.setAttribute("message1", "�������벻һ��");
				request.getRequestDispatcher("/register.jsp").forward(
						request, response);
			}
		} else {
			request.setAttribute("message1", "ѡ���Ϊ��");
			request.getRequestDispatcher("/register.jsp").forward(request,
					response);
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
