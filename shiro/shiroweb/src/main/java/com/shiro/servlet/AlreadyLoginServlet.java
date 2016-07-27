package com.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet(name = "alreadyLoginServlet", urlPatterns = "/alreadyLogin")
public class AlreadyLoginServlet extends HttpServlet{

	private static final long serialVersionUID = -8812465187959473155L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Subject subject=SecurityUtils.getSubject();
		req.setAttribute("subject",subject);
		req.getRequestDispatcher("/WEB-INF/jsp/alreadyLogin.jsp").forward(req, resp);
	}
	
	
}
