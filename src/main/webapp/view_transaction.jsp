<%@page import="dto.CustomerInfo"%>
<%@page import="dto.Bank_Transaction"%>
<%@page import="java.util.List"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.Bank_dao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<style>
      body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 80%;
        margin: 50px auto;
        padding: 40px;
        background-color: #ffffff;
        box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        animation: scaleUp 1.5s ease-in-out;
    }

    h1 {
        font-size: 28px;
        color: #2c3e50;
        margin-bottom: 20px;
        animation: fadeIn 1.5s ease-in-out;
        text-align: center;
        border-bottom: 2px solid #2c3e50;
        padding-bottom: 20px;
    }

    p {
        color: #34495e;
        animation: fadeIn 1.5s ease-in-out;
        font-size: 16px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        animation: fadeIn 1.5s ease-in-out;
        margin-bottom: 20px;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border: 1px solid #ccc;
    }

    th {
        background-color: #2c3e50;
        color: white;
        font-size: 16px;
        font-weight: bold;
    }

    td {
        font-size: 14px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }

    @keyframes scaleUp {
        0% {
            transform: scale(0.9);
            opacity: 0;
        }
        100% {
            transform: scale(1);
            opacity: 1;
        }
    }

    /* Unique CSS and animations */
    .unique-container {
        border: 4px dashed #9b59b6;
        animation: uniqueExpand 1.5s ease-in-out;
    }

    @keyframes uniqueExpand {
        0% {
            transform: scale(0.95);
            opacity: 0;
        }
        100% {
            transform: scale(1);
            opacity: 1;
        }
    }

    .unique-text {
        color: #9b59b6;
        font-weight: bold;
        animation: uniqueSlideIn 1.5s ease-in-out;
    }

    @keyframes uniqueSlideIn {
        0% {
            opacity: 0;
            transform: translateX(-100%);
        }
        100% {
            opacity: 1;
            transform:translateX(0);
}
}.unique-row {
    animation: uniqueRowFadeIn 1.5s ease-in-out;
}

@keyframes uniqueRowFadeIn {
    0% {
        opacity: 0;
        transform: translateY(20px);
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
    CustomerInfo customerInfo  = (CustomerInfo)session.getAttribute("customerInfo");
    if(customerInfo==null){
        response.getWriter().print("<h1>Please Login Again</h1>");
        request.getRequestDispatcher("login.html").include(request,response);
    }else{
    long acno = (long) request.getSession().getAttribute("acno");
    Bank_dao bank_dao = new Bank_dao();
    BankAccount account = bank_dao.find(acno);
    List<Bank_Transaction> list = account.getBank_Transactions();
    %>
    <div class="container unique-container">
        <h1 class="unique-text">Transactions</h1>
        <p>Account Number: <%=account.getAccount_No() %></p>
        <p>Account type: <%=account.getType() %></p>
        <p>Transactions:</p>
        <table>
            <tr>
                <th>ID</th>
                <th>Deposit</th>
                <th>Withdraw</th>
                <th>Balance</th>
                <th>Date</th>
            </tr>
            <% for (Bank_Transaction transaction : list) { %>
            <tr class="unique-row">
                <td><%=transaction.getId()%></td>
                <td><%=transaction.getDeposit()%></td>
                <td><%=transaction.getWithdraw()%></td>
                <td><%=transaction.getBalance()%></td>
                <td><%=transaction.getDateTime()%></td>
            </tr>
            <% }} %>
        </table>
        
    </div>
    <center><a href="Account_home.html"><button type="button" style="background-color: blue">Back</button></a></center>
</body>
</html>
