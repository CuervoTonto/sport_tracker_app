document.addEventListener('DOMContentLoaded', () => {

    // --- 1. Constantes y Variables ---
    const API_URL = 'http://localhost:8080/api/competencias';
    const CIUDADES_API_URL = 'http://localhost:8080/api/ciudades'; 
    
    const form = document.getElementById('competencia-form');
    const tableBody = document.getElementById('tabla-competencias-body');
    const formTitle = document.getElementById('form-title');
    const btnGuardar = document.getElementById('btn-guardar');
    const btnCancelar = document.getElementById('btn-cancelar');
    const inputTitulo = document.getElementById('titulo');
    const selectCiudad = document.getElementById('ciudadNombre'); 

    let isEditMode = false;
    let editTitulo = null;

    // --- Carga el dropdown de Ciudades ---
    async function cargarCiudades() {
        try {
            const response = await fetch(CIUDADES_API_URL);
            if (!response.ok) throw new Error('Error al cargar ciudades');
            const ciudades = await response.json();

            selectCiudad.innerHTML = '<option value="">-- Seleccione una ciudad --</option>';

            ciudades.forEach(ciudad => {
                const option = document.createElement('option');
                option.value = ciudad.nombre; 
                option.textContent = `${ciudad.nombre} (${ciudad.departamentoNombre})`; 
                selectCiudad.appendChild(option);
            });

        } catch (error) {
            console.error(error);
            selectCiudad.innerHTML = '<option value="">Error al cargar ciudades</option>';
        }
    }

    // --- Carga la tabla de Competencias ---
    async function cargarCompetencias() {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Error al cargar competencias');
            const competencias = await response.json(); 
            
            tableBody.innerHTML = ''; 
            
            if (competencias.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="4" class="px-6 py-4 text-center">No hay competencias para mostrar.</td></tr>`;
            } else {
                competencias.forEach(comp => { 
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm font-medium text-gray-900">${comp.titulo}</div>
                        </td>
                        <td class="px-6 py-4">
                            <div class="text-sm text-gray-700 truncate max-w-xs">${comp.descripcion}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-700">${comp.ciudadNombre || 'N/A'}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <button data-titulo="${comp.titulo}" class="btn-editar text-blue-600 hover:text-blue-900 mr-4">Editar</button>
                            <button data-titulo="${comp.titulo}" class="btn-eliminar text-red-600 hover:text-red-900">Eliminar</button>
                        </td>
                    `;
                    tableBody.appendChild(tr);
                });
            }
        } catch (error) {
            console.error(error);
        }
    }

    // --- Resetea el Formulario ---
    function resetFormulario() {
        form.reset();
        isEditMode = false;
        editTitulo = null;
        formTitle.textContent = 'Nueva Competencia';
        btnGuardar.textContent = 'Crear Competencia';
        inputTitulo.readOnly = false;
        btnCancelar.style.display = 'none';
        selectCiudad.value = ""; 
    }

    // --- Envía el Formulario (Crear o Actualizar) ---
    form.addEventListener('submit', async (e) => {
        e.preventDefault(); 
        const formData = new FormData(form);
        const competencia = Object.fromEntries(formData.entries());

        if (competencia.ciudadNombre === "") {
            competencia.ciudadNombre = null;
        }

        try {
            let response;
            if (isEditMode) {
                response = await fetch(`${API_URL}/${editTitulo}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(competencia)
                });
            } else {
                response = await fetch(API_URL, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(competencia)
                });
            }

            if (!response.ok) {
                if (response.status === 409) {
                    alert('Error: Ya existe una competencia con ese título.');
                } else if (response.status === 400) {
                    alert('Error 400: Datos Inválidos. Asegúrate de llenar todos los campos.');
                } else {
                    alert('Error al guardar la competencia.');
                }
                throw new Error('Error al guardar');
            }
            resetFormulario();
            cargarCompetencias();
        } catch (error) {
            console.error(error);
        }
    });

    // ================================================================
    // --- ¡ESTE ES EL BLOQUE QUE PROBABLEMENTE FALTABA! ---
    // Event Listener para los botones de la TABLA
    // ================================================================
    tableBody.addEventListener('click', async (e) => {
        // Obtenemos el título del atributo 'data-titulo' del botón
        const titulo = e.target.dataset.titulo; 
        if (!titulo) return; // Si hicieron clic en otra parte

        // --- Clic en ELIMINAR ---
        if (e.target.classList.contains('btn-eliminar')) {
            if (confirm(`¿Estás seguro de que quieres eliminar la competencia "${titulo}"?`)) {
                try {
                    const response = await fetch(`${API_URL}/${titulo}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Error al eliminar');
                    cargarCompetencias(); 
                } catch (error) {
                    console.error(error);
                }
            }
        }

        // --- Clic en EDITAR ---
        if (e.target.classList.contains('btn-editar')) {
            try {
                // 1. Obtener los datos de la competencia
                const response = await fetch(`${API_URL}/${titulo}`);
                if (!response.ok) throw new Error('Competencia no encontrada');
                const competencia = await response.json();

                // 2. Rellenar el formulario
                form.titulo.value = competencia.titulo;
                form.descripcion.value = competencia.descripcion;
                form.ciudadNombre.value = competencia.ciudadNombre || ""; 
                
                // 3. Poner en Modo Edición
                isEditMode = true;
                editTitulo = competencia.titulo; 
                formTitle.textContent = `Editando Competencia: ${competencia.titulo}`;
                btnGuardar.textContent = 'Actualizar Competencia';
                inputTitulo.readOnly = true; 
                btnCancelar.style.display = 'block';

                // Mover la vista al formulario
                window.scrollTo({ top: 0, behavior: 'smooth' });

            } catch (error) {
                console.error(error);
            }
        }
    });
    
    // --- Event Listener para el botón Cancelar ---
    btnCancelar.addEventListener('click', resetFormulario);

    // --- Carga Inicial de Datos ---
    cargarCompetencias();
    cargarCiudades();
});