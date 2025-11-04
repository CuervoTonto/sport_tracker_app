package co.edu.uniquindio.sporttracker.controller;

import co.edu.uniquindio.sporttracker.dao.UsuarioDAO; // <- CAMBIADO
import co.edu.uniquindio.sporttracker.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO; 

    // --- CREATE (Crear) ---
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioDAO.existsById(usuario.getUsername())) { // <- CAMBIADO
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
        }
        Usuario nuevoUsuario = usuarioDAO.save(usuario); // <- CAMBIADO
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario); 
    }

    // --- READ (Leer todos) ---
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.findAll(); 
    }

    // --- READ (Leer uno) ---
    @GetMapping("/{username}")
    public ResponseEntity<Usuario> obtenerUsuarioPorUsername(@PathVariable String username) {
        return usuarioDAO.findById(username) 
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    }

    // --- UPDATE (Actualizar) ---
    @PutMapping("/{username}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable String username, @Valid @RequestBody Usuario usuarioActualizado) {
        // Primero verificamos si existe
        if (!usuarioDAO.existsById(username)) { 
            return ResponseEntity.notFound().build(); 
        }
        // El DAO se encarga de la actualización
        Usuario guardado = usuarioDAO.update(username, usuarioActualizado); 
        return ResponseEntity.ok(guardado);
    }

    // --- DELETE (Eliminar) ---
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String username) {
        if (!usuarioDAO.existsById(username)) { 
            return ResponseEntity.notFound().build();
        }
        usuarioDAO.deleteById(username); 
        return ResponseEntity.noContent().build();
    }
}