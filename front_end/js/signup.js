const passwordField = document.getElementById('password');
const confPasswordField = document.getElementById('confirmPassword');
const showPasswordBtn = document.getElementById('showPasswordBtn');
const showConfPasswordBtn = document.getElementById('showConfPasswordBtn');

showPasswordBtn.addEventListener('click', () => {
  if (passwordField.type === 'password') {
    passwordField.type = 'text';
    showPasswordBtn.innerHTML = '<i class="fas fa-eye-slash"></i>'; // Font Awesome eye-slash icon
  } else {
    passwordField.type = 'password';
    showPasswordBtn.innerHTML = '<i class="fas fa-eye"></i>'; // Font Awesome eye icon
  }
});
showConfPasswordBtn.addEventListener('click', () => {
    if (confPasswordField.type === 'password') {
      confPasswordField.type = 'text';
      showConfPasswordBtn.innerHTML = '<i class="fas fa-eye-slash"></i>'; // Font Awesome eye-slash icon
    } else {
      confPasswordField.type = 'password';
      showConfPasswordBtn.innerHTML = '<i class="fas fa-eye"></i>'; // Font Awesome eye icon
    }
  });
  
const formReg = document.getElementById('registrationForm');
const ssnField = document.getElementById('SSN');
const ssnError = document.getElementById('SSNError');
const phoneField = document.getElementById('phone');
const phoneError = document.getElementById('phoneError');
const confPasswordError = document.getElementById('confirmPassword');

formReg.addEventListener('submit', (e) => {
  e.preventDefault(); // Prevent form submission

  // Reset error messages
  ssnError.textContent = '';
  phoneError.textContent = '';
  passwordError.textContent = '';
  confPasswordError.textContent = '';

  // Validate SSN to be 14 digits
  const ssn = ssnField.value.trim();
  const ssnRegex = /^\d{14}$/; // SSN regex

  if (!ssnRegex.test(ssn)) {
    ssnError.textContent = 'Please enter a valid SSN';
    ssnField.classList.add('is-invalid');
    return;
  }

  // Validate phone number to be 11 digits and starts with 01
  const phone = phoneField.value.trim();
  const phoneRegex = /^01\d{9}$/; // Phone regex

  if (!phoneRegex.test(phone)) {
    phoneError.textContent = 'Please enter a valid phone number';
    phoneField.classList.add('is-invalid');
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

  // Validate confirm password
  const confPassword = confPasswordField.value.trim();

  if (confPassword !== password) {
    confPasswordError.textContent = 'Passwords do not match';
    confPasswordField.classList.add('is-invalid');
    return;
  }

  // Form is valid, can proceed with submission
  formReg.submit();
});

