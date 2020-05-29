<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="scripts/index.js"></script>
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
    <button class="navbar-toggler" data-toggle="collapse" data-target="#Menu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="Menu">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" id="registerLink">
                <a href="index.jsp" class="nav-link active">Register</a>
            </li>
            <li class="nav-item" id="loginLink">
                <a href="login.jsp" class="nav-link">Login</a>
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

<form name="registerForm" method="post" action="account" id="registerForm">
    <div class="container">
        <h2 class="center">Create an account</h2>
        <hr>

        <label for="firstName" class="required"><b>First Name</b></label>
        <input pattern="[a-zA-Z -]+" type="text" placeholder="Enter First Name" name="firstName" id="firstName" required title="Alphabets only">

        <label for="lastName" class="required"><b>Last Name</b></label>
        <input pattern="[a-zA-Z -]+" type="text" placeholder="Enter Last Name" name="lastName" id="lastName" required title="Alphabets only">

        <label for="email" class="required"><b>Email</b></label>
        <input type="text" pattern="^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$"
               placeholder="Enter Email" name="email" id="email" required title="Email pattern needed">

        <label for="password" class="required"><b>Password</b></label>
        <input pattern=".{5,12}" type="password" placeholder="Enter Password" name="password" id="password" required title="Length has to be from 5 to 12 characters">

        <label for="discountType" class = "required"> <b>I am: </b></label>
        <br>
        <select id="discountType" name="type" class="select">
            <option value="Adult">Adult</option>
            <option value="Child">Child</option>
            <option value="Student">Student</option>
            <option value="Pensioner">Pensioner</option>
        </select>

        <hr>

        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin center">
        <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
    </div>
</form>
</body>
</html>
