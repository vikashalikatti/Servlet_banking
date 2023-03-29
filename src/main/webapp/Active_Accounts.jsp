<%@page import="dto.BankAccount"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Active Accounts</title>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap');

    body {
        font-family: 'Roboto', sans-serif;
        background-color: #f5f5f5;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
        margin: 0;
    }

    h1 {
        text-align: center;
        margin-bottom: 30px;
        color: #3f51b5;
        font-weight: 500;
        text-transform: uppercase;
        letter-spacing: 1.5px;
        animation: fadeInDown 1s ease;
    }

    table {
        border-collapse: collapse;
        width: 60%;
        text-align: center;
        animation: fadeIn 1s ease;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 12px;
    }
    th {
        background-color: #3f51b5;
        color: white;
        font-weight: 500;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    button {
        background-color: #3f51b5;
        color: white;
        padding: 8px 16px;
        margin: 10px 0;
        border: none;
        cursor: pointer;
        font-size: 14px;
        border-radius: 5px;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        transition: 0.3s;
    }

    button:hover {
        background-color: #283593;
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    @keyframes fadeIn {
        0% {opacity: 0;}
        100% {opacity: 1;}
    }

    @keyframes fadeInDown {
        0% {
            opacity: 0;
            transform: translateY(-30px);
        }
        100% {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>
</head>
<body>
    <%
    List<BankAccount> list = (List<BankAccount>) request.getAttribute("list");
    if(list.isEmpty()){
    %>
    <h1>No Active Accounts Found</h1>
    <%}else{ %>
    <h1>Select Bank Account</h1>
    <table>
        <thead>
            <tr>
                <th>Account Number</th>
                <th>Type</th>
                <th>Select</th>
            </tr>
        </thead>
        <tbody>
        <%for(BankAccount account:list){ %>
            <tr>
                <td><%=account.getAccount_No()%></td>
                <td><%=account.getType()%></td>
                <td><a href="setaccount?acno=<%=account.getAccount_No() %>"><button>Select</button></a></td>
            </tr>
        <%} %>
        </tbody>
    </table>
    <%} %>
    <br>
    <a href="customer_home.html"><button>Back</button></a>
</body>
</html>
