document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Constantes y Variables ---
    const API_URL = 'http://localhost:8080/api/metas';
    const USUARIOS_API_URL = 'http://localhost:8080/api/usuarios'; // <-- API de Usuarios
    
    const form = document.getElementById('meta-form');
    const tableBody = document.getElementById('tabla-metas-body');
    const formTitle = document.getElementById('form-title');
    const btnGuardar = document.getElementById('btn-guardar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const selectUsuario = document.getElementById('usuarioUsername'); // <-- Dropdown de Usuario
    
    let isEditMode = false;
    let editId = null; // <-- Usamos ID (numérico) en lugar de un string

    // --- NUEVA FUNCIÓN: Cargar Usuarios en el Dropdown ---
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

    // --- 2. Función Principal: Cargar Metas ---
    async function cargarMetas() {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Error al cargar metas');
            const metas = await response.json();
            
            tableBody.innerHTML = ''; 
            
            if (metas.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="5" class="px-6 py-4 text-center">No hay metas para mostrar.</td></tr>`;
            } else {
                metas.forEach(meta => {
                    const tr = document.createElement('tr');
                    //  Campos de la tabla ---
                    tr.innerHTML = `
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm font-medium text-gray-900">${meta.id}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${meta.usuarioUsername}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-900">${meta.kilometrosActuales} / ${meta.kilometrosObjetivos} KM</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${meta.fechaInicio} al ${meta.fechaFin}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <button data-id="${meta.id}" class="btn-editar text-blue-600 hover:text-blue-900 mr-4">Editar</button>
                            <button data-id="${meta.id}" class="btn-eliminar text-red-600 hover:text-red-900">Eliminar</button>
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
        formTitle.textContent = 'Nueva Meta';
        btnGuardar.textContent = 'Crear Meta';
        selectUsuario.disabled = false; // Habilitar el dropdown de usuario
        btnCancelar.style.display = 'none';
        
        // Ponemos el valor por defecto de 'kilometrosActuales'
        form.kilometrosActuales.value = 0;
    }

    // --- 4. Event Listener para el Formulario (Crear o Actualizar) ---
    form.addEventListener('submit', async (e) => {
        e.preventDefault(); 
        const formData = new FormData(form);
        const meta = Object.fromEntries(formData.entries());

        try {
            let response;
            if (isEditMode) {
                // --- MODO ACTUALIZAR (PUT) ---
                response = await fetch(`${API_URL}/${editId}`, { // <-- Usa editId
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(meta)
                });
            } else {
                // --- MODO CREAR (POST) ---
                response = await fetch(API_URL, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(meta)
                });
            }

            if (!response.ok) {
                alert('Error 400: Datos Inválidos. Asegúrate de llenar todos los campos.');
                throw new Error('Error al guardar');
            }

            resetFormulario();
            cargarMetas();

        } catch (error) {
            console.error(error);
        }
    });

    // --- 5. Event Listener para la Tabla (Botones Editar y Eliminar) ---
    tableBody.addEventListener('click', async (e) => {
        const id = e.target.dataset.id; // <-- CAMBIO: data-id
        if (!id) return; // Si no hay id, no hacer nada

        // --- Clic en ELIMINAR ---
        if (e.target.classList.contains('btn-eliminar')) {
            if (confirm(`¿Estás seguro de que quieres eliminar la meta con ID ${id}?`)) {
                try {
                    const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Error al eliminar');
                    cargarMetas();
                } catch (error) {
                    console.error(error);
                }
            }
        }

        // --- Clic en EDITAR ---
        if (e.target.classList.contains('btn-editar')) {
            try {
                // 1. Obtener los datos de la meta
                const response = await fetch(`${API_URL}/${id}`);
                if (!response.ok) throw new Error('Meta no encontrada');
                const meta = await response.json();

                // 2. Rellenar el formulario
                form.usuarioUsername.value = meta.usuarioUsername;
                form.kilometrosObjetivos.value = meta.kilometrosObjetivos;
                form.kilometrosActuales.value = meta.kilometrosActuales;
                form.fechaInicio.value = meta.fechaInicio;
                form.fechaFin.value = meta.fechaFin;
                
                // 3. Poner en Modo Edición
                isEditMode = true;
                editId = meta.id; // <-- Usa editId
                formTitle.textContent = `Editando Meta: ${meta.id}`;
                btnGuardar.textContent = 'Actualizar Meta';
                selectUsuario.disabled = true; // No dejamos cambiar el usuario de una meta
                btnCancelar.style.display = 'block';

            } catch (error) {
                console.error(error);
            }
        }
    });
    
    // --- 6. Event Listener para el botón Cancelar ---
    btnCancelar.addEventListener('click', resetFormulario);

    // --- 7. Carga Inicial de Datos ---
    cargarMetas();
    cargarUsuarios(); // <-- ¡LLAMAMOS A LAS DOS FUNCIONES DE CARGA!
});