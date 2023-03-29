package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bank_dao;
import dao.Customer_dao;
import dto.BankAccount;
import dto.CustomerInfo;

@WebServlet("/createbankaccount")
public class Create_Bank_account extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String banktype = req.getParameter("banktype");
            CustomerInfo customerInfo = (CustomerInfo) req.getSession().getAttribute("customerInfo");
            List<BankAccount> list = customerInfo.getAccounts();
            boolean flag = true;
            for(BankAccount account:list) {
                if(account.getType().equals(banktype)) {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                BankAccount account = new BankAccount();
                account.setType(banktype);
                if (banktype.equals("saving")) 
                    account.setAccount_limit(10000);
                else 
                    account.setAccount_limit(50000);
                account.setCustomerInfo(customerInfo);
                Bank_dao bank_dao = new Bank_dao();
                bank_dao.save(account);
                list.add(account);
                customerInfo.setAccounts(list);
                Customer_dao customer_dao =  new Customer_dao();
                customer_dao.update(customerInfo);
                resp.getWriter().print("<h1><center>Account Created Successfully Wait for Management To Approve</center></h1>");
                resp.getWriter().print("<h1><center>"+customerInfo.getCustomer_id()+"</center></h1>");
                resp.getWriter().print("<h1><center>"+customerInfo.getName()+"</center></h1>");
                resp.getWriter().print("<h1><center>"+customerInfo.getEmail()+"</center></h1>");
                resp.getWriter().print("<h1><center>"+customerInfo.getMobile()+"</center></h1>");
                resp.getWriter().print("<h1><center>"+account.getAccount_No()+"</center></h1>");
                resp.getWriter().print("<h1><center>"+account.getType()+"</center></h1>");
                req.getRequestDispatcher("Rener_Home_page.html").include(req, resp);
            } else {
                resp.getWriter().print("<h1>"+ banktype +" Account Already Exists</h1>");
                req.getRequestDispatcher("customer_home.html").include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("somthing_went_wrong.html");
        }
    }
}
