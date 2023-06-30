<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet" href="CheckBalance.css">
<link rel ="stylesheet" href="common.css">
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
  	<span class="name"> <b> Hyderabad National Bank </b></span>
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
        <a class="nav-link" id="icons" href="./LogoutServlet">Logout</a>
      </li>
      
    </ul>
  </div>
</nav>
<div class="background-image">


<h1 class="heading" ><center>Your balance amount is ${AccBalance}</center></h1>

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