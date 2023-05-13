async function fetchDataAndRender() {
    console.log('Fetching data...');
    try {

        // Get the authCode from localStorage
        const authCode = localStorage.getItem('authCode');

        // the endpoint
        const response = await axios.get('https://your-api-endpoint-here.com/data', {
            headers: {
                // Include the authCode in the Authorization header
                'Authorization': `Bearer ${authCode}`
            }
        });

        // Assuming the response data is an array of objects
        const data = response.data;

        // Get the table body element
        const tableBody = document.querySelector('.table tbody');

        // Clear the current table content
        tableBody.innerHTML = '';

        // Iterate through the data array and create table rows
        data.forEach(item => {
            const row = document.createElement('tr');

            // Add the table cells with the data
            row.innerHTML = `
          <td>${item.ticketNumber}</td>
          <td>${item.citizenName}</td>
          <td>${item.citizenSSN}</td>
          <td>${item.request}</td>
          <td>${item.date}</td>
          <td>
            <select class="form-control">
              <option ${item.state === 'Received' ? 'selected' : ''}>Received</option>
              <option ${item.state === 'Accepted' ? 'selected' : ''}>Accepted</option>
              <option ${item.state === 'Refused' ? 'selected' : ''}>Refused</option>
            </select>
          </td>
          <td><button class="btn btn-primary">Download</button></td>
        `;

            // Append the row to the table body
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching data', error);
    }
}
axios.defaults.timeout = 5000; // 5 second timeout
// document.addEventListener('DOMContentLoaded', fetchDataAndRender);
