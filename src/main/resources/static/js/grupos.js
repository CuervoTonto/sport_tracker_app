document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Constantes y Variables ---
    const API_URL = 'http://localhost:8080/api/grupos';
    const USUARIOS_API_URL = 'http://localhost:8080/api/usuarios'; 
    
    const form = document.getElementById('grupo-form');
    const tableBody = document.getElementById('tabla-grupos-body');
    const formTitle = document.getElementById('form-title');
    const btnGuardar = document.getElementById('btn-guardar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const inputNombre = document.getElementById('nombre'); // <-- Campo de texto 'nombre'
    const selectAdministrador = document.getElementById('administrador'); // <-- Dropdown
    
    let isEditMode = false;
    let editNombre = null; // <-- La PK es 'nombre' (string)

    // --- Cargar Usuarios en el Dropdown (Función Auxiliar) ---
    async function cargarUsuarios() {
        try {
            const response = await fetch(USUARIOS_API_URL);
            if (!response.ok) throw new Error('Error al cargar usuarios');
            const usuarios = await response.json();

            selectAdministrador.innerHTML = '<option value="">-- Seleccione un administrador --</option>';

            usuarios.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.username; 
                option.textContent = `${usuario.primerNombre} ${usuario.primerApellido} (${usuario.username})`; 
                selectAdministrador.appendChild(option);
            });

        } catch (error) {
            console.error(error);
            selectAdministrador.innerHTML = '<option value="">Error al cargar usuarios</option>';
        }
    }

    // --- 2. Función Principal: Cargar Grupos ---
    async function cargarGrupos() {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Error al cargar grupos');
            const grupos = await response.json();
            
            tableBody.innerHTML = ''; 
            
            if (grupos.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="4" class="px-6 py-4 text-center">No hay grupos para mostrar.</td></tr>`;
            } else {
                grupos.forEach(grupo => {
                    const tr = document.createElement('tr');
                    
                    // Formatear la fecha (DATETIME)
                    const fecha = new Date(grupo.fechaCreacion);
                    const fechaFormateada = fecha.toLocaleString('es-CO', { 
                        year: 'numeric', month: '2-digit', day: '2-digit'
                    });

                    // --- CAMBIO: Campos de la tabla ---
                    tr.innerHTML = `
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm font-medium text-gray-900">${grupo.nombre}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${grupo.administrador}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${fechaFormateada}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <button data-nombre="${grupo.nombre}" class="btn-editar text-blue-600 hover:text-blue-900 mr-4">Editar</button>
                            <button data-nombre="${grupo.nombre}" class="btn-eliminar text-red-600 hover:text-red-900">Eliminar</button>
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
        editNombre = null;
        formTitle.textContent = 'Nuevo Grupo';
        btnGuardar.textContent = 'Crear Grupo';
        inputNombre.readOnly = false; // El nombre se puede editar al crear
        btnCancelar.style.display = 'none';
        selectAdministrador.value = ""; // Resetea el dropdown
    }

    // --- 4. Event Listener para el Formulario (Crear o Actualizar) ---
    form.addEventListener('submit', async (e) => {
        e.preventDefault(); 
        const formData = new FormData(form);
        const grupo = Object.fromEntries(formData.entries());

        // El backend (GrupoController) pone la fecha de creación, no necesitamos enviarla.

        try {
            let response;
            if (isEditMode) {
                // --- MODO ACTUALIZAR (PUT) ---
                response = await fetch(`${API_URL}/${editNombre}`, { // <-- Usa editNombre
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(grupo)
                });
            } else {
                // --- MODO CREAR (POST) ---
                response = await fetch(API_URL, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(grupo)
                });
            }

            if (!response.ok) {
                if (response.status === 409) {
                    alert('Error: Ya existe un grupo con ese nombre.');
                } else {
                    alert('Error 400: Datos Inválidos. Asegúrate de llenar todos los campos.');
                }
                throw new Error('Error al guardar');
            }

            resetFormulario();
            cargarGrupos();

        } catch (error) {
            console.error(error);
        }
    });

    // --- 5. Event Listener para la Tabla (Botones Editar y Eliminar) ---
    tableBody.addEventListener('click', async (e) => {
        const nombre = e.target.dataset.nombre; // <-- CAMBIO: data-nombre
        if (!nombre) return; 

        // --- Clic en ELIMINAR ---
        if (e.target.classList.contains('btn-eliminar')) {
            if (confirm(`¿Estás seguro de que quieres eliminar el grupo "${nombre}"?`)) {
                try {
                    const response = await fetch(`${API_URL}/${nombre}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Error al eliminar');
                    cargarGrupos();
                } catch (error) {
                    console.error(error);
                }
            }
        }

        // --- Clic en EDITAR ---
        if (e.target.classList.contains('btn-editar')) {
            try {
                // 1. Obtener los datos del grupo
                const response = await fetch(`${API_URL}/${nombre}`);
                if (!response.ok) throw new Error('Grupo no encontrado');
                const grupo = await response.json();

                // 2. Rellenar el formulario
                form.nombre.value = grupo.nombre;
                form.administrador.value = grupo.administrador;
                
                // 3. Poner en Modo Edición
                isEditMode = true;
                editNombre = grupo.nombre;
                formTitle.textContent = `Editando Grupo: ${grupo.nombre}`;
                btnGuardar.textContent = 'Actualizar Grupo';
                
                // El backend SOLO permite actualizar el admin, así que bloqueamos el nombre.
                inputNombre.readOnly = true; 
                
                btnCancelar.style.display = 'block';

            } catch (error) {
                console.error(error);
            }
        }
    });
    
    // --- 6. Event Listener para el botón Cancelar ---
    btnCancelar.addEventListener('click', resetFormulario);

    // --- 7. Carga Inicial de Datos ---
    cargarGrupos();
    cargarUsuarios(); 
});