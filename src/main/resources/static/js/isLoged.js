(() => {
  if (localStorage.getItem('user') === null) {
    window.location.assign('http://localhost:8080/login.html')
  }

  document.querySelectorAll('.btn-logout').forEach((btn) => {
    btn.addEventListener('click', () => {
      localStorage.removeItem('user');
      return window.location.assign('http://localhost:8080/login.html');
    })
  })
})();