axios.defaults.timeout = 5000; // 5 second timeout
// console.log(localStorage.getItem('authCode'));
// When page loads, get list of citizens and render 
axios.get('/citizens')
  .then(res => {
    let citizens = res.data
    citizens.forEach(citizen => {
      document.querySelector('#citizens').innerHTML += `
        <tr>
          <td>${citizen.ssn}</td>
          <td>${citizen.first_name}</td>
          <td>${citizen.last_name}</td>
          <td><button class="btn btn-danger btn-sm removeBtn">Remove</button></td>
        </tr>
      `
    })
  })
  .catch(err => {
    console.log(err);
    if (err.code === 'ECONNABORTED') {
      alert('Request timeout. Please try again.');
    }
    else {
      alert(`Error ${err.code}\n${err.message}`);
    }
  })

// Add citizen submit form
document.querySelector('#addCitizenModal').addEventListener('submit', e => {
  e.preventDefault()
  axios.post('/citizens', { /* form data */ })
    .then(res => {
      // Close modal, get updated citizen list
    })
    .catch(err => {
      if (err.code === 'ECONNABORTED') {
        alert('Request timeout. Please try again.');
      }
    })
})

document.querySelectorAll('.removeBtn').forEach(button => {
  button.addEventListener('click', () => {
    axios.delete(`/citizens/${button.parentElement.querySelector('td').innerText}`)
      .then(res => {
        button.parentElement.remove() // Remove row from table
      })
  })
})

document.querySelector('#showPwd').addEventListener('click', () => {
  let passwordInput = document.querySelector('#password');
  if (passwordInput.type === 'password') {
    passwordInput.type = 'text';
  } else {
    passwordInput.type = 'password';
  }
})

document.querySelector('#genPwdBtn').addEventListener('click', () => {
  document.querySelector('#password').value = generatePassword();
})

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function generatePassword() {
  const length = getRandomInt(8, 12);
  let password = "";
  let hasCapital = false;
  let hasSmall = false;
  let hasDigit = false;

  while (password.length < length) {
    const charCode = getRandomInt(48, 122);

    if (48 <= charCode && charCode <= 57) { // digits
      hasDigit = true;
    } else if (65 <= charCode && charCode <= 90) { // capital letters
      hasCapital = true;
    } else if (97 <= charCode && charCode <= 122) { // small letters
      hasSmall = true;
    } else {
      continue; // skip non-required characters
    }

    password += String.fromCharCode(charCode);
  }

  // If any required character type is missing, regenerate the password
  if (!hasCapital || !hasSmall || !hasDigit) {
    return generatePassword();
  }

  return password;
}

function onlyNumbers(evt) {
  var charCode = (evt.which) ? evt.which : event.keyCode;
  if (charCode != 46 && charCode > 31
    && (charCode < 48 || charCode > 57))
    return false;
  return true;
}