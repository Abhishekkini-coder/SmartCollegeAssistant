const loginForm = document.getElementById('loginForm');
if (loginForm) {
  loginForm.addEventListener('submit', async e => {
    e.preventDefault();
    const email = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
      const res = await fetch('/students/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
      });
      if (!res.ok) {
        const err = await res.json();
        alert(err.message || 'Login failed');
        return;
      }
      const data = await res.json();
      // Store token or user info as needed
      localStorage.setItem('token', data.token);
      window.location.href = 'student-dashboard.html';
    } catch (err) {
      console.error(err);
      alert('Server error. Please try again later.');
    }
  });
}
