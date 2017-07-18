package com.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login dopost");
		String userName= req.getParameter("userName");
		String password=req.getParameter("password");
		
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
		
		try {
			subject.login(token);
			resp.sendRedirect("success.jsp");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			req.setAttribute("errorInfo","userName or password error");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			System.out.println("login fail");
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("login doget");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

}
