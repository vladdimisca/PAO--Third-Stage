window.onload = function () {
    let logged = JSON.parse(localStorage.getItem('logged'));

    if(logged)
        if(logged === 1) {
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";
        }

    const xHttpGet = new XMLHttpRequest();

    xHttpGet.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.response);

            let selectTag = document.getElementById("chooseEvent");

            response.forEach(function (event) {
                let newOption = document.createElement('option');
                selectTag.appendChild(newOption);

                newOption.setAttribute("id", event['eventId']);
                newOption.value = "Event: " + event["showName"] + ", Location: " + event["roomName"] + ", Date: " +
                    event["eventDate"] + ", $" + event["price"];

                newOption.innerHTML = newOption.value;
            });
        }
    };

    xHttpGet.open("GET", "events", true);
    xHttpGet.send();

    let buyButton = document.getElementById("buyButton");

    buyButton.onclick = function (event) {
        event.preventDefault();

        addTicket(buyButton);
    }
}

function addTicket(buyButton) {
    let p = document.getElementById('message');

    if(p !== null) {
        p.parentNode.removeChild(p);
    }

    p = document.createElement('p');
    p.setAttribute('id', 'message');

    let d = document.createElement('div');


    p.style.display = 'block';

    let email = localStorage.getItem('email');

    if(email) {
        let select = document.getElementById("chooseEvent");

        let eventId = select.options[select.selectedIndex].id;

        const xHttpAdd = new XMLHttpRequest();

        xHttpAdd.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const response = JSON.parse(this.response);

                if(response.hasOwnProperty("success")) {
                    p.innerHTML = response['success'];
                } else {
                    p.innerHTML = response['failure'];
                }
            }
        };

        xHttpAdd.open("POST", "tickets?email=" + email + "&eventId=" + eventId, true);
        xHttpAdd.send();
    } else {
        p.innerHTML = "You must be logged in";
    }

    d.appendChild(p);
    buyButton.parentNode.insertBefore(d, buyButton);

    setTimeout(function() {

        d.setAttribute('id', 'messages--delete');

        p.addEventListener('transitionend', function() {
            p.parentNode.removeChild(p);
        });
    }, 2000);

}