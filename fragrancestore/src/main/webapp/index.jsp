<%@page import= "java.util.List" %>
<%@page import= "com.fragrancestore.connection.DbCon" %>
<%@page import= "fragrancestore.dao.ProductDao" %>
<%@page import="com.fragrancestore.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProducts();

    String popupMessage = (String) session.getAttribute("popupMessage");
    if (popupMessage != null) {
        session.removeAttribute("popupMessage");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Athena's Apothecary</title>
    <%@include file="includes/head.jsp" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <style>
        /* Carousel Customization */
        .carousel-item img {
            height: 400px;
            object-fit: contain;
            margin: 0 auto;
        }
        .carousel-caption {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px;
            border-radius: 5px;
        }
        /* Carousel Arrow Styling */
        .carousel-control-prev-icon, .carousel-control-next-icon {
            background-color: rgba(0, 0, 0, 0.5);
            border-radius: 50%;
            width: 50px;
            height: 50px;
        }
        /* Product Card Styling */
        .product-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            margin: 20px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            text-align: center;
            transition: transform 0.3s ease;
        }
        .product-card:hover {
            transform: scale(1.05);
        }
        .product-card img {
            width: 100%;
            height: 300px;
            object-fit: contain;
            border-radius: 10px;
        }
        .product-title {
            font-size: 1.5rem;
            font-weight: bold;
            margin-top: 15px;
        }
        .product-perfumer, .product-price {
            margin-top: 10px;
            font-size: 1.1rem;
        }
        .product-price {
            color: #28a745;
            font-weight: bold;
        }
        .add-to-cart-btn {
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>

    <!-- Display Popup Message -->
    <script>
        <% if (popupMessage != null) { %>
            alert("<%= popupMessage %>");
        <% } %>
    </script>

    <!-- Carousel Section -->
    <div id="carouselExampleDark" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <!-- Dynamic Product Slides -->
            <%
                int index = 0;
                for (Product product : products) {
                    String formattedPrice = String.format("%.2f", product.getPrice());
            %>
                <div class="carousel-item <%= index == 0 ? "active" : "" %>" data-bs-interval="10000">
                    <div class="text-center">
                        <!-- Image Section -->
                        <img src="product-images/<%= product.getImage() %>" class="d-block w-50 mx-auto" alt="<%= product.getTitle() %>">
                        <!-- Product Details -->
                        <div class="product-card mx-auto" style="max-width: 600px;">
                            <h3 class="product-title"><%= product.getTitle() %></h3>
                            <p class="product-perfumer"><strong>Perfumer:</strong> <%= product.getPerfumer() %></p>
                            <p class="product-price"><strong>Price:</strong> $<%= formattedPrice %></p>
                            <a href="add-to-cart?id=<%= product.getFragranceId() %>" class="btn btn-dark">Add to Cart</a>
                        </div>
                    </div>
                </div>
            <%
                    index++;
                }
            %>
        </div>

        <!-- Carousel Controls -->
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <%@include file="includes/footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
