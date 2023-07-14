package com.cglia.bankapp.servlet;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class LogoutServlet extends HttpServlet {  
       
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException { 
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			   response.setHeader("Pragma", "no-cache");
			   response.setDateHeader("Expires", 0);
			   response.setHeader("Pragma","no-cache");
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            System.out.println("coming to login page") ; 
            request.getRequestDispatcher("Login.html").include(request, response);  
              
            HttpSession session=request.getSession();  
            session.invalidate();  
            System.out.println("session invalidated") ;  
            out.print("You are successfully logged out!");  
              
            out.close();  
    }  
}  




/*import java.io.IOException;
import java.io.PrintWriter;

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
/*@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(LogoutServlet.class);
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();  
			Layout layout = new SimpleLayout();
			Appender app = new ConsoleAppender(layout);
			log.addAppender(app);
			
			log.info("Http Session Requested");
	     	HttpSession httpSession = request.getSession(false);
	     	int userid= (Integer) httpSession.getAttribute("userid");
	     	System.out.println(userid);
	     	httpSession.removeAttribute("userid");
	     	System.out.println(userid);
	        httpSession.invalidate();
	        out.println("logged out success");
	        log.info("Http Session closed");
	        
	        //response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	        //response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	        //response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	        //response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
	        //int userid = (Integer)httpSession.getAttribute("userid");
	        //if(0 == userid) {
	        	
	        //}	
	        
	        if(httpSession !=null) {
	        	httpSession.invalidate();
	        }else {
	        	out.println("logged out");
	        	log.info("Passing control to Home.html");
	        	System.out.println("loged out");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("Home.html");
	        	dispatcher.forward(request, response);
	        	System.out.println("loged out");
	        }
	        	}
	        
	        
	}*/

	


