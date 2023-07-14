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
 * Servlet implementation class TransferServlet
 */
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TransferServlet.class);   
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
		
		log.info("Getting the values from Transfer Form");
		int fromAccNo = Integer.parseInt(request.getParameter("ownerAccNo"));
		int toAccNo = Integer.parseInt(request.getParameter("transferAccNo"));
		int amount = Integer.parseInt(request.getParameter("transferAmount"));
		log.info("values taken from the Transfer Form");
		
		log.info("Executing the transfer method");
		int status = CustomerDao.transfer(fromAccNo,toAccNo,amount);
        
		if(status>0){ 
            	
        	log.info("Transfer Successful");
            out.println("<h1><center>Transfer Successful</center></h1>");
        
        }
        else {
        	
        	log.info("Transfer UnSuccessful ");
        	out.println("<h1><center>Transfer UnSuccessful</center></h1>");
        	
        }
	}

}
