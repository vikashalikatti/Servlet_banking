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

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
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

				if (amt > account.getAmount()) {
					resp.getWriter().print("<h1>Insufficient balance</h1>");
					req.getRequestDispatcher("withdraw.html").include(req, resp);
				} else {
					if (amt > account.getAccount_limit()) {
						resp.getWriter()
								.print("<h1>Out of Limit Enter Amount With In " + account.getAccount_limit() + "</h1>");
						req.getRequestDispatcher("withdraw.html").include(req, resp);
					} else {
						account.setAmount(account.getAmount() - amt);

						Bank_Transaction transaction = new Bank_Transaction();
						transaction.setDeposit(0);
						transaction.setWithdraw(amt);
						transaction.setBalance(account.getAmount());
						transaction.setDateTime(LocalDateTime.now());

						List<Bank_Transaction> list = account.getBank_Transactions();
						list.add(transaction);

						bank_dao.update(account);
						resp.getWriter().print("<h1>Amount has been withdrawn successfully</h1>");
						req.getRequestDispatcher("Account_home.html").include(req, resp);
					}
				}
			}
		} catch (NumberFormatException e) {
			resp.sendRedirect("somthing_went_wrong.html");
		} catch (Exception e) {
			resp.sendRedirect("somthing_went_wrong.html");
		}
	}
}
