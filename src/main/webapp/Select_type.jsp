<%@page import="dto.CustomerInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Type Account</title>
<style>
  body {
  background-color: #f4f4f4;
  font-family: 'Roboto', sans-serif;
  font-size: 16px;
  line-height: 1.5;
  color: #333;
}

.container {
  margin: 0 auto;
  max-width: 600px;
  padding: 30px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

h1 {
  font-size: 36px;
  margin-top: 0;
  margin-bottom: 20px;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 2px;
  color: #1b1b1b;
}

h2 {
  font-size: 24px;
  margin-bottom: 30px;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 2px;
  color: #1b1b1b;
}

.form-group {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

input[type=radio] {
  margin-right: 15px;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  height: 20px;
  width: 20px;
  border-radius: 50%;
  border: 2px solid #ccc;
  outline: none;
  cursor: pointer;
}

input[type=radio]:checked {
  background-color: #4CAF50;
  border-color: #4CAF50;
}

label {
  font-size: 20px;
  cursor: pointer;
  font-weight: bold;
  letter-spacing: 1px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 30px;
}

button {
  padding: 12px 25px;
  border: none;
  border-radius: 30px;
  font-size: 18px;
  color: #fff;
  cursor: pointer;
  transition: all 0.3s ease;
}

button[type=submit] {
  background-color: #4CAF50;
}

button[type=reset] {
  background-color: #ccc;
  color: #1b1b1b;
}

@media screen and (max-width: 768px) {
  h1 {
    font-size: 28px;
  }

  h2 {
    font-size: 22px;
  }

  .form-group {
    flex-direction: column;
  }

  label {
    margin-bottom: 10px;
  }

  input[type=radio] {
    margin-right: 0;
    margin-bottom: 10px;
  }

  .button-group {
    flex-direction: column;
  }

  button {
    margin-top: 15px;
  }
}
  
    
</style>
</head>
<body>
    <%
    CustomerInfo customerInfo  = (CustomerInfo)session.getAttribute("customerInfo");
    if(customerInfo==null){
    	response.getWriter().print("<h1>Pleaze Login Again</h1>");
    	request.getRequestDispatcher("login.html").include(request,response);
    }else{
    %>
    <div class="container">
        <h1>Hello <%=customerInfo.getName()%></h1>
        <h2>Select Type of Account</h2>
        <form action="createbankaccount">
            <div class="form-group">
                <input type="radio" name="banktype" value="saving" id="saving" required="required">
                <label for="saving">Saving</label>
            </div>
            <div class="form-group">
                <input type="radio" name="banktype" value="current" id="current">
                <label for="current">Current</label>
            </div>
            <div class="button-group">
                <button type="reset">Cancel</button>
                <button type="submit">Submit</button>
            </div>
        </form>
        <%} %>>
    </div>
</body>
</html>
