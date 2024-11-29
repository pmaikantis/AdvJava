<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.fragrancestore.model.User" %>
<%@page import="com.fragrancestore.model.Cart" %>
<%@page import="com.fragrancestore.connection.DbCon" %>
<%@page import="fragrancestore.dao.ProductDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProducts = null;
    double subtotal = 0.0;

    if (cart_list != null) {
        ProductDao pDao = new ProductDao(DbCon.getConnection());
        cartProducts = pDao.getCartProducts(cart_list);

        for (Cart item : cartProducts) {
            subtotal += item.getPrice() * item.getQuantity();
        }
    }

    double tax = subtotal * 0.13;
    double total = subtotal + tax;

    String popupMessage = (String) session.getAttribute("popupMessage");
    if (popupMessage != null) {
        session.removeAttribute("popupMessage");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart - Athena's Apothecary</title>
    <%@include file="includes/head.jsp" %>
    <style>
        .cart-container {
            max-width: 900px;
            margin: 30px auto;
        }
        .thumbnail {
            max-width: 100px;
            max-height: 100px;
        }
        .btn-decre, .btn-incre {
            font-size: 20px;
            padding: 5px 10px;
        }
        .quantity-input {
            width: 50px;
            text-align: center;
        }
        .btn-danger {
            padding: 5px 15px;
        }
        .table td, .table th {
            vertical-align: middle;
            text-align: center;
        }
        .quantity-wrapper {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }
        .summary-container {
            margin-top: 30px;
        }
        .summary-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            font-size: 1.2rem;
        }
        .checkout-btn {
            width: 100%;
            background-color: #007bff;
            color: white;
            padding: 10px;
            font-size: 1.2rem;
            border: none;
            cursor: pointer;
        }
        .checkout-btn:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        <% if (popupMessage != null) { %>
            alert("<%= popupMessage %>");
        <% } %>
    </script>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    <div class="container cart-container">
        <table class="table table-light">
            <thead>
                <tr>
                    <th>Thumbnail</th>
                    <th>Title</th>
                    <th>Perfumer</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Cancel</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (cartProducts != null && !cartProducts.isEmpty()) {
                    for (Cart item : cartProducts) { %>
                        <tr>
                            <td>
                                <img src="product-images/<%= item.getImage() %>" alt="<%= item.getTitle() %>" class="thumbnail">
                            </td>
                            <td><%= item.getTitle() %></td>
                            <td><%= item.getPerfumer() %></td>
                            <td>$<%= String.format("%.2f", item.getPrice()) %></td>
                            <td>
                                <div class="quantity-wrapper">
                                    <a href="quantity-inc-dec?action=dec&id=<%= item.getProductId() %>" class="btn btn-sm btn-decre">-</a>
                                    <input type="text" class="form-control quantity-input" value="<%= item.getQuantity() %>" readonly>
                                    <a href="quantity-inc-dec?action=inc&id=<%= item.getProductId() %>" class="btn btn-sm btn-incre">+</a>
                                </div>
                            </td>
                            <td>
                                <form action="remove-from-cart" method="post">
                                    <input type="hidden" name="productId" value="<%= item.getProductId() %>" />
                                    <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                                </form>
                            </td>
                        </tr>
                <%  }
                } else { %>
                    <tr>
                        <td colspan="6" class="text-center">Your cart is empty.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <div class="summary-container">
            <div class="summary-row">
                <span>Subtotal:</span>
                <span>$<%= String.format("%.2f", subtotal) %></span>
            </div>
            <div class="summary-row">
                <span>Tax (13%):</span>
                <span>$<%= String.format("%.2f", tax) %></span>
            </div>
            <div class="summary-row">
                <strong>Total:</strong>
                <strong>$<%= String.format("%.2f", total) %></strong>
            </div>
            <form action="checkout" method="post">
                <button type="submit" class="checkout-btn">Checkout</button>
            </form>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</body>
</html>
