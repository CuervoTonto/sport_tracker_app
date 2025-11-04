package co.edu.uniquindio.sporttracker.controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.sporttracker.dto.progreso.ActualizarProgresoPeticion;
import co.edu.uniquindio.sporttracker.dto.progreso.CrearProgresoPeticion;
import co.edu.uniquindio.sporttracker.modelo.Progreso;
import co.edu.uniquindio.sporttracker.servicio.ProgresoServicio;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/progresos")
public class ProgresoControlador {
  @Autowired
  private ProgresoServicio servicio;

  @PostMapping
  public ResponseEntity<Void> crear(
    @RequestBody CrearProgresoPeticion peticion
  ) {
    this.servicio.crear(peticion);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Void> actualiar(
    @PathVariable Long id,
    @RequestBody @Valid ActualizarProgresoPeticion entrada
  ) {
    this.servicio.actualizar(id, entrada);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> eliminar(
    @PathVariable Long id
  ) {
    this.servicio.eliminar(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Progreso> encontrarPorId(
    @PathVariable Long id
  ) {
    Optional<Progreso> progreso = this.servicio.encontrarPorId(id);

    if (progreso.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.of(progreso);
    }
  }

  @GetMapping
  public ResponseEntity<List<Progreso>> buscar(
    @RequestParam @Nullable LocalDate fecha
  ) {
    return ResponseEntity.ofNullable(this.servicio.buscar(fecha));
  }
}
