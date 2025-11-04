package co.edu.uniquindio.sporttracker.casosuso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.sporttracker.dto.usuario.CrearUsuarioPeticion;
import co.edu.uniquindio.sporttracker.mapper.UsuarioMapper;
import co.edu.uniquindio.sporttracker.repositorio.UsuarioRepositorio;

@Component
public class CrearUsuarioCasoUso {
    private final UsuarioRepositorio repositorio;

    @Autowired
    public CrearUsuarioCasoUso(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(CrearUsuarioPeticion entrada) {
        this.repositorio.crear(new UsuarioMapper().desdePeticion(entrada));
    }
}
