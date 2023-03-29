<%@page import="dto.BankAccount"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bank Accounts</title>
<!-- Include Font Awesome library -->
<script src="https://kit.fontawesome.com/49b30a67fc.js"
	crossorigin="anonymous"></script>
<style>
body {
	background-color: #f5f5f5;
	font-family: Arial, sans-serif;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}

button {
	background-color: #008CBA;
	color: white;
	border: none;
	padding: 10px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

button:hover {
	background-color: #005f77;
}

table tr td:last-child button {
	transition: background-color 0.2s ease-in-out;
}

table tr td:last-child button:hover {
	background-color: #005f77;
}
/* Add hover effect to table rows */
tr:hover {
	background-color: #ddd;
}

/* Style the "Change Status" button */
button {
	background-color: #008CBA;
	color: white;
	border: none;
	padding: 10px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 5px;
	transition: all 0.3s ease-in-out;
}

/* Add hover effect to "Change Status" button */
button:hover {
	background-color: #005f7f;
}

/* Style the table header */
th {
	background-color: #4CAF50;
	color: white;
	font-weight: bold;
	font-size: 14px;
	padding: 12px 8px;
	text-align: left;
	border-bottom: 2px solid #ddd;
}

/* Style the table cells */
td {
	padding: 8px;
	font-size: 14px;
	border-bottom: 1px solid #ddd;
}

/* Style the table footer */
tfoot {
	background-color: #f2f2f2;
	font-weight: bold;
}

/* Style the "Logout" button */
a button {
	background-color: #f44336;
}

/* Add margin to "Logout" button */
a button {
	margin-top: 20px;
}

/* Add new CSS classes for status indicators */
.status-true {
	color: green;
}

.status-false {
	color: red;
}
</style>
</head>
<body>
	<%
	List<BankAccount> list = (List<BankAccount>) request.getAttribute("list");
	%>
	<center>
		<table>
			<thead>
				<tr>
					<th>Account Number</th>
					<th>Account Type</th>
					<th>Customer Name</th>
					<th>Customer Id</th>
					<th>Status</th>
					<th>Change Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (BankAccount account : list) {
				%>
				<tr>
					<td><%=account.getAccount_No()%></td>
					<td><%=account.getType()%></td>
					<td><%=account.getCustomerInfo().getName()%></td>
					<td><%=account.getCustomerInfo().getCustomer_id()%></td>
					<td><i
						class="fa-regular fa-sun fa-beat-fade <%=account.isStatus() ? "status-true" : "status-false"%>"></i>
					</td>
					<td>
						<form method="POST" action="changestatus">
							<input type="hidden" name="acno"
								value="<%=account.getAccount_No()%>">
							<button type="submit">Change</button>
						</form>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<br> <a href="home.html"><button>Logout</button></a>
	</center>
</body>
</html>

