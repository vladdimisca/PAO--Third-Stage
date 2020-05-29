<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="scripts/deletion.js"></script>
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
                <a href="login.jsp" class="nav-link">Login</a>
            </li>
            <li class="nav-item">
                <a href="profile.jsp" class="nav-link active">Account</a>
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

<form name="deletionForm" action="account" id="deletionForm">
    <div class="container">

        <h2 class="center">Confirm account deletion</h2>
        <hr>

        <label id="email"><b>Email: </b></label>
        <br>
        <br>

        <label for="psw" ><b>Password </b></label>
        <input type="password" placeholder="Confirm Password" name="psw" id="psw" required>

        <hr>
        <button type="submit" class="registerbtn">Confirm</button>
    </div>
</form>
</body>
</html>
