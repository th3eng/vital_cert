axios.defaults.timeout = 5000; // 5 second timeout

// get employees
async function fetchEmployeeDataAndRender() {
  try {
    // Get the authCode from localStorage
    const authCode = localStorage.getItem('authCode');

    // Replace the URL with the endpoint you want to fetch data from
    const response = await axios.get('https://your-api-endpoint-here.com/employees', {
      headers: {
        // Include the authCode in the Authorization header
        'Authorization': `Bearer ${authCode}`
      }
    });

    // Assuming the response data is an array of objects
    const data = response.data;

    // Get the table body element
    const tableBody = document.getElementById('employees');

    // Clear the current table content
    tableBody.innerHTML = '';

    // Iterate through the data array and create table rows
    data.forEach(item => {
      const row = document.createElement('tr');

      // Add the table cells with the data
      row.innerHTML = `
        <td>${item.ssn}</td>
        <td>${item.firstName}</td>
        <td>${item.lastName}</td>
        <td><button class="btn btn-danger btn-sm removeBtn">Remove</button></td>
      `;

      // Append the row to the table body
      tableBody.appendChild(row);
    });

    // Attach click event listeners to the remove buttons
    attachEmployeeRemoveListeners();
  } catch (error) {
    console.error('Error fetching data', error);
  }
}

// remove employee
function attachRemoveListeners() {
  const removeBtns = document.querySelectorAll('.removeBtn');
  removeBtns.forEach(btn => {
    btn.addEventListener('click', async (event) => {
      const row = event.target.closest('tr');
      const ssn = row.querySelector('td:first-child').textContent;
      try {
        console.log('User clicked OK');
        const authCode = localStorage.getItem('authCode');
        await axios.delete(`https://your-api-endpoint-here.com/citizens/${ssn}`, {
          headers: {
            'Authorization': `Bearer ${authCode}`
          }
        });
        row.remove();
      } catch (error) {
        alert('Error removing employee');
      }
    });
  });
}

document.addEventListener('DOMContentLoaded', fetchEmployeeDataAndRender);
document.addEventListener('DOMContentLoaded', attachRemoveListeners);

// add employee
async function handleAddEmployee() {
  const ssnInput = document.getElementById('ssn');
  const passwordInput = document.getElementById('password');

  const ssn = ssnInput.value.trim();
  const password = passwordInput.value.trim();

  if (!ssn || !password) {
    alert('Please fill in all fields');
    return;
  }

  try {
    // Replace the URL with the endpoint for adding a new employee
    const authCode = localStorage.getItem('authCode');
    await axios.post('https://your-api-endpoint-here.com/employees', {
      ssn, password
    }, {
      headers: {
        'Authorization': `Bearer ${authCode}`
      }
    });

    // Clear the inputs
    ssnInput.value = '';
    passwordInput.value = '';

    // Close the modal
    $('#addEmployeeModal').modal('hide');

    // Refresh the employee table
    fetchEmployeeDataAndRender();
  } catch (error) {
    console.error('Error adding employee', error);
  }
}
document.addEventListener('DOMContentLoaded', () => {
  const submitBtn = document.querySelector('#addEmployeeModal .btn-primary');
  submitBtn.addEventListener('click', handleAddEmployee);
});

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

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function onlyNumbers(evt) {
  var charCode = (evt.which) ? evt.which : event.keyCode;
  if (charCode != 46 && charCode > 31
    && (charCode < 48 || charCode > 57))
    return false;
  return true;
}