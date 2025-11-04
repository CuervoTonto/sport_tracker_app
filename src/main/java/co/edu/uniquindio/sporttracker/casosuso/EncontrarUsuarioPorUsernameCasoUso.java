package co.edu.uniquindio.sporttracker.casosuso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.sporttracker.modelo.Usuario;
import co.edu.uniquindio.sporttracker.repositorio.UsuarioRepositorio;

@Component
public class EncontrarUsuarioPorUsernameCasoUso {
  private final UsuarioRepositorio repositorio;

  @Autowired
  public EncontrarUsuarioPorUsernameCasoUso(UsuarioRepositorio repositorio) {
    this.repositorio = repositorio;
  }
  
  public Optional<Usuario> ejecutar(String username) {
    return Optional.ofNullable(
      this.repositorio.encontrarPorUsername(username)
    );
  }
}
