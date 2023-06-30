<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet" href="common.css">
<link rel ="stylesheet" href="AccountDetails.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<meta charset="ISO-8859-1">
<style>
bg-color{
	height: 750px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">
  	<img src="https://upload.wikimedia.org/wikipedia/commons/4/47/Eo_circle_blue_letter-h.svg" class="bank-logo"/>
  	<span class="name"> <b> Hyderabad National Bank </b> </span>
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
        <a class="nav-link" id="icons" href="./ViewTranscationsServlet">View Transcation</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="icons" href="./LogoutServlet">Logout</a>
      </li>
      
    </ul>
  </div>
</nav>
<div class="bg-color">
<div>
<form  action="EditDetailsServlet" class="form" method="post">
<table class="table-style">
   <tr>
    <td>Account No:</td>
    <td><input type="text" name="accNo" value="${AccID}" readonly></td>
   </tr>
   <tr>
    <td>UserName:</td>
    <td><input type="text"  name="user" value="${UserName}"></td>
   </tr>
   <tr>
   <tr>
    <td>City:</td>
    <td><input type="text" name="city" value="${City}"></td>
   </tr>
   <tr>
    <td>State:</td>
    <td><input type="text" name="state" value="${State}"></td>
   </tr>
   <tr>
   <tr>
    <td>Mobile:</td>
    <td><input type="text" name="mobile" value="${Mobile}"></td>
   </tr>
   <tr>
    <td>Password:</td>
    <td><input type="text" name="password" value="${Password}"></td>
   </tr>
   <tr>
   <tr>
    <td>Email:</td>
    <td><input type="text" name="email" value="${Email}"></td>
   </tr>
   <tr>
    <td>Country:</td>
    <td><input type="text" name="country" value="${Country}"></td>
   </tr>
   <tr>
    <td></td><td><input class="button" type="submit" value="Edit" required></td>
   </tr>
   </table>
</form>
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