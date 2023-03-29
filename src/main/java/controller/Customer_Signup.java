package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer_dao;
import dto.CustomerInfo;

@WebServlet("/CustomerSignup")
public class Customer_Signup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Customer_dao dao = new Customer_dao();
            long mobile = Long.parseLong(req.getParameter("mobile"));
            String email = req.getParameter("email");
            Date date = Date.valueOf(req.getParameter("dob"));
            int age = Period.between(date.toLocalDate(), LocalDate.now()).getYears();
            if (age < 18) {
                req.getRequestDispatcher("Signup_error.html").include(req, resp);
            } else {
                if (dao.check(mobile).isEmpty() && dao.check(email).isEmpty()) {
                    CustomerInfo customerInfo = new CustomerInfo();
                    customerInfo.setName(req.getParameter("name"));
                    customerInfo.setPassword(req.getParameter("password"));
                    customerInfo.setGender(req.getParameter("gender"));
                    customerInfo.setDate(date);
                    customerInfo.setMobile(mobile);
                    customerInfo.setEmail(email);
                    dao.save(customerInfo);
                    resp.getWriter().print("<h1>Your Customer ID Created Successfully</h1>");
                    if(customerInfo.getGender().endsWith("female")) {
                        resp.getWriter().print("<h1><center>Hello Mam</center></h1>");
                        resp.getWriter().print("<h1><center>Your Customer ID:"+customerInfo.getCustomer_id()+"</center></h1>");
                        req.getRequestDispatcher("Account_create_successfully.html").include(req, resp);
                    } else {
                        resp.getWriter().print("<h1><center>Hello Sir</center></h1>");
                        resp.getWriter().print("<h1><center>Your Customer ID:"+customerInfo.getCustomer_id()+"</center></h1>");
                        req.getRequestDispatcher("Account_create_secessfully.html").include(req, resp);
                    }
                } else {
                    resp.getWriter().print("<h1>Email or Phone Number already Exists</h1>");
                    req.getRequestDispatcher("signup.html").include(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("home.html");
        }
    }
}
