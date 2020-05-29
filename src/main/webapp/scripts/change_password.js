window.onload = function () {
    let email = localStorage.getItem('email');
    let changePass = document.getElementById('changeForm');

    if(changePass){
        changePass.onsubmit = (event) => updatePassword(event, email);
    }
}

function updatePassword (event, email) {
    event.preventDefault()
    let oldPassword = document.querySelector('input[id=oldpsw]');
    let newPassword = document.querySelector('input[id=newpsw]');

    const xHttpUpdate = new XMLHttpRequest();

    xHttpUpdate.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.response);

            if (response.hasOwnProperty('success')) {
                localStorage.setItem('logged', '0');
                window.location.replace('login.jsp?message=' + response.success);

            } else {
                if (!document.getElementById('message')) {
                    let message = document.createElement('h4');
                    message.setAttribute('id', 'message');
                    newPassword.parentNode.insertBefore(message, newPassword.nextSibling);
                    message.innerHTML = response['failure'];
                }
            }
        }
    };

    xHttpUpdate.open("OPTIONS", "account?email=" + email + "&oldPassword=" + oldPassword.value + "&newPassword=" +
                newPassword.value, true);
    xHttpUpdate.send();
}