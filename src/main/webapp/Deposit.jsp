<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
HttpSession httpSession = request.getSession(false);
int userid= (Integer) httpSession.getAttribute("userid");
out.println(userid);
if(userid == 0){
	  RequestDispatcher dispatcher = request.getRequestDispatcher("Home.html");
	  dispatcher.forward(request, response);
}
%>    
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet" href="Deposit.css">
<link rel="stylesheet" href="common.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">
  	<img src="https://upload.wikimedia.org/wikipedia/commons/4/47/Eo_circle_blue_letter-h.svg" class="bank-logo"/>
  	<span class="name" ><b> Hyderabad National Bank </b> </span>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" id="icons" href="./AccountDetails">Profile Details<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="Deposit.jsp">Deposit</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="Withdraw.jsp">Withdraw</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="Transfer.jsp">Transfer</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="./CheckBalanceServlet">Check Balance</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="./ViewTranscationServlet">View Transcation</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="">Logout</a>
      </li>
      
    </ul>
  </div>
</nav>
<div class="background-image">
	<div class="card-container">
	<div class="form-container">
	<div>
	<h1 class="form-heading">Deposit Amount</h1>
	</div>
	<div>
	<form  action="DepositServlet" class="form" method="post">
	
	
	<table>
	 <tr>
	    <td>Account No:</td>
	    <td><input type="text" value="${userid}" name="accNo" readonly></td>
	   </tr>
	   <tr>
	    <td>Deposit  Amount:</td>
	    <td><input type="text" name="amount" placeholder="Enter here"></td>
	   </tr>
	   <tr>
	    <td></td><td><input class="button" type="submit" value="Continue"></td>
	   </tr>
	   </table>
	
	</form>
	</div>
	</div>
	</div>
</div>
<div class="footer-section">
   <div class="footer-content">
   	<div>
   		<ul style="list-style: none;">
   		    <li><b>EXPLORE</b></li>
   			<li>Investor Relations</li>
   			<li>Internet Banking</li>
   			<li>Mobile Banking</li>
		</ul>
   			
   	</div>
    <div>
   		<ul style="list-style: none;">
   		    <li><b>POPULAR PRODUCTS</b></li>
   			<li>Savings Accounts</li>
   			<li>Current Accounts</li>
   			<li>Credit Cards</li>
		</ul>
   			
   	</div>
	<div>
   		<ul style="list-style: none;">
   		    <li><b>INITIATIVES</b></li>
   			<li>Debt Service</li>
   			<li>Blogs</li>
   			
		</ul>
   			
   	</div>
	
	<div>
   		<ul style="list-style: none;">
   		    <li><b>RATES AND CHARGES</b></li>
   			<li>Interest Rates</li>
   			<li>Service Charges and Fees</li>
   			
		</ul>
   			
   	</div>
  
   </div>
   </div>

</body>
</html>