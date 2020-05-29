window.onload = function () {
    let logged = JSON.parse(localStorage.getItem('logged'));

    if(logged)
        if(logged === 1) {
            window.location.replace("profile.jsp");
        }

    document.getElementById('registerForm').onsubmit = function (event) {
        event.preventDefault();

        let firstName = document.getElementById('firstName');
        let lastName = document.getElementById('lastName');
        let email = document.getElementById('email');
        let password = document.getElementById('password');
        let discountType = document.getElementById('discountType');

        const xHttpRegister = new XMLHttpRequest();

        xHttpRegister.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const response = JSON.parse(this.response);

                if(response.hasOwnProperty('success')) {
                    window.location.replace('login.jsp');
                } else {
                    if (response.hasOwnProperty('failure') && !document.getElementById('message')) {
                        let message = document.createElement('h4');
                        message.setAttribute('id', 'message');
                        message.innerHTML = response.failure;

                        discountType.parentNode.insertBefore(message, discountType.nextSibling);
                    }
                }
            }
        };

        xHttpRegister.open("POST", "account?firstName=" + firstName.value + "&lastName=" + lastName.value +
                    "&email=" + email.value + "&password=" + password.value + "&discountType=" + discountType.value, true);
        xHttpRegister.send();
    };
};