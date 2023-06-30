package com.cglia.bankapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.cglia.bankapp.dao.CustomerDao;
import com.cglia.bankapp.bean.Transcation;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ViewTranscationServlet
 */
@WebServlet("/ViewTranscationServlet")
public class ViewTranscationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ViewTranscationServlet.class);
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("started doPost method");
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        int userid=0;
        
        log.info("Calling Http Session instance");
        HttpSession httpSession = request.getSession(false);
    	if(httpSession!= null) {
    		log.info("Getting the userid attribute");
    		userid=(int)httpSession.getAttribute("userid");
    		    		
    	}
    	
    	log.info("Executing the transfer function");
    	List<Transcation> transcations= CustomerDao.transcation(userid);
    	
    	out.print("<table border='1' width='60%'");
    	
    	log.info("Displaying the records");
    	out.println("<tr>\r\n"
    			
    			+ "    	<th>Transcation Type</th>\r\n"
    			+ "    	<th>Transcation Status</th>\r\n"
    			+ "    	<th>Amount</th>\r\n"
    			+ "    	<th>Final Balance</th>  \r\n"
    			+ "    </tr>");
    	for(Transcation t:transcations) {
    		out.println("<tr><td>"+t.getTransType()+"</td>"
    						+ "<td>"+t.getTransStatus()+"</td>"
    								+ "<td>"+t.getAmount()+"</td>"
    										+ "<td>"+t.getBalance()+"</td></tr>");
    	}
    	
	}

	

}
