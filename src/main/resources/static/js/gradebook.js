// TODO: Replace with actual logged-in user ID logic
const currentUserId = 1;

function loadGradebook() {
  const tbody = document.querySelector('#gradebook-table tbody');
  const chartCanvas = document.getElementById('gradebook-chart');
  if (!tbody || !chartCanvas) return;

  fetch(`/grades/student/${currentUserId}`)
    .then(res => res.json())
    .then(data => {
      tbody.innerHTML = '';
      const labels = [];
      const values = [];

      data.forEach(record => {
        // Get color class based on subject
        const subjectColor = getSubjectColor(record.subject);

        // Populate table
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td class="p-3 font-medium ${subjectColor}">${record.subject}</td>
          <td class="p-3">${record.assignmentName}</td>
          <td class="p-3 font-semibold text-[var(--color-secondary)]">${record.marksObtained}</td>
          <td class="p-3 text-gray-600">${record.feedback || ''}</td>
        `;
        tbody.appendChild(tr);

        // Prepare chart data
        labels.push(record.assignmentName);
        values.push(record.marksObtained);
      });

      // Render bar chart with Chart.js
      new Chart(chartCanvas.getContext('2d'), {
        type: 'bar',
        data: {
          labels,
          datasets: [{
            label: 'Marks Obtained',
            data: values,
            fill: false,
            tension: 0.3,
            backgroundColor: 'rgba(0, 166, 153, 0.6)',
            borderColor: 'rgba(0, 166, 153, 1)',
            borderWidth: 2
          }]
        },
        options: {
          plugins: {
            tooltip: { mode: 'index', intersect: false }
          },
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
    })
    .catch(err => console.error('Error loading grades:', err));
}

// Returns a Tailwind text color class based on the subject name
function getSubjectColor(subject) {
  switch(subject) {
    case "Mathematics": return "text-red-500";
    case "Physics": return "text-blue-500";
    case "Computer Science": return "text-green-600";
    case "English": return "text-purple-500";
    default: return "text-gray-700";
  }
}

document.addEventListener('DOMContentLoaded', loadGradebook);
