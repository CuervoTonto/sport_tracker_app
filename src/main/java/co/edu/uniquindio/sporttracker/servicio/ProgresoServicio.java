package co.edu.uniquindio.sporttracker.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniquindio.sporttracker.dto.progreso.ActualizarProgresoPeticion;
import co.edu.uniquindio.sporttracker.dto.progreso.CrearProgresoPeticion;
import co.edu.uniquindio.sporttracker.modelo.Progreso;
import co.edu.uniquindio.sporttracker.repositorio.ProgresoRepositorio;

@Service
public class ProgresoServicio {
  @Autowired
  private ProgresoRepositorio repositorio;

  public void crear(CrearProgresoPeticion entrada) {
    Progreso progreso = new Progreso();
    progreso.setUsuario(entrada.getUsuario());
    progreso.setFecha(entrada.getFecha());
    progreso.setKilometrosRecorridos(entrada.getKilometrosRecorridos());

    this.repositorio.crear(progreso);
  }

  public void actualizar(Long id, ActualizarProgresoPeticion entrada) {
    Progreso progreso = new Progreso();
    progreso.setUsuario(entrada.getUsuario());
    progreso.setFecha(entrada.getFecha());
    progreso.setKilometrosRecorridos(entrada.getKilometrosRecorridos());

    this.repositorio.actualiar(id, progreso);
  }

  public void eliminar(Long id) {
    this.repositorio.eliminar(id);
  }

  public List<Progreso> buscar(LocalDate fecha) {
    if (fecha == null) {
      return this.obtenerTodos();
    } else {
      return this.repositorio.encontrarPorFecha(fecha);
    }
  }

  public Optional<Progreso> encontrarPorId(Long id) {
    return this.repositorio.encontrarPorId(id);
  }

  public List<Progreso> obtenerTodos() {
    return this.repositorio.obtenerTodos();
  }
}
