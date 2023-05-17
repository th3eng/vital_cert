const passwordField = document.getElementById('password');
const showPasswordBtn = document.getElementById('showPasswordBtn');

showPasswordBtn.addEventListener('click', () => {
  if (passwordField.type === 'password') {
    passwordField.type = 'text';
    showPasswordBtn.innerHTML = '<i class="fas fa-eye-slash"></i>'; // Font Awesome eye-slash icon
  } else {
    passwordField.type = 'password';
    showPasswordBtn.innerHTML = '<i class="fas fa-eye"></i>'; // Font Awesome eye icon
  }
});

// sign in validation
const formLogin = document.getElementById('loginForm');
const usernameField = document.getElementById('username');
const usernameError = document.getElementById('usernameError');
const passwordError = document.getElementById('passwordError');

document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm');
  loginForm.addEventListener('submit', (event) => {
    event.preventDefault();
    handleLoginFormSubmit();
  });
});

async function handleLoginFormSubmit() {
  const usernameInput = document.getElementById('username');
  const passwordInput = document.getElementById('password');

  const username = usernameInput.value.trim();
  const password = passwordInput.value.trim();

  if (!username || !password) {
    alert('Please fill in all fields');
    return;
  }

    // Reset error messages
    usernameError.textContent = '';
    passwordError.textContent = '';
  
    const usernameRegex = /^\d{14}$/; // Phone or SSN regex
  
    if (!usernameRegex.test(username)) {
      usernameError.textContent = 'Please enter a valid phone number or SSN';
      usernameField.classList.add('is-invalid');
      return;
    }
  
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/; // Password regex
  
    if (!passwordRegex.test(password)) {
      passwordError.textContent = 'Weak password';
      passwordField.classList.add('is-invalid');
      return;
    }

  try {
    // Replace the URL with the endpoint for logging in
    const response = await axios.post('http://localhost:8080/api/v1/employee/auth/authenticate', 
    {
      "ssn": username,
      password: password
    }
    );
    console.log(response);
    // Get the auth token from the response
    const authToken = response.data.token;

    // Store the auth token in localStorage
    localStorage.setItem('authToken', authToken);

    // Redirect to the dashboard or another page after successful login
    window.location.href = '../dashboard/citizen/index.html';
  } catch (error) {
    console.error('Error logging in', error);
    // Show an error message (customize this part as needed)
    alert('Invalid credentials. Please try again.');
  }
}