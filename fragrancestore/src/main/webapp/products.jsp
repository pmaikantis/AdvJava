<%@page import= "java.util.List" %>
<%@page import= "com.fragrancestore.connection.DbCon" %>
<%@page import= "fragrancestore.dao.ProductDao" %>
<%@page import="com.fragrancestore.model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Products - Athena's Apothecary</title>
    <%@include file="includes/head.jsp" %>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            height: 100%;
            max-width: 300px;
            margin: auto;
        }
        .card-img-top {
            height: 250px;
            object-fit: contain;
            width: 100%;
            background-color: #f8f9fa;
            padding: 10px;
        }
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .container {
            padding-top: 20px;
        }
        .row {
            justify-content: center;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    
    <%
    
        String popupMessage = (String) session.getAttribute("popupMessage");
        if (popupMessage != null) {
            session.removeAttribute("popupMessage"); 
        }
    %>
    
    <!-- Display the popup message -->
    <script>
        <% if (popupMessage != null) { %>
            alert("<%= popupMessage %>");
        <% } %>
    </script>

    <div class="container">
        <h1 class="text-center mt-5">Our Products</h1>
        <div class="row product-container">
            <%

                ProductDao productDao = new ProductDao(DbCon.getConnection());
                List<Product> products = productDao.getAllProducts();

                for (Product product : products) {
                    String formattedPrice = String.format("%.2f", product.getPrice());
            %>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="product-images/<%= product.getImage() %>" class="card-img-top" alt="<%= product.getTitle() %>">
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getTitle() %></h5>
                            <p><strong>Category:</strong> <%= product.getCategory() %></p>
                            <p><strong>Perfumer:</strong> <%= product.getPerfumer() %></p>
                            <p><strong>Description:</strong> <%= product.getDescription() %></p>
                            <p><strong>Notes:</strong> <%= product.getNotes() %></p>
                            <p><strong>Price:</strong> $<%= formattedPrice %></p>
                            <p><strong>Release Date:</strong> <%= product.getFormattedReleaseDate() %></p>
                            <!-- Modified Add to Cart button -->
                            <a href="add-to-cart?id=<%= product.getFragranceId() %>&redirect=products" class="btn btn-dark">Add to Cart</a>
                        </div>
                    </div>
                </div>
            <%
                }
            %>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <%@include file="includes/footer.jsp" %>
</body>
</html>
