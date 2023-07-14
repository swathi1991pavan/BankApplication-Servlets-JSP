package com.cglia.bankapp.servlet;

import java.io.IOException;
import com.cglia.bankapp.dao.CustomerDao;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class CheckBalanceServlet
 */
@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CheckBalanceServlet.class);   
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		   response.setHeader("Pragma", "no-cache");
		   response.setDateHeader("Expires", 0);
		   response.setHeader("Pragma","no-cache");
		Layout layout = new SimpleLayout();
		Appender app = new ConsoleAppender(layout);
		log.addAppender(app);
		
		response.setContentType("text/html");  
        
        int userid=0;
        
        log.info("Instance Session Requested ");
        HttpSession httpSession = request.getSession(false);
        
    	if(httpSession!= null) {
    		log.info("Getting the userid from session");
    		userid=(int)httpSession.getAttribute("userid");
    		
       
    	
    	 log.info("Calling checkBalance method");
    	 float balance = CustomerDao.checkBalance(userid);
    	 log.info("Balance Fetched");
    	 
    	 //setting balance attribute in session
    	 httpSession.setAttribute("AccBalance",balance);
    	 
    	 log.info("Passing control to CheckBalance.jsp");
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("CheckBalance.jsp");
         dispatcher.forward(request, response);
    	}else {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.html");
            dispatcher.forward(request, response);
    	}
    	 
	}

}
