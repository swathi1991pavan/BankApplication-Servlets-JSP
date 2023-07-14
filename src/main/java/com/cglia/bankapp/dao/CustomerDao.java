package com.cglia.bankapp.dao;

import com.cglia.bankapp.bean.Customer;
import com.cglia.bankapp.bean.Account;
import com.cglia.bankapp.bean.Transcation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


    
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;


public class CustomerDao {
	 private static Logger log = Logger.getLogger(CustomerDao.class);
	
	 public static Connection getConnection(){
		    Layout layout = new SimpleLayout();
		    Appender app = new ConsoleAppender(layout);
		    log.addAppender(app);
		    log.info("start of connection method");
	        Connection con=null;  
	        try{
	        	log.info("Loading the driver");
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	log.info("Getting Connection");
	            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swathi","SWATHI","SWATHI@12345");
	            log.info("Connection to the database is Success");
	        }catch(Exception e)
	        	{
	        	log.error("Connection Fail");
	        	System.out.println(e);}  
	        return con;
	        
	 }
	   
	 public static int registerSave(Customer customer){  
	       int status=0;  
	        try{
	        	log.info("registerSave method calling connection");
	            Connection con=CustomerDao.getConnection(); 
	            log.info("inserting the values");
	            PreparedStatement ps=con.prepareStatement(  
	                         "insert into customer(AccountNo,Name,City,State,Mobile,Password,Email,Country) values (?,?,?,?,?,?,?,?)");  
	            ps.setInt(1,customer.getAccId());  
	            ps.setString(2,customer.getCustName());  
	            ps.setString(3,customer.getCity());  
	            ps.setString(4,customer.getState());
	            ps.setInt(5,customer.getMobile());
	            ps.setString(6, customer.getPassword());
	            ps.setString(7,customer.getEmail());
	            ps.setString(8,customer.getCountry());
	             
	            status=ps.executeUpdate();  
	            log.info("query executed with status" + status);  
	            con.close(); 
	            
	        }catch(Exception ex){
	        	log.error("Connection Fail");
	        	ex.printStackTrace();}  
	          
	        return status;  
	    }  
	 public static int loginSave(Account account){  
	       int loginStatus=0;  
	        try{ 
	        	log.info("loginSave method calling connection");
	            Connection con=CustomerDao.getConnection();
	            log.info("inserting the values");
	            PreparedStatement ps=con.prepareStatement(  
	                         "insert into account(AccountNo,Balance,AccountStatus) values (?,?,?)");  
	            ps.setInt(1,account.getAccId());  
	            ps.setFloat(2,account.getBalance());  
	            ps.setString(3,account.getAccStatus());  
	           
	            loginStatus=ps.executeUpdate();  
	            log.info("query executed with status" + loginStatus);  
	            con.close();  
	        }catch(Exception ex){
	        	log.error("Connection Fail");
	        	ex.printStackTrace();}  
	          
	        return loginStatus;  
	    }
	 public static String loginValidate(int accNo , String password) {
		 int status=0;
		 String userName="";
	        try{
	        	log.info("loginSave method calling connection");
	            Connection con=CustomerDao.getConnection(); 
	            log.info("Retrieving data");
	            PreparedStatement ps=con.prepareStatement( 
	            		"select * from customer where AccountNo=? and Password=?");
	            ps.setInt(1,accNo);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();
	            if(rs.next()) {
	            	status =1;
	            	userName = rs.getString(2);
	            }
	            log.info("query executed with status" + status);    
	            con.close();  
	        }catch(Exception ex){
	        	log.error("Connection Fail");
	        	ex.printStackTrace();}  
	          
	        return userName;  
	    }
	 
	 
	 public static int withdraw(int accNo,int amount) {
    	 int status=0; 
    	 Connection con= null;
	        try{ 
	        	log.info("loginSave method calling connection");
	             con=CustomerDao.getConnection();
	            con.setAutoCommit(false);  
	            log.info("Retrieving data");
	            PreparedStatement ps=con.prepareStatement(  
	            		"select * from account where AccountNo=?");
	            ps.setInt(1, accNo);
	            
	            ResultSet rs = ps.executeQuery();
	            
	            
	            while(rs.next()){
	            	log.info("query executed fetching the data");
	            	float currentAmount= rs.getInt(2);
		            if(currentAmount>amount) {
		            	log.info("entered if");
		            	float updateAmount= currentAmount-amount;
		            	log.info("Updating data");
		            	PreparedStatement pstat=con.prepareStatement(  
		            		"update account set Balance=? where AccountNo=?");
		            	pstat.setFloat(1, updateAmount);
		            	pstat.setInt(2,accNo);
		            	status=pstat.executeUpdate();
		            	log.info("query executed with status" + status); 
		            	
		            	log.info("inserting data into transcation table");
		            	PreparedStatement psTrans =con.prepareStatement(  
			            		"insert into transcation(AccountNo,TranscationType,TransactionStatus,Amount,FinalBalance) values (?,?,?,?,?)");
			            psTrans.setInt(1,accNo);
			            psTrans.setString(2,"Withdraw");
			            if(status>0) {
			            	psTrans.setString(3,"SUCCESS");
			            }
			            else {
			            	psTrans.setString(3,"FAILURE");
			            }
			            
			            psTrans.setInt(4,amount);
			            psTrans.setFloat(5,updateAmount);
			            System.out.println(updateAmount);
			            psTrans.executeUpdate();
			            log.info("query executed success");
			            
		            }	
	            }
	            
	            con.commit();
	       
	        }catch(Exception ex) {
	        	try {
					con.rollback();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	        }
	        finally {
	        	try {
	        		if (con != null) {
	        			con.close();
	        		}
	        	}catch(SQLException ignored) { }
	        }
	        return status;
     }
	 
	 
	 public static int deposit(int accNo,int amount) {
    	 int status=0;
       	 float updateAmount = 0;
       	Connection con= null;
	        try{ 
	        	log.info("deposit method calling connection");
	            con=CustomerDao.getConnection();
	            con.setAutoCommit(false); 
	            log.info("Retrieving data");
	            PreparedStatement ps=con.prepareStatement(  
	            		"select * from account where AccountNo=?");
	            ps.setInt(1, accNo);
	            ResultSet rs = ps.executeQuery();
	            
	            
	            while(rs.next()){
	            	log.info("data fetched success");
	            	float currentAmount= rs.getInt(2);
		            updateAmount= currentAmount+amount;
		            		            
		            log.info("updating data");
	            	PreparedStatement pstat=con.prepareStatement(  
		            		"update account set Balance=? where AccountNo=?");
	            	pstat.setFloat(1, updateAmount);
	            	pstat.setInt(2,accNo);
	    	        status=pstat.executeUpdate();
	    	        log.info("query executed with status" + status);
	            	
	    	        log.info("inserting data");
	            	PreparedStatement psTrans =con.prepareStatement(  
		            		"insert into transcation(AccountNo,TranscationType,TransactionStatus,Amount,FinalBalance) values (?,?,?,?,?)");
		            psTrans.setInt(1,accNo);
		            psTrans.setString(2,"Deposit");
		            if(status>0) {
		            	psTrans.setString(3,"SUCCESS");
		            }
		            else {
		            	psTrans.setString(3,"FAILURE");
		            }
		            
		            psTrans.setInt(4,amount);
		            psTrans.setFloat(5,updateAmount);
		            
		            psTrans.executeUpdate();
		            log.info("query executed with status" + psTrans);
		            
	            }
	            
	            	           
	            con.commit();
	            
	        }catch(Exception ex) {
	        	try {
					con.rollback();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	        }
	        finally {
	        	try {
	        		if (con != null) {
	        			con.close();
	        		}
	        	}catch(SQLException ignored) { }
	        }
	        return status;
     }
    
	/* public static int withdraw(int accNo,int amount) {
    	 int status=0;  
	        try{ 
	        	log.info("loginSave method calling connection");
	            Connection con=CustomerDao.getConnection();
	            log.info("Retrieving data");
	            PreparedStatement ps=con.prepareStatement(  
	            		"select * from account where AccountNo=?");
	            ps.setInt(1, accNo);
	            
	            ResultSet rs = ps.executeQuery();
	            
	            
	            while(rs.next()){
	            	log.info("query executed fetching the data");
	            	float currentAmount= rs.getInt(2);
		            if(currentAmount>amount) {
		            	log.info("entered if");
		            	float updateAmount= currentAmount-amount;
		            	log.info("Updating data");
		            	PreparedStatement pstat=con.prepareStatement(  
		            		"update account set Balance=? where AccountNo=?");
		            	pstat.setFloat(1, updateAmount);
		            	pstat.setInt(2,accNo);
		            	status=pstat.executeUpdate();
		            	log.info("query executed with status" + status); 
		            	
		            	log.info("inserting data into transcation table");
		            	PreparedStatement psTrans =con.prepareStatement(  
			            		"insert into transcation(AccountNo,TranscationType,TransactionStatus,Amount,FinalBalance) values (?,?,?,?,?)");
			            psTrans.setInt(1,accNo);
			            psTrans.setString(2,"Withdraw");
			            if(status>0) {
			            	psTrans.setString(3,"SUCCESS");
			            }
			            else {
			            	psTrans.setString(3,"FAILURE");
			            }
			            
			            psTrans.setInt(4,amount);
			            psTrans.setFloat(5,updateAmount);
			            System.out.println(updateAmount);
			            psTrans.executeUpdate();
			            log.info("query executed success");
		            }	
	            }
	            con.close();
	        }catch(Exception ex){
	        	log.error("Connection Fail");
	        	ex.printStackTrace();}
	        
	        return status;
	                
     }*/
	 
	 
    /* public static int deposit(int accNo,int amount) {
    	 int status=0;
       	 float updateAmount = 0;
	        try{ 
	        	log.info("deposit method calling connection");
	            Connection con=CustomerDao.getConnection();
	            
	            log.info("Retrieving data");
	            PreparedStatement ps=con.prepareStatement(  
	            		"select * from account where AccountNo=?");
	            ps.setInt(1, accNo);
	            ResultSet rs = ps.executeQuery();
	            
	            
	            while(rs.next()){
	            	log.info("data fetched success");
	            	float currentAmount= rs.getInt(2);
		            updateAmount= currentAmount+amount;
		            		            
		            log.info("updating data");
	            	PreparedStatement pstat=con.prepareStatement(  
		            		"update account set Balance=? where AccountNo=?");
	            	pstat.setFloat(1, updateAmount);
	            	pstat.setInt(2,accNo);
	    	        status=pstat.executeUpdate();
	    	        log.info("query executed with status" + status);
	            	
	    	        log.info("inserting data");
	            	PreparedStatement psTrans =con.prepareStatement(  
		            		"insert into transcation(AccountNo,TranscationType,TransactionStatus,Amount,FinalBalance) values (?,?,?,?,?)");
		            psTrans.setInt(1,accNo);
		            psTrans.setString(2,"Deposit");
		            if(status>0) {
		            	psTrans.setString(3,"SUCCESS");
		            }
		            else {
		            	psTrans.setString(3,"FAILURE");
		            }
		            
		            psTrans.setInt(4,amount);
		            psTrans.setFloat(5,updateAmount);
		            
		            psTrans.executeUpdate();
		            log.info("query executed with status" + psTrans);
		            
	            }
	            
	            	           
	            con.close();
	            
	        }catch(Exception ex){
	        	log.error("Connection Fail");
	        	ex.printStackTrace();}
	        
	        return status;
	                
     }*/
	  
     public static int transfer(int fromAccNo,int toAccNo, int amount) {
    	 int status=0;
    	 Connection con= null;
	        try{
	        	log.info("transfer method calling connection");
	             con=CustomerDao.getConnection();
	             con.setAutoCommit(false); 
	            log.info("Fetching data");
	            PreparedStatement ps=con.prepareStatement(  
	            		"select * from account where AccountNo=?");
	            ps.setInt(1, fromAccNo);
	            ResultSet rs = ps.executeQuery();
	            
	            
	            while(rs.next()){ 
	            	log.info("data fetched success");
	            	float currentAmount= rs.getInt(2);
	            	if(currentAmount>amount) {
		            	float updateAmount= currentAmount-amount;
		            	
		            	log.info("Updating data");
		            	PreparedStatement pstat=con.prepareStatement(  
		            		"update account set Balance=? where AccountNo=?");
		            	pstat.setFloat(1, updateAmount);
		            	pstat.setInt(2,fromAccNo);
	            		status=pstat.executeUpdate();
	            		log.info("query executed with status" + status);
	            		
		            	if(status>0) {
		            		log.info("Fetching data");
		            		PreparedStatement psToAcc=con.prepareStatement(  
			            		"select * from account where AccountNo=?");
		            		psToAcc.setInt(1, toAccNo);
		            		ResultSet rsToAcc = psToAcc.executeQuery();
			            
		            		while(rsToAcc.next()) {
		            			log.info("data fetched success");
		            			float toAccCurrentAmount= rsToAcc.getInt(2);
		            			float updateToAcc= toAccCurrentAmount + amount;
		            			
		            			log.info("Updating data");
		            			PreparedStatement psUpdToAcc=con.prepareStatement(  
				            		"update account set Balance=? where AccountNo=?");
		            			psUpdToAcc.setFloat(1, updateToAcc);
		            			psUpdToAcc.setInt(2,toAccNo);
		            			status=psUpdToAcc.executeUpdate();
		            			log.info("query executed with status" + status); 
		            			
		            			if(status>0) {
		            				log.info("inserting values");
		            				PreparedStatement psTrans =con.prepareStatement(  
		        		            		"insert into transcation(AccountNo,TranscationType,TransactionStatus,Amount,FinalBalance) values (?,?,?,?,?)");
		        		            psTrans.setInt(1,fromAccNo);
		        		            psTrans.setString(2,"Transfer");
		        		            if(status>0) {
		        		            	psTrans.setString(3,"SUCCESS");
		        		            }
		        		            else {
		        		            	psTrans.setString(3,"FAILURE");
		        		            }
		        		            
		        		            psTrans.setInt(4,amount);
		        		            psTrans.setFloat(5,updateAmount);
		        		            
		        		            psTrans.executeUpdate();
		        		            log.info("Query success");
		        		            System.out.println("success");
		            			}
		            		}	
			            }
	            	}
		            	
	            }
	            con.commit();
	        }catch(Exception ex) {
	        	try {
					con.rollback();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	        }
	        finally {
	        	try {
	        		if (con != null) {
	        			con.close();
	        		}
	        	}catch(SQLException ignored) { }
	        }
	        return status;
     }
	            
	        
	                
     
     
     public static Customer getCustById(int accNo ){  
         Customer c=new Customer();  
           
         try{
        	 log.info("getCustById method calling connection");
             Connection con=CustomerDao.getConnection(); 
             log.info("Fetching data");
             PreparedStatement ps=con.prepareStatement("select * from customer where AccountNo=?");  
             ps.setInt(1,accNo);  
             ResultSet rs=ps.executeQuery();  
             if(rs.next()){  
            	 log.info("data fetched success");  
                 c.setAccId(rs.getInt(1));  
                 c.setCustName(rs.getString(2));  
                 c.setCity(rs.getString(3));  
                 c.setState(rs.getString(4));  
                 c.setMobile(rs.getInt(5));
                 c.setPassword(rs.getString(6));
                 c.setEmail(rs.getString(7));
                 c.setCountry(rs.getString(8));
                 log.info("values assigned successfully");
                    
             }  
             con.close();  
         }catch(Exception ex){
        	 log.error("error");
        	 ex.printStackTrace();}  
           
         return c;  
     }
     
     public static float checkBalance(int accNo ){ 
    	 float balance = 0.0f;
    	 try{
    		 log.info("checkBalance method started");
             Connection con=CustomerDao.getConnection(); 
             log.info("Fetching Data");
             PreparedStatement ps=con.prepareStatement("select balance from  account where AccountNo=?");  
             ps.setInt(1,accNo);  
             ResultSet rs=ps.executeQuery(); 
             if(rs.next()) {
            	 log.info("data fetch success");
            	 balance=rs.getInt(1);
            	 
             }
             con.close();  
         }catch(Exception ex){
        	 log.error("error");
        	 ex.printStackTrace();}  
    	 
    	 return balance;
     }
     
     public static  List<Transcation> transcation(int accNo) {
    	 List<Transcation> transList = new ArrayList<Transcation>();
    	 try{
    		 log.info("transcation method calling connection");
             Connection con=CustomerDao.getConnection();
             
             log.info("Fetching data");
             PreparedStatement ps=con.prepareStatement("select * from  transcation where AccountNo=?");  
             ps.setInt(1,accNo);  
             ResultSet rs=ps.executeQuery(); 
             while(rs.next()) {
            	 log.info("data fetch success");
            	 Transcation t = new Transcation();
            	 t.setAccId(rs.getInt(1));
            	 t.setTransType(rs.getString(2));
            	 t.setTransStatus(rs.getString(3));
            	 t.setAmount(rs.getInt(4));
            	 t.setBalance(rs.getFloat(5));
            	 transList.add(t);
             }
             con.close();  
         }catch(Exception ex){
        	 log.error("error");
        	 ex.printStackTrace();}  
    	 
    	 return transList;
     }
     
     public static Customer updateDetails(Customer customer) {
    	 Customer updCustomer=new Customer();
    	 int status=0;  
	        try{
	        	log.info("updateDetails method calling connection");
	            Connection con=CustomerDao.getConnection(); 
	            log.info("updating the values");
	            System.out.println(customer.getAccId());
	            System.out.println(customer.getEmail());
	            PreparedStatement ps=con.prepareStatement(  
	   	                   "update customer set Name=? ,City=? ,State=? ,Mobile=? ,Password=? ,Email=? ,Country=? where AccountNo=?");  
	           
	            ps.setString(1,customer.getCustName());  
	            ps.setString(2,customer.getCity());  
	            ps.setString(3,customer.getState());
	            ps.setInt(4,customer.getMobile());
	            ps.setString(5, customer.getPassword());
	            ps.setString(6,customer.getEmail());
	            ps.setString(7,customer.getCountry());
	            ps.setInt(8,customer.getAccId()); 
	            status=ps.executeUpdate();  
	            log.info("query executed with status" + status);
	            
	            if(status>0) {
	            	updCustomer = getCustById(customer.getAccId());
	            }
	            con.close(); 
	            
	        }catch(Exception ex){
	        	log.error("Connection Fail");
	        	ex.printStackTrace();}  
	          
	        return updCustomer;  
     }
	  
}
