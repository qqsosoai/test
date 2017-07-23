package com.bdqn.market.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bdqn.market.bean.User;

public class SessionFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String uri=req.getRequestURI();
		User user=(User) req.getSession().getAttribute("user");
		if (uri.contains("Login")|| user!=null || (uri.contains(".js")&&!uri.contains(".jsp")) || uri.contains(".css") || uri.contains("img")) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect("Login.jsp?flag=session");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
