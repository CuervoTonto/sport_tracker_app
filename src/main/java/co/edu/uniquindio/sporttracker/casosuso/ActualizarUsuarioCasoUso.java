package co.edu.uniquindio.sporttracker.casosuso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.sporttracker.dto.usuario.ActualizarUsuarioPeticion;
import co.edu.uniquindio.sporttracker.mapper.UsuarioMapper;
import co.edu.uniquindio.sporttracker.repositorio.UsuarioRepositorio;

@Component
public class ActualizarUsuarioCasoUso {
    private final UsuarioRepositorio repositorio;

    @Autowired
    public ActualizarUsuarioCasoUso(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(String username, ActualizarUsuarioPeticion entrada) {
        this.repositorio.actualizar(username, new UsuarioMapper().desdePeticion(entrada));
    }
}
