package com.cglia.bankapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.cglia.bankapp.bean.Customer;
import com.cglia.bankapp.bean.Account;
import com.cglia.bankapp.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(RegisterServlet.class);
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("entered the post method in RegisterServlet");
		PrintWriter out=response.getWriter();  
		response.setContentType("text/html");  
        
        int accId=0;
        int mobile=0;
        log.info("Getting the values from Register Form");
        String num = request.getParameter("accNo");
        if(num!=null) {
        	accId = Integer.parseInt(request.getParameter("accNo"));
        }
        System.out.println(num);
        System.out.println(accId);
        String name= request.getParameter("user"); 
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String mob = request.getParameter("mobile");
        if(mob!=null) {
        	mobile = Integer.parseInt(request.getParameter("mobile"));
        }
        
        String password=request.getParameter("password");  
        String email=request.getParameter("email");  
        String country=request.getParameter("country"); 
        log.info("values taken from the Register Form");
        
        log.info("Creating a new customer");
        Customer customer = new Customer();
        customer.setAccId(accId);
        customer.setCustName(name);
        customer.setCity(city);
        customer.setState(state);
        customer.setMobile(mobile);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setCountry(country);
        log.info("values got assigned to the customer");
       
        int status=CustomerDao.registerSave(customer);
        System.out.println(status);
        if(status>0){ 
        	
        	log.info("Query executed successfully with status" +status);
        	log.info("Creating a new account for customer ");
        	Account account = new Account();
        	account.setAccId(accId);
        	account.setAccStatus("Active");
        	account.setBalance(0.0f);
            log.info("values got assigned to the customer account");
            
            log.info("Creating login information for user");
            int loginStatus = CustomerDao.loginSave(account);
            if(loginStatus>0) {
            	log.info("Login record was saved successfully");
            	
            }
            log.info("Control goes to RegisterSuccess.html"); 
            request.getRequestDispatcher("RegisterSuccess.html").include(request, response);  
        }else{ 
        	log.error("Query unsuccessfully");
        	request.getRequestDispatcher("RegisterFail.html").include(request, response); 
        }  
          
        out.close();  
    }  
   
        
}	


