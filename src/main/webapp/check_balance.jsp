<%@page import="dto.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@page import="dao.Bank_dao"%>
<%@page import="dto.BankAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Balance</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

h1 {
	font-size: 28px;
	margin-top: 30px;
	margin-bottom: 30px;
	text-align: center;
}

.account-info {
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	margin: 50px auto;
	max-width: 500px;
	padding: 30px;
	text-align: center;
}

.account-info h2 {
	font-size: 24px;
	margin-bottom: 20px;
}

.account-info p {
	font-size: 20px;
	line-height: 1.5;
	margin: 0;
	padding: 0;
}

a {
	display: block;
	font-size: 16px;
	text-align: center;
	margin-top: 30px;
	margin-bottom: 30px;
	color: #4CAF50;
}
</style>
</head>
<body>
	<%
	 CustomerInfo customerInfo1  = (CustomerInfo)session.getAttribute("customerInfo");
    if(customerInfo1==null){
    	response.getWriter().print("<h1>Pleaze Login Again</h1>");
    	request.getRequestDispatcher("login.html").include(request,response);
    }else{
	long acno = (long) request.getSession().getAttribute("acno");
	Bank_dao bank_dao = new Bank_dao();
	BankAccount account = bank_dao.find(acno);
	CustomerInfo customerInfo = account.getCustomerInfo();
	%>

	<div class="account-info">
		

		<h2>
			Hello
			<%
		if (customerInfo.getGender().equals("male")) {
		%>Mr.<%
		} else {
		%>Ms.<%
		}
		%><%=customerInfo.getName()%>
		
		</h2>
		
		<h2>Account Number: <%=account.getAccount_No()%></h2><br>
		<h2>Account Type: <%=account.getType()%></h2><br>
		<h2>Your Account balance is:</h2>
		<h2><%=account.getAmount()%></h2>
		<a href="Account_home.html">Back to account home</a>
		<%} %>
	</div>
</body>
</html>
