package co.edu.uniquindio.sporttracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioViewController {
    @GetMapping(path = "")
    public String inicio() {
        return "pages/usuarios/inicio";
    }
}
