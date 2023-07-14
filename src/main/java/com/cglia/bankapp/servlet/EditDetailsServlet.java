package com.cglia.bankapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cglia.bankapp.bean.Customer;
import com.cglia.bankapp.dao.CustomerDao;

/**
 * Servlet implementation class EditDetailsServlet
 */
@WebServlet("/EditDetailsServlet")
public class EditDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(EditDetailsServlet.class);   
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		   response.setHeader("Pragma", "no-cache");
		   response.setDateHeader("Expires", 0);
		   response.setHeader("Pragma","no-cache");
		
			log.info("entered the post method in EditDetailsServlet");
			//PrintWriter out=response.getWriter();  
			response.setContentType("text/html");  
			        
	        
	        log.info("Getting the values from AccountDetails to update");
	        int accNo = Integer.parseInt(request.getParameter("accNo"));
	        System.out.println(accNo);
	        String name= request.getParameter("user"); 
	        String city = request.getParameter("city");
	        String state = request.getParameter("state");
	     	int	mobile = Integer.parseInt(request.getParameter("mobile"));   
	        String password=request.getParameter("password");  
	        String email=request.getParameter("email"); 
	        System.out.println(email);
	        String country=request.getParameter("country"); 
	        log.info("values taken from the Register Form");
	        
	        log.info("Creating a new customer");
	        Customer customer = new Customer();
	        customer.setAccId(accNo);
	        System.out.println(customer.getAccId());
	        customer.setCustName(name);
	        customer.setCity(city);
	        customer.setState(state);
	        customer.setMobile(mobile);
	        customer.setPassword(password);
	        customer.setEmail(email);
	        System.out.println(customer.getEmail());
	        customer.setCountry(country);
	        log.info("values got assigned to the customer");
	        
	        Customer updCustomer=CustomerDao.updateDetails(customer);
	        HttpSession httpSession = request.getSession(false); 
	        
	        log.info("Setting values to httpSession");
	        httpSession.setAttribute("AccID", updCustomer.getAccId());
	        httpSession.setAttribute("UserName", updCustomer.getCustName());
	        httpSession.setAttribute("City", updCustomer.getCity());
	        httpSession.setAttribute("State", updCustomer.getState());
	        httpSession.setAttribute("Mobile", updCustomer.getMobile());
	        httpSession.setAttribute("Password", updCustomer.getPassword());
	        httpSession.setAttribute("Email", updCustomer.getEmail());
	        httpSession.setAttribute("Country", updCustomer.getCountry());
	        log.info("Values assigned Successfully");
	        
	        log.info("Passig control to AccountDetails.jsp");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
	        dispatcher.forward(request, response);
	}

}
