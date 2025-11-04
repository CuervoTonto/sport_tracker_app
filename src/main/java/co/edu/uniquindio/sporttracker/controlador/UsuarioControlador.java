package co.edu.uniquindio.sporttracker.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.uniquindio.sporttracker.casosuso.CrearUsuarioCasoUso;
import co.edu.uniquindio.sporttracker.dto.usuario.CrearUsuarioPeticion;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioControlador {
    @Autowired
    private CrearUsuarioCasoUso crearUsuario;

    @PostMapping(path = "/almacenar")
    public String crearUsuario(
        @Valid @ModelAttribute CrearUsuarioPeticion peticion
    ) {
        this.crearUsuario.ejecutar(peticion);
        
        return "redirect:/";
    }
    
    @GetMapping(path = "/crear")
    public String inicio() {
        return "usuarios/crear";
    }
}
