package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BankAccount;
import dto.CustomerInfo;

@WebServlet("/fetchactiveaccount")
public class Fetch_Active_account extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerInfo customerInfo = (CustomerInfo) req.getSession().getAttribute("customerInfo");
            if(customerInfo==null){
            	resp.getWriter().print("<h1>Pleaze Login Again</h1>");
            	req.getRequestDispatcher("login.html").include(req,resp);
            }else {
            List<BankAccount> list = customerInfo.getAccounts();

            List<BankAccount> list2 = new ArrayList<>();
            for (BankAccount account : list) {
                if (account.isStatus()) {
                    list2.add(account);
                }
            }
            req.setAttribute("list", list2);
            req.getRequestDispatcher("Active_Accounts.jsp").include(req, resp);
            }
        } catch (Exception e) {
        	resp.sendRedirect("somthing_went_wrong.html");
        }
    }
}
