package com.cglia.bankapp.servlet;

import java.io.IOException;
import com.cglia.bankapp.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */  
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
 	   response.setHeader("Pragma", "no-cache");
 	   response.setDateHeader("Expires", 0);
 	   response.setHeader("Pragma","no-cache");
   		Layout layout = new SimpleLayout();
		Appender app = new ConsoleAppender(layout);
		log.addAppender(app);
		
   		
   		log.info("started doPost method in LoginServlet");
		response.setContentType("text/html");
		
		log.info("Getting the values from Login Form");
		int accNo = Integer.parseInt(request.getParameter("accNo"));
		String pword = request.getParameter("password");
		log.info("values taken from the Login Form");
		
		log.info("validating login");
		String userName = CustomerDao.loginValidate(accNo,pword);
        if(userName!=null){ 
        	        	
        	log.info("Login Successful ");
        	
        	HttpSession httpSession = request.getSession();
        	httpSession.setAttribute("userid", accNo);
        	httpSession.setAttribute("userName", userName);
        	
        	log.info("Control to Welcome.html");
        	request.getRequestDispatcher("Welcome.jsp").include(request, response); 
        }
        else {
        	log.info("Login UnSuccessful ");
        	log.info("Control to LoginFail.html");
        	request.getRequestDispatcher("LoginFail.html").include(request, response); 
        	
        }
	}

}
