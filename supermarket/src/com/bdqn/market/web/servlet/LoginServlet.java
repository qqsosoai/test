package com.bdqn.market.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.market.bean.User;
import com.bdqn.market.service.user.UserService;
import com.bdqn.market.service.user.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		switch (method) {
		case "login":
			login(request, response);
			break;
		case "out":
			out(request, response);
			break;
		}
	}
	/**
	 * 登录方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		UserService service=new UserServiceImpl();
		User user=service.login(name);
		if (user!=null) {
			if (password.equals(user.getUserPassword())) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("welcome.jsp");
			}else {
				response.sendRedirect("Login.jsp?flag=password");
			}
		}else {
			response.sendRedirect("Login.jsp?flag=false");
		}
	}
	/**
	 * 注销方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void out(HttpServletRequest request, HttpServletResponse response)throws IOException{
		request.getSession().invalidate();
		response.sendRedirect("Login.jsp");
	}
	
}
