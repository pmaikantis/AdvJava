<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <!-- Brand -->
    <a class="navbar-brand" href="index.jsp">Athena's Apothecary</a>


    <button 
      class="navbar-toggler" 
      type="button" 
      data-bs-toggle="collapse" 
      data-bs-target="#navbarSupportedContent" 
      aria-controls="navbarSupportedContent" 
      aria-expanded="false" 
      aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <!-- Home Link -->
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>


        <li class="nav-item">
          <a class="nav-link" href="products.jsp">Products</a>
        </li>
        
		<li class="nav-item">
          <a class="nav-link" href="about.jsp">About Us</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="contact.jsp">Contact Us</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="cart.jsp">Cart</a>
        </li>

        <% if (session.getAttribute("auth") != null) { %>
          <li class="nav-item">
            <a class="nav-link" href="orders.jsp">Orders</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="log-out">Logout</a>
          </li>
        <% } else { %>
          <li class="nav-item">
            <a class="nav-link" href="login.jsp">Login</a>
          </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
