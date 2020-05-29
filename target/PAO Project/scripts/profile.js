window.onload = function () {
    let logged = JSON.parse(localStorage.getItem('logged'));

    if(logged)
        if(logged === 1) {
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";
            document.getElementById("profileMessage").style.display = "none";

            document.getElementById("hidden").style.display = "block";

            let email = localStorage.getItem('email');

            const xHttpProfile = new XMLHttpRequest();

            xHttpProfile.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    const response = JSON.parse(this.response);

                    let firstName = document.createElement("h4");
                    let lastName = document.createElement("h4");
                    let em = document.createElement("h4");
                    let discountType = document.createElement('h4');

                    firstName.innerHTML = 'First Name: ' + response['firstName'];
                    lastName.innerHTML = 'Last Name: ' + response['lastName'];
                    em.innerHTML = 'Email: ' + response['email'];
                    discountType.innerHTML = response['discountType'];

                    let detailsDiv = document.getElementById('logged');
                    detailsDiv.appendChild(firstName);
                    detailsDiv.appendChild(lastName);
                    detailsDiv.appendChild(em);
                    detailsDiv.appendChild(discountType);
                }
            };

            xHttpProfile.open("GET", "account?email=" + email, true);
            xHttpProfile.send();
        }
};

function logout() {
    localStorage.setItem('logged', '0');
    localStorage.removeItem('email');
    location.replace("login.jsp");
}

function deletionPage() {
    location.replace("deletion_page.jsp");
}

function changePassword() {
    location.replace("change_password.jsp");
}