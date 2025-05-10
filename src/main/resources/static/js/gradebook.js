// TODO: Replace with actual logged-in user ID logic
const currentUserId = 1;

function loadGradebook() {
  const tbody       = document.querySelector('#gradebook-table tbody');
  const chartCanvas = document.getElementById('gradebook-chart');
  if (!tbody || !chartCanvas) return;

  fetch(`/grades/student/${currentUserId}`)
    .then(res => res.json())
    .then(data => {
      // data = [ { id, subject, assignmentName, marksObtained, feedback, student }, ... ]
      tbody.innerHTML = '';
      const labels = [];
      const values = [];

      data.forEach(record => {
        // Populate table
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td class="p-3 font-medium text-[var(--color-neutral)]">${record.subject}</td>
          <td class="p-3">${record.assignmentName}</td>
          <td class="p-3 font-semibold text-[var(--color-secondary)]">${record.marksObtained}</td>
          <td class="p-3 text-gray-600">${record.feedback || ''}</td>
        `;
        tbody.appendChild(tr);

        // Prepare for chart
        labels.push(record.assignmentName);
        values.push(record.marksObtained);
      });

      // Render Chart.js line chart
      new Chart(chartCanvas.getContext('2d'), {
        type: 'line',
        data: {
          labels,
          datasets: [{
            label: 'Marks Obtained',
            data: values,
            fill: false,
            tension: 0.3,
            borderColor: 'rgba(0, 166, 153, 0.8)',
            pointBackgroundColor: 'rgba(255, 90, 95, 0.8)',
            pointRadius: 5,
            borderWidth: 2
          }]
        },
        options: {
          plugins: { tooltip: { mode: 'index', intersect: false } },
          scales: { y: { beginAtZero: true } }
        }
      });
    })
    .catch(err => console.error('Error loading grades:', err));
}

document.addEventListener('DOMContentLoaded', () => {
  console.log('⚡️ [Debug] DOM loaded, about to fetch grades…');

  const studentPayload = { id: 1 };
  console.log('⚡️ [Debug] Payload →', studentPayload);

  fetch('http://localhost:8080/grades/student', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(studentPayload)
  })
  .then(res => {
    console.log(`⚡️ [Debug] Fetch responded with status ${res.status}`);
    if (!res.ok) throw new Error(`Server returned ${res.status}`);
    return res.json();
  })
  .then(data => {
    console.log('⚡️ [Debug] Raw data from server →', data);
    renderChart(data);
  })
  .catch(err => {
    console.error('❌ [Debug] Could not load grade data:', err);
    document.getElementById('performanceChart').innerText = 'Failed to load chart.';
  });
});

function renderChart(data) {
  // 2) Map your exact JSON fields
  const assignments = data.map(d => d.assignmentName);
  const marks       = data.map(d => d.marksObtained);

  console.log('⚡️ [Debug] assignments →', assignments);
  console.log('⚡️ [Debug] marks       →', marks);

  // 3) Build and draw the Plotly trace
  const trace = {
    x: assignments,
    y: marks,
    type: 'scatter',
    mode: 'lines+markers',
    hovertemplate: '%{x}: %{y} pts<extra></extra>'
  };

  const layout = {
    margin: { t: 30, r: 20, b: 50, l: 40 },
    xaxis: { title: 'Assignment', type: 'category' },
    yaxis: { title: 'Marks Obtained', range: [0, 100] },
    paper_bgcolor: 'rgba(0,0,0,0)',
    plot_bgcolor:  'rgba(0,0,0,0)'
  };
  console.log('Assignments →', assignments);
  console.log('Marks       →', marks);


  Plotly.newPlot('performanceChart', [trace], layout, { responsive: true });
}


document.addEventListener('DOMContentLoaded', loadGradebook);
