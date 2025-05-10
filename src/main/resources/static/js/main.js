// js/main.js

// ----------------------


// ----------------------
// REGISTER FORM HANDLER
// ----------------------
const registerForm = document.getElementById("registerForm");
if (registerForm) {
  registerForm.addEventListener("submit", function (e) {
    e.preventDefault();
    // … your fetch("/students/register") logic here …
  });
}

// ----------------------
// ATTENDANCE LOADER (used on attendance.html)
// ----------------------
function loadAttendance() {
  const attendanceTableBody = document.querySelector('#attendance-table tbody');
  const chartCanvas = document.getElementById('attendance-chart');
  if (!attendanceTableBody || !chartCanvas) return;

  fetch('/attendance/student/1')  // adjust ID as needed
    .then(res => res.json())
    .then(data => {
      // Populate table rows
      attendanceTableBody.innerHTML = '';
      const subjects = [], percentages = [];
      data.forEach(record => {
        const row = document.createElement('tr');
        row.innerHTML = `<td class="p-3">${record.subject}</td><td class="p-3">${record.attendance}%</td>`;
        attendanceTableBody.appendChild(row);
        subjects.push(record.subject);
        percentages.push(record.attendance);
      });

      // Render Chart.js bar chart
      const ctx = chartCanvas.getContext('2d');
      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: subjects,
          datasets: [{
            label: 'Attendance %',
            data: percentages,
            backgroundColor: 'rgba(54, 162, 235, 0.7)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
          }]
        },
        options: { scales: { y: { beginAtZero: true, max: 100 } } }
      });
    })
    .catch(err => console.error('Error loading attendance:', err));
}

// Automatically run loadAttendance() on pages where the table & chart exist
document.addEventListener('DOMContentLoaded', loadAttendance);
