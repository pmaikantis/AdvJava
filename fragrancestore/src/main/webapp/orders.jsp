<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.fragrancestore.model.User" %>
<%@page import="com.fragrancestore.model.Order" %>
<%@page import="com.fragrancestore.connection.DbCon" %>
<%@page import="fragrancestore.dao.OrderDao" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Order> userOrders = new OrderDao(DbCon.getConnection()).getOrdersByUserId(auth.getId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your Orders</title>
</head>
<body>
    <h1>Your Orders</h1>
    <%
        if (userOrders != null && !userOrders.isEmpty()) {
            for (Order order : userOrders) {
    %>
        <div>
            <h3>Order ID: <%= order.getOrderId() %></h3>
            <p>Order Date: <%= order.getOrderDate() %></p>
            <p>Quantity: <%= order.getOrderQuantity() %></p>
            <p>Total Price: $<%= String.format("%.2f", order.getTotalPrice()) %></p>
        </div>
    <%
            }
        } else {
    %>
        <p>No orders available.</p>
    <%
        }
    %>
</body>
</html>
