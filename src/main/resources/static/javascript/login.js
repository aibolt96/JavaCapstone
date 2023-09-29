const loginForm = document.getElementById('login-form');
const loginUsername = document.getElementById('login-username');
const loginPassword = document.getElementById('login-password');

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080'

const handleSubmit = async (e) => {
    e.preventDefault();

    const username = loginUsername.value.trim();
    const password = loginPassword.value.trim();

    if (!username || !password) {
        alert('Username and password are required fields');
        return;
    }

    const bodyObj = {
        username: username,
        password: password,
    };

    const response = await fetch(`${baseUrl}/api/v1/users/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    });

    if (response.ok) {
        const responseArr = await response.json();

        if (responseArr.length >= 2 && responseArr[0] === 'http://localhost:8080/home.html') {
            window.location.replace(responseArr[0]);
        } else {
            alert('Username or password are incorrect.');
        }
    } else {
        console.error('Login failed: HTTP status ' + response.status);
    }
}

loginForm.addEventListener('submit', handleSubmit);