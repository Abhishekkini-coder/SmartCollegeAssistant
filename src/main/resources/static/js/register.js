const registerForm = document.getElementById('registerForm');
if (registerForm) {
  registerForm.addEventListener('submit', async e => {
    e.preventDefault();
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    try {
      const res = await fetch('/students/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, password })
      });
      if (!res.ok) {
        const err = await res.json();
        alert(err.message || 'Registration failed');
        return;
      }
      alert('Registration successful! Please log in.');
      window.location.href = 'login.html';
    } catch (err) {
      console.error(err);
      alert('Server error. Please try again later.');
    }
  });
}
