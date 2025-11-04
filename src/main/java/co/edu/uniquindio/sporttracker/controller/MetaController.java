package co.edu.uniquindio.sporttracker.controller;

import co.edu.uniquindio.sporttracker.dao.MetaDAO;
import co.edu.uniquindio.sporttracker.model.Meta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
@CrossOrigin(origins = "*")
public class MetaController {

    @Autowired
    private MetaDAO metaDAO;

    // --- CREATE (Crear) ---
    // URL: POST http://localhost:8080/api/metas
    @PostMapping
    public ResponseEntity<Meta> crearMeta(@Valid @RequestBody Meta meta) {
        // No necesitamos comprobar si existe, ya que el ID es auto-generado
        Meta nuevaMeta = metaDAO.save(meta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMeta);
    }

    // --- READ (Leer Todas O Filtrar por Usuario) ---
    // URL: GET http://localhost:8080/api/metas
    // URL: GET http://localhost:8080/api/metas?usuario=username_del_usuario
    @GetMapping
    public List<Meta> listarMetas(@RequestParam(required = false) String usuario) {
        if (usuario != null && !usuario.isEmpty()) {
            return metaDAO.findAllByUsuario(usuario);
        }
        return metaDAO.findAll();
    }

    // --- READ (Leer Una por ID) ---
    // URL: GET http://localhost:8080/api/metas/123
    @GetMapping("/{id}")
    public ResponseEntity<Meta> obtenerMetaPorId(@PathVariable Long id) {
        return metaDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- UPDATE (Actualizar) ---
    // URL: PUT http://localhost:8080/api/metas/123
    @PutMapping("/{id}")
    public ResponseEntity<Meta> actualizarMeta(@PathVariable Long id, @Valid @RequestBody Meta metaActualizada) {
        if (!metaDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Meta guardada = metaDAO.update(id, metaActualizada);
        return ResponseEntity.ok(guardada);
    }

    // --- DELETE (Eliminar) ---
    // URL: DELETE http://localhost:8080/api/metas/123
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMeta(@PathVariable Long id) {
        if (!metaDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        metaDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}