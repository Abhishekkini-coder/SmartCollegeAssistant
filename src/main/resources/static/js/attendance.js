const currentUserId = 1;

function loadAttendance() {
  const tbody       = document.querySelector('#attendance-table tbody');
  const chartCanvas = document.getElementById('attendance-chart');
  if (!tbody || !chartCanvas) return;

  fetch(`/attendance/student/${currentUserId}`)
    .then(res => res.json())
    .then(data => {
      // data = [ { id, subject, date, status, student: { id, … } }, … ]

      // 1. Group records by subject
      const bySubject = data.reduce((acc, rec) => {
        const subj = rec.subject;
        if (!acc[subj]) acc[subj] = [];
        acc[subj].push(rec);
        return acc;
      }, {});

      // 2. Compute % present per subject
      const labels = [];
      const values = [];

      tbody.innerHTML = '';  // clear table

      Object.entries(bySubject).forEach(([subject, recs]) => {
        const total   = recs.length;
        const present = recs.filter(r => r.status.toLowerCase() === 'present').length;
        const pct     = total > 0 ? Math.round((present * 100) / total) : 0;

        // populate table
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td class="p-3 font-medium">${subject}</td>
          <td class="p-3 font-semibold text-[var(--color-primary)]">${pct}%</td>
        `;
        tbody.appendChild(tr);

        // collect for chart
        labels.push(subject);
        values.push(pct);
      });

      // 3. Render the chart
      // (use canvas element directly, and ensure it has width/height set in HTML)
      new Chart(chartCanvas, {
        type: 'bar',
        data: {
          labels,
          datasets: [{
            label: 'Attendance %',
            data: values,
            // you can keep or remove these colors
            backgroundColor: 'rgba(0, 166, 153, 0.7)',
            borderColor:     'rgba(0, 166, 153, 1)',
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
