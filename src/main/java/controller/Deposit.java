package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bank_dao;
import dto.BankAccount;

import dto.Bank_Transaction;
import dto.CustomerInfo;

@WebServlet("/deposit")
public class Deposit extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			CustomerInfo customerInfo = (CustomerInfo) req.getSession().getAttribute("customerInfo");
			if (customerInfo == null) {
				resp.getWriter().print("<h1>Pleaze Login Again</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			} else {
				double amt = Double.parseDouble(req.getParameter("amt"));
				long acno = (long) req.getSession().getAttribute("acno");

				Bank_dao bank_dao = new Bank_dao();
				BankAccount account = bank_dao.find(acno);

				account.setAmount(account.getAmount() + amt);

				Bank_Transaction transaction = new Bank_Transaction();
				transaction.setDeposit(amt);
				transaction.setWithdraw(0);
				transaction.setBalance(account.getAmount());
				transaction.setDateTime(LocalDateTime.now());

				List<Bank_Transaction> list = account.getBank_Transactions();
				list.add(transaction);

				bank_dao.update(account);
				resp.getWriter().print("<h1>Amount has been deposited successfully</h1>");
				req.getRequestDispatcher("Account_home.html").include(req, resp);
			}

		} catch (NumberFormatException e) {
			resp.sendRedirect("somthing_went_wrong.html");
		} catch (Exception e) {
			resp.sendRedirect("somthing_went_wrong.html");
		}
	}
}
