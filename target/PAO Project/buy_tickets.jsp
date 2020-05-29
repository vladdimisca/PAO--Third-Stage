<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy tickets</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="scripts/buy_tickets.js"></script>
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
                <a href="profile.jsp" class="nav-link">Account</a>
            </li>
            <li class="nav-item">
                <a href="buy_tickets.jsp" class="nav-link active">Book tickets</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="tickets_cart.jsp" class="nav-link">Booked Tickets</a>
            </li>
        </ul>
    </div>
</nav>

    <form name="ticketForm" method="post" action="account" id="ticketForm">
        <div class="container">
            <h2 class="center">Book a ticket</h2>
            <hr>

            <label for="chooseEvent" class = "required"> <b>Choose an event: </b></label>
            <br>
            <select id="chooseEvent" name="type" class="select"></select>

            <hr>

            <button type="submit" class="registerbtn" id="buyButton">Book ticket</button>
        </div>
    </form>
</body>
</html>
