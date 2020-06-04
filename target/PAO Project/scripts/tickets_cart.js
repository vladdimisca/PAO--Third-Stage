window.onload = function() {
    let logged = JSON.parse(localStorage.getItem('logged'));

    if(logged)
        if(logged === 1) {
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";

            getTickets();
        }
}

function getTickets() {
    let email = localStorage.getItem('email');

    const xHttpGet = new XMLHttpRequest();

    xHttpGet.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.response);

            let container = document.getElementsByClassName('container')[0];
            let paragraph = document.getElementById('cartMessage');
            paragraph.innerText = 'Your cart is empty.';

            let ticketsDiv = document.createElement('div');
            ticketsDiv.classList.add("tickets");

            container.appendChild(ticketsDiv);

            response.forEach(function (ticket) {
                if (paragraph)
                    paragraph.style.display = "none";

                let ticketDiv = document.createElement('div');
                ticketDiv.classList.add("ticket");

                let removeButton = document.createElement('i');
                removeButton.className += "fa fa-close center";
                removeButton.style.fontSize = "20px";
                removeButton.style.color = "red";
                removeButton.style.cursor = "pointer";
                removeButton.setAttribute('id', ticket['ticketId']);

                let namesDiv = document.createElement('div');
                namesDiv.className += "div1 center";

                let eventName = document.createElement('h3');
                eventName.innerHTML = ticket['showName'];

                let hallName = document.createElement('h5');
                hallName.innerHTML = ticket['roomName'];

                let date = document.createElement('p');
                date.innerHTML = ticket['eventDate'];

                namesDiv.appendChild(eventName);
                namesDiv.appendChild(hallName);
                namesDiv.appendChild(date);

                let priceDiv = document.createElement('div');
                priceDiv.className += "div2 center";

                let price = document.createElement('h5');
                price.innerHTML = "$" + ticket['price'];

                let discountType = document.createElement('p');
                discountType.innerHTML = ticket['discount'];

                priceDiv.appendChild(price);
                priceDiv.appendChild(discountType);

                let seatDiv = document.createElement('div');
                seatDiv.className += "div3 center";

                let row = document.createElement('p');
                row.innerHTML = "Row: " + ticket['row'];

                let column = document.createElement('p');
                column.innerHTML = "Column: " + ticket['column'];

                seatDiv.appendChild(row);
                seatDiv.appendChild(column);

                ticketDiv.appendChild(namesDiv);
                ticketDiv.appendChild(priceDiv);
                ticketDiv.appendChild(seatDiv);
                ticketDiv.appendChild(removeButton);

                ticketsDiv.appendChild(ticketDiv);

                let br = document.createElement('br');

                ticketsDiv.appendChild(br);

                removeButton.onclick = () => removeTicket(removeButton, ticketDiv, br);
            });
        }
    };

    xHttpGet.open("GET", "tickets?email=" + email, true);
    xHttpGet.send();
}

function removeTicket(removeButton) {
    const xHttpDelete = new XMLHttpRequest();

    xHttpDelete.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.response);

            if(response.hasOwnProperty('success')) {
                window.location.replace("tickets_cart.jsp");
            }
        }
    };

    xHttpDelete.open("DELETE", "tickets?ticketId=" + removeButton.id , true);
    xHttpDelete.send();
}