package co.edu.uniquindio.sporttracker.casosuso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.sporttracker.repositorio.UsuarioRepositorio;

@Component
public class EliminarUsuarioCasoUso {
  private final UsuarioRepositorio repositorio;
  
  @Autowired
  public EliminarUsuarioCasoUso(UsuarioRepositorio repositorio) {
    this.repositorio = repositorio;
  }

  public void ejecutar(String username) {
    this.repositorio.eliminar(username);
  }
}
