package co.edu.uniquindio.sporttracker.controller;

import co.edu.uniquindio.sporttracker.dao.CompetenciaDAO;
import co.edu.uniquindio.sporttracker.model.Competencia;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competencias")
@CrossOrigin(origins = "*") // Permite que Angular (que correrá en otro puerto) llame a esta API
public class CompetenciaController {

    @Autowired
    private CompetenciaDAO competenciaDAO;

    // --- CREATE (Crear) ---
    // URL: POST http://localhost:8080/api/competencias
    @PostMapping
    public ResponseEntity<Competencia> crearCompetencia(@Valid @RequestBody Competencia competencia) {
        if (competenciaDAO.existsById(competencia.getTitulo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Competencia nuevaCompetencia = competenciaDAO.save(competencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompetencia);
    }

    // --- READ (Leer Todas) ---
    // URL: GET http://localhost:8080/api/competencias
    @GetMapping
    public List<Competencia> listarCompetencias() {
        return competenciaDAO.findAll();
    }

    // --- READ (Leer Una) ---
    // URL: GET http://localhost:8080/api/competencias/un-titulo
    @GetMapping("/{titulo}")
    public ResponseEntity<Competencia> obtenerCompetenciaPorTitulo(@PathVariable String titulo) {
        return competenciaDAO.findById(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- UPDATE (Actualizar) ---
    // URL: PUT http://localhost:8080/api/competencias/un-titulo
    @PutMapping("/{titulo}")
    public ResponseEntity<Competencia> actualizarCompetencia(@PathVariable String titulo, @Valid @RequestBody Competencia competenciaActualizada) {
        if (!competenciaDAO.existsById(titulo)) {
            return ResponseEntity.notFound().build();
        }
        // Asignamos el título por si acaso no viene en el body, para asegurar que actualizamos el correcto
        competenciaActualizada.setTitulo(titulo); 
        Competencia guardada = competenciaDAO.update(titulo, competenciaActualizada);
        return ResponseEntity.ok(guardada);
    }

    // --- DELETE (Eliminar) ---
    // URL: DELETE http://localhost:8080/api/competencias/un-titulo
    @DeleteMapping("/{titulo}")
    public ResponseEntity<Void> eliminarCompetencia(@PathVariable String titulo) {
        if (!competenciaDAO.existsById(titulo)) {
            return ResponseEntity.notFound().build();
        }
        competenciaDAO.deleteById(titulo);
        return ResponseEntity.noContent().build();
    }
}
