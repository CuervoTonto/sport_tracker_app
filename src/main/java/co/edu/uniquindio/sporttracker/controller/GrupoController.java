package co.edu.uniquindio.sporttracker.controller;

import co.edu.uniquindio.sporttracker.dao.GrupoDAO;
import co.edu.uniquindio.sporttracker.model.Grupo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoDAO grupoDAO;

    // --- CREATE (Crear) ---
    // URL: POST http://localhost:8080/api/grupos
    @PostMapping
    public ResponseEntity<Grupo> crearGrupo(@Valid @RequestBody Grupo grupo) {
        if (grupoDAO.existsById(grupo.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // Asignamos la fecha de creación en el servidor
        grupo.setFechaCreacion(LocalDateTime.now());
        
        Grupo nuevoGrupo = grupoDAO.save(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGrupo);
    }

    // --- READ (Leer Todos) ---
    // URL: GET http://localhost:8080/api/grupos
    @GetMapping
    public List<Grupo> listarGrupos() {
        return grupoDAO.findAll();
    }

    // --- READ (Leer Uno) ---
    // URL: GET http://localhost:8080/api/grupos/un-nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<Grupo> obtenerGrupoPorNombre(@PathVariable String nombre) {
        return grupoDAO.findById(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- UPDATE (Actualizar) ---
    // URL: PUT http://localhost:8080/api/grupos/un-nombre
    @PutMapping("/{nombre}")
    public ResponseEntity<Grupo> actualizarGrupo(@PathVariable String nombre, @Valid @RequestBody Grupo grupoActualizado) {
        if (!grupoDAO.existsById(nombre)) {
            return ResponseEntity.notFound().build();
        }
        // Nos aseguramos de que el nombre (PK) sea el de la URL
        grupoActualizado.setNombre(nombre); 
        Grupo guardado = grupoDAO.update(nombre, grupoActualizado);
        return ResponseEntity.ok(guardado);
    }

    // --- DELETE (Eliminar) ---
    // URL: DELETE http://localhost:8080/api/grupos/un-nombre
    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> eliminarGrupo(@PathVariable String nombre) {
        if (!grupoDAO.existsById(nombre)) {
            return ResponseEntity.notFound().build();
        }
        grupoDAO.deleteById(nombre);
        return ResponseEntity.noContent().build();
    }
}
