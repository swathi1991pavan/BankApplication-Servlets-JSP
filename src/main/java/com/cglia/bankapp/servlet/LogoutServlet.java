package com.cglia.bankapp.servlet;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(LogoutServlet.class);
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Layout layout = new SimpleLayout();
			Appender app = new ConsoleAppender(layout);
			log.addAppender(app);
			
			log.info("Http Session Requested");
	     	HttpSession httpSession = request.getSession(false);
	        httpSession.invalidate();
	        log.info("Http Session closed");
	        log.info("Passing control to Home.html");
	        System.out.println("hi");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.html");
	    	dispatcher.forward(request, response);
	        
	        
	}

	

}
