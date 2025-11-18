document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Constantes y Variables ---
    const API_URL = 'http://localhost:8080/api/progresos';
    const USUARIOS_API_URL = 'http://localhost:8080/api/usuarios'; 
    
    const form = document.getElementById('progreso-form');
    const tableBody = document.getElementById('tabla-progresos-body');
    const formTitle = document.getElementById('form-title');
    const btnGuardar = document.getElementById('btn-guardar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const selectUsuario = document.getElementById('usuarioUsername'); 
    
    let isEditMode = false;
    let editId = null; 

    // --- Cargar Usuarios en el Dropdown (Función Auxiliar) ---
    async function cargarUsuarios() {
        try {
            const response = await fetch(USUARIOS_API_URL);
            if (!response.ok) throw new Error('Error al cargar usuarios');
            const usuarios = await response.json();

            selectUsuario.innerHTML = '<option value="">-- Seleccione un usuario --</option>';

            usuarios.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.username; 
                option.textContent = `${usuario.primerNombre} ${usuario.primerApellido} (${usuario.username})`; 
                selectUsuario.appendChild(option);
            });

        } catch (error) {
            console.error(error);
            selectUsuario.innerHTML = '<option value="">Error al cargar usuarios</option>';
        }
    }

    // --- 2. Función Principal: Cargar Progresos ---
    async function cargarProgresos() {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Error al cargar progresos');
            const progresos = await response.json();
            
            tableBody.innerHTML = ''; 
            
            if (progresos.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="5" class="px-6 py-4 text-center">No hay progresos para mostrar.</td></tr>`;
            } else {
                progresos.forEach(progreso => {
                    const tr = document.createElement('tr');
                    
                    // Formatear la fecha (DATETIME) para que sea legible
                    const fecha = new Date(progreso.fecha);
                    const fechaFormateada = fecha.toLocaleString('es-CO', { 
                        year: 'numeric', month: '2-digit', day: '2-digit', 
                        hour: '2-digit', minute: '2-digit' 
                    });

                    // --- CAMBIO: Campos de la tabla ---
                    tr.innerHTML = `
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm font-medium text-gray-900">${progreso.id}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${progreso.usuarioUsername}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-900">${progreso.kilometrosRecorridos} KM</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${fechaFormateada}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <button data-id="${progreso.id}" class="btn-editar text-blue-600 hover:text-blue-900 mr-4">Editar</button>
                            <button data-id="${progreso.id}" class="btn-eliminar text-red-600 hover:text-red-900">Eliminar</button>
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
        editId = null;
        formTitle.textContent = 'Nuevo Progreso';
        btnGuardar.textContent = 'Crear Progreso';
        selectUsuario.disabled = false; 
        btnCancelar.style.display = 'none';
    }

    // --- 4. Event Listener para el Formulario (Crear o Actualizar) ---
    form.addEventListener('submit', async (e) => {
        e.preventDefault(); 
        const formData = new FormData(form);
        const progreso = Object.fromEntries(formData.entries());

        // El <input type="datetime-local"> ya da el formato YYYY-MM-DDTHH:MM
        // que Spring Boot [LocalDateTime] puede entender.

        try {
            let response;
            if (isEditMode) {
                // --- MODO ACTUALIZAR (PUT) ---
                response = await fetch(`${API_URL}/${editId}`, { 
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(progreso)
                });
            } else {
                // --- MODO CREAR (POST) ---
                response = await fetch(API_URL, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(progreso)
                });
            }

            if (!response.ok) {
                alert('Error 400: Datos Inválidos. Asegúrate de llenar todos los campos.');
                throw new Error('Error al guardar');
            }

            resetFormulario();
            cargarProgresos();

        } catch (error) {
            console.error(error);
        }
    });

    // --- 5. Event Listener para la Tabla (Botones Editar y Eliminar) ---
    tableBody.addEventListener('click', async (e) => {
        const id = e.target.dataset.id;
        if (!id) return; 

        // --- Clic en ELIMINAR ---
        if (e.target.classList.contains('btn-eliminar')) {
            if (confirm(`¿Estás seguro de que quieres eliminar el progreso con ID ${id}?`)) {
                try {
                    const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Error al eliminar');
                    cargarProgresos();
                } catch (error) {
                    console.error(error);
                }
            }
        }

        // --- Clic en EDITAR ---
        if (e.target.classList.contains('btn-editar')) {
            try {
                // 1. Obtener los datos del progreso
                const response = await fetch(`${API_URL}/${id}`);
                if (!response.ok) throw new Error('Progreso no encontrado');
                const progreso = await response.json();

                // 2. Rellenar el formulario
                form.usuarioUsername.value = progreso.usuarioUsername;
                form.kilometrosRecorridos.value = progreso.kilometrosRecorridos;
                // El formato de fecha del backend (LocalDateTime) es compatible con el input
                form.fecha.value = progreso.fecha; 
                
                // 3. Poner en Modo Edición
                isEditMode = true;
                editId = progreso.id;
                formTitle.textContent = `Editando Progreso: ${progreso.id}`;
                btnGuardar.textContent = 'Actualizar Progreso';
                selectUsuario.disabled = true; // No dejamos cambiar el usuario
                btnCancelar.style.display = 'block';

            } catch (error) {
                console.error(error);
            }
        }
    });
    
    // --- 6. Event Listener para el botón Cancelar ---
    btnCancelar.addEventListener('click', resetFormulario);

    // --- 7. Carga Inicial de Datos ---
    cargarProgresos();
    cargarUsuarios(); 
});