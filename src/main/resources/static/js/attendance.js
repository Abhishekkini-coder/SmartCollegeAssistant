// Replace this with your real user‐ID (e.g. injected server‐side or from auth token)
const currentUserId = 1;

function loadAttendance() {
  const tbody        = document.querySelector('#attendance-table tbody');
  const chartCanvas  = document.getElementById('attendance-chart');
  if (!tbody || !chartCanvas) return;

  fetch(`/attendance/student/${currentUserId}/attendance-percentage`)
    .then(res => res.json())
    .then(data => {
      // data = [ { subject: 'Math', attendance: 85 }, ... ]
      tbody.innerHTML = '';
      const labels = [];
      const values = [];

      data.forEach(item => {
        // Populate table
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td class="p-3 font-medium">${item.subject}</td>
          <td class="p-3 font-semibold text-[var(--color-primary)]">${item.attendance}%</td>
        `;
        tbody.appendChild(tr);

        // Collect for chart
        labels.push(item.subject);
        values.push(item.attendance);
      });

      // Render bar chart
      new Chart(chartCanvas.getContext('2d'), {
        type: 'bar',
        data: {
          labels,
          datasets: [{
            label: 'Attendance %',
            data: values,
            backgroundColor: 'rgba(0, 166, 153, 0.7)',
            borderColor: 'rgba(0, 166, 153, 1)',
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: { beginAtZero: true, max: 100 }
          }
        }
      });
    })
    .catch(err => console.error('Error loading attendance:', err));
}

document.addEventListener('DOMContentLoaded', loadAttendance);
