package co.edu.uniquindio.sporttracker.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.sporttracker.dao.UsuarioDAO;
import co.edu.uniquindio.sporttracker.dto.LoginUsuarioDTO;
import co.edu.uniquindio.sporttracker.model.Usuario;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @PostMapping
    public ResponseEntity<?> login(
        @RequestBody LoginUsuarioDTO request
    ) {
        Optional<Usuario> usOpt = usuarioDAO.findById(request.username());

        if (usOpt.isEmpty()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("errors", Map.of("username", "No se encontro el usuario"));

            return ResponseEntity.badRequest().body(errors);
        }

        if (! usOpt.get().getContrasenia().equals(request.password())) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("errors", Map.of("username", "Contraeñas incorrectas"));

            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok(usOpt.get());
    }
}
