<%@page import="com.fragrancestore.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
   User auth = (User)request.getSession().getAttribute("auth");
   if (auth != null) {
       response.sendRedirect("index.jsp");
   }

   String popupMessage = (String) session.getAttribute("popupMessage");


   if (popupMessage != null) {
       session.removeAttribute("popupMessage");
   }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@ include file="includes/head.jsp" %>
    <script>

        <% if (popupMessage != null) { %>
            alert("<%= popupMessage %>");
        <% } %>
    </script>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">
                <h3>Login</h3>
            </div>
            <div class="card-body">
                <!-- Updated form with correct action and method -->
                <form action="user-login" method="post">
                    <!-- Email Address -->
                    <div class="form-group">
                        <label>Email Address</label>
                        <input 
                            type="email" 
                            class="form-control" 
                            name="login-email" 
                            placeholder="Enter your email" 
                            required />
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                        <label>Password</label>
                        <input 
                            type="password" 
                            class="form-control" 
                            name="login-password" 
                            placeholder="*******" 
                            required />
                    </div>
                    <!-- Submit Button -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
