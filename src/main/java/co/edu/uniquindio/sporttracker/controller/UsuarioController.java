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

        // --- VALIDACIÓN MANUAL AÑADIDA ---
        if (usuario.getContrasenia() == null || usuario.getContrasenia().length() < 8) {
            // Si la contraseña es nula o muy corta, rechazamos la petición.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
        // --- FIN DE LA VALIDACIÓN ---

        if (usuarioDAO.existsById(usuario.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
        }
        Usuario nuevoUsuario = usuarioDAO.save(usuario);
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

        // Verificamos si el usuario existe
        Optional<Usuario> usuarioAntiguoOpt = usuarioDAO.findById(username);
        if (usuarioAntiguoOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        // --- LÓGICA DE CONTRASEÑA ---
        if (usuarioActualizado.getContrasenia() == null || usuarioActualizado.getContrasenia().isEmpty()) {
            // El usuario NO quiere cambiar la contraseña.
            // Le volvemos a poner la contraseña antigua para no borrarla.
            String contraseniaAntigua = usuarioAntiguoOpt.get().getContrasenia();
            usuarioActualizado.setContrasenia(contraseniaAntigua);

        } else {
            // El usuario SÍ quiere cambiar la contraseña.
            // Validamos que la NUEVA contraseña tenga 8+ caracteres.
            if (usuarioActualizado.getContrasenia().length() < 8) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
            }
        }
        // --- FIN DE LA LÓGICA ---

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