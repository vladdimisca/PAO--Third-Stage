<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="scripts/login.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
    <button class="navbar-toggler" data-toggle="collapse" data-target="#Menu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="Menu">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" id="registerLink">
                <a href="index.jsp" class="nav-link">Register</a>
            </li>
            <li class="nav-item" id="loginLink">
                <a href="login.jsp" class="nav-link active">Login</a>
            </li>
            <li class="nav-item">
                <a href="profile.jsp" class="nav-link">Account</a>
            </li>
            <li class="nav-item">
                <a href="buy_tickets.jsp" class="nav-link">Book tickets</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="tickets_cart.jsp" class="nav-link">Booked Tickets</a>
            </li>
        </ul>
    </div>
</nav>

<form name="loginForm" action="account" id="loginForm">
    <div class="container">

        <h2 class="center">Sign in to your account</h2>
        <hr>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" id="email" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>

        <hr>
        <button type="submit" class="registerbtn">Sign in</button>
    </div>

    <div class="container signin center">
        <p>Don't have an account? <a href="index.jsp">Register</a>.</p>
    </div>
</form>
</body>
</html>
