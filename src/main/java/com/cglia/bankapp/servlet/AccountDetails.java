package com.cglia.bankapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.cglia.bankapp.bean.Customer;
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
 * Servlet implementation class AccountDetails
 */
@WebServlet("/AccountDetails")
public class AccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AccountDetails.class); 
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Layout layout = new SimpleLayout();
		Appender app = new ConsoleAppender(layout);
		log.addAppender(app);
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        int userid=0;
        log.info("Http Session Requested");
        HttpSession httpSession = request.getSession(false);
    	if(httpSession!= null) {
    		log.info("Setting userid  from httpSession");
    		userid=(int)httpSession.getAttribute("userid");
    		out.println("Profile Details");
    		
    	}
    	
    	log.info("Executing getCustById method");
        Customer c = CustomerDao.getCustById(userid);
        
        log.info("Setting values to httpSession");
        httpSession.setAttribute("AccID", c.getAccId());
        httpSession.setAttribute("UserName", c.getCustName());
        httpSession.setAttribute("City", c.getCity());
        httpSession.setAttribute("State", c.getState());
        httpSession.setAttribute("Mobile", c.getMobile());
        httpSession.setAttribute("Password", c.getPassword());
        httpSession.setAttribute("Email", c.getEmail());
        httpSession.setAttribute("Country", c.getCountry());
        log.info("Values assigned Successfully");
        
        log.info("Passig control to AccountDetails.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
        dispatcher.forward(request, response);
            
    }
}
