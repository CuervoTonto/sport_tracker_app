package co.edu.uniquindio.sporttracker.controlador;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.sporttracker.casosuso.ActualizarUsuarioCasoUso;
import co.edu.uniquindio.sporttracker.casosuso.BuscarUsuarioCasoUso;
import co.edu.uniquindio.sporttracker.casosuso.CrearUsuarioCasoUso;
import co.edu.uniquindio.sporttracker.casosuso.EliminarUsuarioCasoUso;
import co.edu.uniquindio.sporttracker.casosuso.EncontrarUsuarioPorUsernameCasoUso;
import co.edu.uniquindio.sporttracker.dto.usuario.ActualizarUsuarioPeticion;
import co.edu.uniquindio.sporttracker.dto.usuario.CrearUsuarioPeticion;
import co.edu.uniquindio.sporttracker.modelo.Usuario;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioControlador {
    @Autowired
    private CrearUsuarioCasoUso crearUsuario;
    
    @Autowired
    private BuscarUsuarioCasoUso buscarUsuario;
    
    @Autowired
    private EncontrarUsuarioPorUsernameCasoUso encontrarUsuarioPorUsername;

    @Autowired
    private ActualizarUsuarioCasoUso actualizarUsuario;

    @Autowired
    private EliminarUsuarioCasoUso eliminarUsuario;

    @PostMapping
    public ResponseEntity<Void> crearUsuario(
        @Valid @RequestBody CrearUsuarioPeticion peticion
    ) {
        this.crearUsuario.ejecutar(peticion);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarUsuario(
        @RequestParam @Nullable String q
    ) {
        return ResponseEntity.ofNullable(this.buscarUsuario.ejecutar(q));
    }
    
    @GetMapping(path = "/{username}")
    public ResponseEntity<Usuario> encontrarUsuario(
        @PathVariable String username
    ) {
        Optional<Usuario> usuario = this.encontrarUsuarioPorUsername.ejecutar(username);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.of(usuario);
        }
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<Void> actualizarUsuario(
        @PathVariable String username,
        @RequestBody ActualizarUsuarioPeticion entrada
    ) {
        this.actualizarUsuario.ejecutar(username, entrada);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Void> eliminarUsuario(
        @PathVariable String username
    ) {
        this.eliminarUsuario.ejecutar(username);

        return ResponseEntity.noContent().build();
    }
}
