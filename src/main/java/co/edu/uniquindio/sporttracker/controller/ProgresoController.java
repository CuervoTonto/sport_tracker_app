package co.edu.uniquindio.sporttracker.controller;

import co.edu.uniquindio.sporttracker.dao.ProgresoDAO;
import co.edu.uniquindio.sporttracker.model.Progreso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresos")
@CrossOrigin(origins = "*")
public class ProgresoController {

    @Autowired
    private ProgresoDAO progresoDAO;

    // --- CREATE (Crear) ---
    // URL: POST http://localhost:8080/api/progresos
    @PostMapping
    public ResponseEntity<Progreso> crearProgreso(@Valid @RequestBody Progreso progreso) {
        Progreso nuevoProgreso = progresoDAO.save(progreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProgreso);
    }

    // --- READ (Leer Todos O Filtrar por Usuario) ---
    // URL: GET http://localhost:8080/api/progresos
    // URL: GET http://localhost:8080/api/progresos?usuario=username_del_usuario
    @GetMapping
    public List<Progreso> listarProgresos(@RequestParam(required = false) String usuario) {
        if (usuario != null && !usuario.isEmpty()) {
            // Devuelve solo el progreso de un usuario específico
            return progresoDAO.findAllByUsuario(usuario);
        }
        // Devuelve todo el progreso
        return progresoDAO.findAll();
    }

    // --- READ (Leer Uno por ID) ---
    // URL: GET http://localhost:8080/api/progresos/123
    @GetMapping("/{id}")
    public ResponseEntity<Progreso> obtenerProgresoPorId(@PathVariable Long id) {
        return progresoDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- UPDATE (Actualizar) ---
    // URL: PUT http://localhost:8080/api/progresos/123
    @PutMapping("/{id}")
    public ResponseEntity<Progreso> actualizarProgreso(@PathVariable Long id, @Valid @RequestBody Progreso progresoActualizado) {
        if (!progresoDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Progreso guardado = progresoDAO.update(id, progresoActualizado);
        return ResponseEntity.ok(guardado);
    }

    // --- DELETE (Eliminar) ---
    // URL: DELETE http://localhost:8080/api/progresos/123
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProgreso(@PathVariable Long id) {
        if (!progresoDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        progresoDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
