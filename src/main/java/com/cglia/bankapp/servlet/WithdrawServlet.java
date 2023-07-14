package com.cglia.bankapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.cglia.bankapp.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(WithdrawServlet.class);
       
   	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		   response.setHeader("Pragma", "no-cache");
		   response.setDateHeader("Expires", 0);
		   response.setHeader("Pragma","no-cache");
		log.info("started doPost method");
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
		
		log.info("Getting the values from Withdraw Form");
		int accNo = Integer.parseInt(request.getParameter("accNo"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		log.info("values taken from the Withdraw Form");
		
		log.info("Executing withdraw method");
		int status = CustomerDao.withdraw(accNo,amount);
        if(status>0){ 
            	
        	log.info("Withdraw Successful ");
            out.println("<h1><center>Withdrawal Successful</center></h1>");
        	
        }
        else {
        	
        	log.info("Withdrawal UnSuccessful ");
        	out.println("<h1><center>Withdrawal UnSuccessful</center></h1>");
        	        	
        }
		
	}

}
