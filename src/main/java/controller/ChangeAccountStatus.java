package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bank_dao;
import dto.BankAccount;

@WebServlet("/changestatus")
public class ChangeAccountStatus extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long acno = Long.parseLong(req.getParameter("acno"));
            Bank_dao bank_dao = new Bank_dao();
            BankAccount account = bank_dao.find(acno);

            if (account.isStatus()) {
                account.setStatus(false);
            } else {
                account.setStatus(true);
            }

            bank_dao.update(account);
            resp.getWriter().print("<h1><center>Update Success</center></h1>");

            req.setAttribute("list", bank_dao.fetchAll());
            req.getRequestDispatcher("Admin.jsp").include(req, resp);
        } catch (Exception e) {
        	resp.sendRedirect("somthing_went_wrong.html");
        }
    }
}
