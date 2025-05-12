const loginForm = document.getElementById('loginForm');
if (loginForm) {
  loginForm.addEventListener('submit', async e => {
    e.preventDefault();
    const email = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value; // Get student/teacher

    try {
      const res = await fetch(`/login?role=${role}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
      });

      if (!res.ok) {
        const text = await res.text();
        alert(text || 'Login failed');
        return;
      }

      // Redirect to respective dashboard
      if (role === 'student') {
        window.location.href = 'student-dashboard.html';
      } else {
        window.location.href = 'teacher-dashboard.html';
      }

    } catch (err) {
      console.error(err);
      alert('Server error. Please try again later.');
    }
  });
}
