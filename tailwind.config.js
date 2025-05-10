module.exports = {
    content: [
      './*.html',   // all HTML files in root
      './js/**/*.js'// all JS files in js/ folder
    ],
    theme: {
      extend: {
        colors: {
          primary: 'var(--color-primary)',
          secondary: 'var(--color-secondary)',
          neutral: 'var(--color-neutral)',
        }
      }
    },
    plugins: [],
  }