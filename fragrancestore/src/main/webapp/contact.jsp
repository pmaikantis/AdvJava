<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Contact Us</title>
<%@include file="includes/head.jsp" %>
<script>

    function validateForm(event) {
        event.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const phone = document.getElementById("phone").value.trim();
        const message = document.getElementById("message").value.trim();

        let hasError = false;
        let errorMessage = "";

        if (!name) {
            errorMessage += "Name is required. ";
            hasError = true;
        }
        if (!email) {
            errorMessage += "Email is required. ";
            hasError = true;
        }
        if (!phone) {
            errorMessage += "Phone Number is required. ";
            hasError = true;
        }
        if (!message) {
            errorMessage += "Message is required.";
            hasError = true;
        }

        if (hasError) {
            alert(errorMessage);
        } else {
            alert("Your inquiry has been submitted.");
            document.getElementById("contactForm").reset(); 
        }
    }
</script>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container" style="margin-top: 50px;">
    <h1 class="text-center">Contact Us</h1>
    <form id="contactForm" onsubmit="validateForm(event)" style="margin-top: 30px;">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone Number</label>
            <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter your phone number">
        </div>
        <div class="mb-3">
            <label for="message" class="form-label">Message</label>
            <textarea class="form-control" id="message" name="message" rows="4" placeholder="Write your message here"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>
