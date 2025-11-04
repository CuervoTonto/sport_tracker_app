// Espera a que el DOM (la página HTML) esté completamente cargado
document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Constantes y Variables Globales ---
    const API_URL = 'http://localhost:8080/api/usuarios';
    const form = document.getElementById('usuario-form');
    const tableBody = document.getElementById('tabla-usuarios-body');
    const formTitle = document.getElementById('form-title');
    const btnGuardar = document.getElementById('btn-guardar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const inputUsername = document.getElementById('username');
    const inputContrasenia = document.getElementById('contrasenia');
    const contraseniaHint = document.getElementById('contrasenia-hint');
    
    let isEditMode = false;
    let editUsername = null;

    // --- 2. Función Principal: Cargar Usuarios ---
    async function cargarUsuarios() {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Error al cargar usuarios');
            const usuarios = await response.json();
            
            tableBody.innerHTML = ''; // Limpiar la tabla
            
            if (usuarios.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="4" class="px-6 py-4 text-center">No hay usuarios para mostrar.</td></tr>`;
            } else {
                usuarios.forEach(usuario => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm font-medium text-gray-900">${usuario.username}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-900">${usuario.primerNombre} ${usuario.primerApellido}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${usuario.correo}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <button data-username="${usuario.username}" class="btn-editar text-blue-600 hover:text-blue-900 mr-4">Editar</button>
                            <button data-username="${usuario.username}" class="btn-eliminar text-red-600 hover:text-red-900">Eliminar</button>
                        </td>
                    `;
                    tableBody.appendChild(tr);
                });
            }
        } catch (error) {
            console.error(error);
        }
    }

    // --- 3. Función para resetear el formulario ---
    function resetFormulario() {
        form.reset();
        isEditMode = false;
        editUsername = null;
        formTitle.textContent = 'Crear Nuevo Usuario';
        btnGuardar.textContent = 'Crear Usuario';
        inputUsername.readOnly = false;
        btnCancelar.style.display = 'none';
        contraseniaHint.style.display = 'none';
        inputContrasenia.placeholder = '********';
    }

    // --- 4. Event Listener para el Formulario (Crear o Actualizar) ---
    form.addEventListener('submit', async (e) => {
        e.preventDefault(); // Evitar que la página se recargue

        // Obtenemos los datos del formulario
        const formData = new FormData(form);
        const usuario = Object.fromEntries(formData.entries());

        // Campos opcionales (si están vacíos, enviar null)
        usuario.segundoNombre = usuario.segundoNombre || null;
        usuario.segundoApellido = usuario.segundoApellido || null;

        try {
            let response;
            if (isEditMode) {
                // --- MODO ACTUALIZAR (PUT) ---
                response = await fetch(`${API_URL}/${editUsername}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(usuario)
                });
            } else {
                // --- MODO CREAR (POST) ---
                response = await fetch(API_URL, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(usuario)
                });
            }

            if (!response.ok) {
                if (response.status === 409) {
                    alert('Error: El username ya existe.');
                } else if (response.status === 400) {
                    alert('Error 400: Datos Inválidos. Asegúrate de que la contraseña tenga 8+ caracteres.');
                } else {
                    alert('Error al guardar el usuario.');
                }
                throw new Error('Error al guardar');
            }

            resetFormulario();
            cargarUsuarios();

        } catch (error) {
            console.error(error);
        }
    });

    // --- 5. Event Listener para la Tabla (Botones Editar y Eliminar) ---
    tableBody.addEventListener('click', async (e) => {
        const username = e.target.dataset.username;

        // --- Clic en ELIMINAR ---
        if (e.target.classList.contains('btn-eliminar')) {
            if (confirm(`¿Estás seguro de que quieres eliminar al usuario ${username}?`)) {
                try {
                    const response = await fetch(`${API_URL}/${username}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Error al eliminar');
                    cargarUsuarios();
                } catch (error) {
                    console.error(error);
                }
            }
        }

        // --- Clic en EDITAR ---
        if (e.target.classList.contains('btn-editar')) {
            try {
                // 1. Obtener los datos del usuario
                const response = await fetch(`${API_URL}/${username}`);
                if (!response.ok) throw new Error('Usuario no encontrado');
                const usuario = await response.json();

                // 2. Rellenar el formulario
                form.username.value = usuario.username;
                form.primerNombre.value = usuario.primerNombre;
                form.primerApellido.value = usuario.primerApellido;
                form.correo.value = usuario.correo;
                form.celular.value = usuario.celular;
                form.fechaNacimiento.value = usuario.fechaNacimiento;
                // No rellenamos la contraseña
                
                // 3. Poner en Modo Edición
                isEditMode = true;
                editUsername = usuario.username;
                formTitle.textContent = `Editando Usuario: ${usuario.username}`;
                btnGuardar.textContent = 'Actualizar Usuario';
                inputUsername.readOnly = true;
                btnCancelar.style.display = 'block';
                contraseniaHint.style.display = 'block';
                inputContrasenia.placeholder = 'Dejar vacío para no cambiar';

            } catch (error) {
                console.error(error);
            }
        }
    });
    
    // --- 6. Event Listener para el botón Cancelar ---
    btnCancelar.addEventListener('click', resetFormulario);

    // --- 7. Carga Inicial de Datos ---
    cargarUsuarios();
});