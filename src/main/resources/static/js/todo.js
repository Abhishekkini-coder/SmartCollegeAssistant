// TODO: replace with real auth/session logic
const currentUserId = 1;
let progressChart; // hold Chart.js instance

// Initialize on DOM ready
document.addEventListener('DOMContentLoaded', () => {
  loadTasks();
});

// Fetch tasks, then render list and chart
function loadTasks() {
  fetch(`http://localhost:8080/todos/student/${currentUserId}`)
    .then(res => {
      if (!res.ok) throw new Error(`Server returned ${res.status}`);
      return res.json();
    })
    .then(tasks => {
      renderTasks(tasks);
      renderProgressChart(tasks);
    })
    .catch(err => console.error('Error loading tasks:', err));
}

// Render the task list
function renderTasks(tasks) {
  const list = document.getElementById('task-list');
  list.innerHTML = '';

  if (!tasks.length) {
    list.innerHTML = '<li class="text-gray-500">No tasks yet. Add one above!</li>';
    return;
  }

  tasks.forEach(task => {
    const li = document.createElement('li');
    li.className = 'flex items-center justify-between p-4 border rounded-lg';

    // Title span
    const span = document.createElement('span');
    span.textContent = task.title;
    span.className = 'ml-3 flex-1 ' +
      (task.completed ? 'line-through text-gray-400' : 'text-gray-800');

    // Checkbox
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = task.completed;
    // Pass the span so we can toggle classes in-place
    checkbox.onchange = () => toggleTask(task.id, checkbox.checked, span);

    // Delete button
    const btn = document.createElement('button');
    btn.textContent = '🗑️';
    btn.onclick = () => deleteTask(task.id);

    li.append(checkbox, span, btn);
    list.appendChild(li);
  });
}

// Render the completion progress pie chart
function renderProgressChart(tasks) {
  const completedCount = tasks.filter(t => t.completed).length;
  const pendingCount   = tasks.length - completedCount;
  const ctx = document.getElementById('progress-chart').getContext('2d');

  // Destroy existing chart if present
  if (progressChart) {
    progressChart.destroy();
  }

  progressChart = new Chart(ctx, {
    type: 'pie',
    data: {
      labels: ['Completed', 'Pending'],
      datasets: [{
        data: [completedCount, pendingCount],
        backgroundColor: [
          'rgba(0, 166, 153, 0.8)',  // greenish
          'rgba(255, 90, 95, 0.8)'   // reddish
        ]
      }]
    },
    options: {
      plugins: {
        tooltip: {
          callbacks: {
            label: ctx => `${ctx.label}: ${ctx.parsed} task${ctx.parsed !== 1 ? 's' : ''}`
          }
        },
        legend: { position: 'bottom' }
      }
    }
  });
}

// Add a new task
function addTask() {
  const input = document.getElementById('new-task');
  const title = input.value.trim();
  if (!title) return;

  fetch('http://localhost:8080/todos/add', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      title,
      description: '',
      completed: false,
      student: { id: currentUserId }
    })
  })
    .then(res => {
      if (!res.ok) throw new Error(`Server returned ${res.status}`);
      return res.json();
    })
    .then(() => {
      input.value = '';
      loadTasks();
    })
    .catch(err => console.error('Error adding task:', err));
}

// Toggle a task’s completion in-place (strikethrough)
function toggleTask(id, completed, span) {
  fetch(`http://localhost:8080/todos/update/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ completed })
  })
  .then(res => {
    if (!res.ok) throw new Error(`Server returned ${res.status}`);
    // Toggle classes on the span
    span.classList.toggle('line-through', completed);
    span.classList.toggle('text-gray-400', completed);
    span.classList.toggle('text-gray-800', !completed);
    // Refresh chart only
    return fetch(`http://localhost:8080/todos/student/${currentUserId}`);
  })
  .then(res => res.json())
  .then(tasks => renderProgressChart(tasks))
  .catch(err => console.error('Error updating task:', err));
}

// Delete a task
function deleteTask(id) {
  fetch(`http://localhost:8080/todos/delete/${id}`, {
    method: 'DELETE'
  })
    .then(res => {
      if (!res.ok) throw new Error(`Server returned ${res.status}`);
      loadTasks();
    })
    .catch(err => console.error('Error deleting task:', err));
}
