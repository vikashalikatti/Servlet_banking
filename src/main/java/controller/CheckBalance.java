package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bank_dao;
import dto.BankAccount;

@WebServlet("/checkBalance")
public class CheckBalance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long acno = (long) req.getSession().getAttribute("acno");

            Bank_dao bank_dao = new Bank_dao();
            BankAccount account = bank_dao.find(acno);

            req.setAttribute("balance", account.getAmount());
            req.getRequestDispatcher("check_balance.jsp").forward(req, resp);
        } catch (Exception e) {
        	resp.sendRedirect("somthing_went_wrong.html");
        }
    }
}
