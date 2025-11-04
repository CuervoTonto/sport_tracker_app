package co.edu.uniquindio.sporttracker.casosuso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.sporttracker.modelo.Usuario;
import co.edu.uniquindio.sporttracker.repositorio.UsuarioRepositorio;

@Component
public class BuscarUsuarioCasoUso {
    private final UsuarioRepositorio repositorio;
    
    @Autowired
    public BuscarUsuarioCasoUso(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    public List<Usuario> ejecutar(String valor) {
        if (valor == null) {
            return this.repositorio.obtenerTodos();
        } else {
            return this.repositorio.buscarPorUsernameOCorreo(valor);
        }
    }
}
