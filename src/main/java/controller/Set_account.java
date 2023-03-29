package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bank_dao;
import dto.BankAccount;

@WebServlet("/setaccount")
public class Set_account extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long acno = Long.parseLong(req.getParameter("acno"));

            req.getSession().setAttribute("acno", acno);

            req.getRequestDispatcher("Account_home.html").include(req, resp);
        } catch (NumberFormatException e) {
            resp.getWriter().print("<h1>Error: Invalid account number format</h1>");
            req.getRequestDispatcher("error_page.jsp").include(req, resp);
        } catch (Exception e) {
        	resp.sendRedirect("somthing_went_wrong.html");
        }
    }
}
