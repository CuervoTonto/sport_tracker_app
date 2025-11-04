package co.edu.uniquindio.sporttracker.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniquindio.sporttracker.dto.grupo.ActualizarGrupoPeticion;
import co.edu.uniquindio.sporttracker.dto.grupo.CrearGrupoPeticion;
import co.edu.uniquindio.sporttracker.modelo.Grupo;
import co.edu.uniquindio.sporttracker.repositorio.GrupoRepositorio;

@Service
public class GrupoServicio {
  @Autowired
  private GrupoRepositorio grupoRepositorio;

  public void crear(CrearGrupoPeticion entrada) {
    Grupo grupo = new Grupo();
    grupo.setNombre(entrada.getNombre());
    grupo.setCreacion(LocalDateTime.now());
    grupo.setAdministrador(entrada.getAdministrador());

    this.grupoRepositorio.crear(grupo);
  }

  public void actualizar(String nombre, ActualizarGrupoPeticion entrada) {
    Grupo grupo = new Grupo();
    grupo.setNombre(entrada.getNombre());
    grupo.setCreacion(LocalDateTime.now());
    grupo.setAdministrador(entrada.getAdministrador());

    this.grupoRepositorio.actualizar(nombre, grupo);
  }

  public void eliminar(String nombre) {
    this.grupoRepositorio.eliminar(nombre);
  }

  public List<Grupo> buscar(String nombre) {
    return this.grupoRepositorio.buscarPorNombre(nombre);
  }

  public Grupo encontrar(String nombre) {
    return this.grupoRepositorio.encontrarPorNombre(nombre);
  }
}
