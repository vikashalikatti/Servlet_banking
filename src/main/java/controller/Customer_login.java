package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer_dao;
import dto.CustomerInfo;

@WebServlet("/customerlogin")
public class Customer_login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id = Integer.parseInt(req.getParameter("userid"));
		String password = req.getParameter("password");
		
//		resp.getWriter().print("<h1>"+user_id+"</h1>"
//								+"<h1>"+password+"</h1>");
		
		Customer_dao customer_dao = new Customer_dao();
		
		CustomerInfo customerInfo = customer_dao.login(user_id);
		if(customerInfo==null) {
			resp.getWriter().print("<h1>Invalid Customer_ID</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else {
			if(customerInfo.getPassword().equals(password)) {
				req.getSession().setAttribute("customerInfo", customerInfo);
				resp.getWriter().print("<h1>Login Sucess</h1>");
				resp.getWriter().print("<h1>Hi "+customerInfo.getName()+"</h1>");
				req.getRequestDispatcher("customer_home.html").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1>Invalid password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
		
		
	}
}
