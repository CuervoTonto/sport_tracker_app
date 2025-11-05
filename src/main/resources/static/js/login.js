(() => {
  const API_URL = 'http://localhost:8080/api';
  const formLogin = document.getElementById('form-login');

  formLogin.onsubmit = (e) => {
    e.preventDefault()
    const formData = new FormData(formLogin);
    const datosLogin = Object.fromEntries(formData.entries());

    fetch(`${API_URL}/login`, {
      method: "POST",
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(datosLogin)
    }).then((res) => {
      if (res.status == 200) {
        res.json().then((data) => {
          localStorage.setItem('user', JSON.stringify(data));
          window.location.assign('http://localhost:8080/usuarios.html');
        });
      } else if (res.status == 400) {
        res.json().then((error) => {
          window.alert(`Error: ${error.errors.username}`)
        });
      } else {
        window.alert("Hay ocurrido un error en la API!");
      }

    }).catch((err) => {
      window.alert(err);
    })
  }
})();