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

formLogin.addEventListener('submit', async (e) => {
  e.preventDefault(); // Prevent form submission

  // Reset error messages
  usernameError.textContent = '';
  passwordError.textContent = '';

  // Validate username
  const username = usernameField.value.trim();
  const usernameRegex = /^(01\d{9}|\d{14})$/; // Phone or SSN regex

  if (!usernameRegex.test(username)) {
    usernameError.textContent = 'Please enter a valid phone number or SSN';
    usernameField.classList.add('is-invalid');
    return;
  }

  // Validate password
  const password = passwordField.value.trim();
  const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/; // Password regex

  if (!passwordRegex.test(password)) {
    passwordError.textContent = 'Weak password';
    passwordField.classList.add('is-invalid');
    return;
  }

  // Send login request
  const loginRes = await axios({
    method: 'POST',
    url: 'http://38.242.205.134:8080/auth',
    data: {
      username: e.target.username.value,
      password: e.target.password.value,
    }
  });

  console.log(loginRes);
});


