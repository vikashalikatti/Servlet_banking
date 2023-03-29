package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bank_dao;

@WebServlet("/AdminLogin")
public class Admin_login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String user = req.getParameter("username");
            String password = req.getParameter("password");

            if (user.equals("admin") && password.equals("admin")) {
                resp.getWriter().print("<h1><center>Welcome Admin</center></h1>");

                Bank_dao bank_dao = new Bank_dao();
                req.setAttribute("list", bank_dao.fetchAll());
                req.getRequestDispatcher("Admin.jsp").include(req, resp);

            } else {
                resp.getWriter().print("<h1><center>Invalid Credentials</center></h1>");
                req.getRequestDispatcher("admin.html").include(req, resp);
            }
        } catch (Exception e) {
            resp.sendRedirect("somthing_went_wrong.html");
        }
    }
}
