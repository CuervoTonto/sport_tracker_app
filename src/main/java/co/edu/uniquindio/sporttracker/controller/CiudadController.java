package co.edu.uniquindio.sporttracker.controller;

import co.edu.uniquindio.sporttracker.dao.CiudadDAO;
import co.edu.uniquindio.sporttracker.model.Ciudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "*")
public class CiudadController {

    @Autowired
    private CiudadDAO ciudadDAO;

    @GetMapping
    public List<Ciudad> listarCiudades() {
        return ciudadDAO.findAll();
    }
}
