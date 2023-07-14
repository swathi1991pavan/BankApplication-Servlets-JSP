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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(DepositServlet.class);
    
	
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	   response.setHeader("Pragma", "no-cache");
	   response.setDateHeader("Expires", 0);
	   response.setHeader("Pragma","no-cache");
		log.info("started doPost method");
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
				
		log.info("Getting the values from Deposit Form");
		int accNo = Integer.parseInt(request.getParameter("accNo"));
		System.out.println("accNo");
		int amount = Integer.parseInt(request.getParameter("amount"));
		System.out.println("amount");
		log.info("values taken from the Deposit Form");
		
		log.info("Executing Depoist method");
		int status = CustomerDao.deposit(accNo,amount);
		
        if(status>0){ 
              	
        	log.info("Deposit Successful ");
            out.println("<h1><center>Deposited Successfully</center></h1>");
        	        	
        }
        else {
        	
        	log.info("Deposit UnSuccessful ");
        	out.println("<h1><center>Deposit UnSuccessful</center></h1>");
        	
        	
        }
		
	}

}
