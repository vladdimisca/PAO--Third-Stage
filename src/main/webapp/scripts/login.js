window.onload = function () {
    document.getElementById('loginForm').onsubmit = function (event) {
        event.preventDefault();

        let email = document.getElementById('email');
        let password = document.getElementById('password');

        const xHttpLogin = new XMLHttpRequest();

        xHttpLogin.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const response = JSON.parse(this.response);

                if(response.hasOwnProperty('success')) {
                    login(email.value);
                    window.location.replace('profile.jsp');
                } else {
                    if (response.hasOwnProperty('failure') && !document.getElementById('message')) {
                        let message = document.createElement('h4');
                        message.setAttribute('id', 'message');
                        message.innerHTML = response.failure;

                        password.parentNode.insertBefore(message, password.nextSibling);
                    } else {
                        message.innerHTML = response['failure'];
                    }
                }
            }
        };

        xHttpLogin.open("PUT", "account?email=" + email.value + "&password=" + password.value, true);
        xHttpLogin.send();
    };
};

function login(email) {
    localStorage.setItem('logged', '1');
    localStorage.setItem('email', email);
}