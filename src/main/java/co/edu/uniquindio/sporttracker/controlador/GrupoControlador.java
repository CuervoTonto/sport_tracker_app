package co.edu.uniquindio.sporttracker.controlador;

import java.util.List;

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

import co.edu.uniquindio.sporttracker.dto.grupo.ActualizarGrupoPeticion;
import co.edu.uniquindio.sporttracker.dto.grupo.CrearGrupoPeticion;
import co.edu.uniquindio.sporttracker.modelo.Grupo;
import co.edu.uniquindio.sporttracker.servicio.GrupoServicio;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/grupos")
public class GrupoControlador {
  @Autowired
  private GrupoServicio grupoServicio;

  @PostMapping
  public ResponseEntity<Void> crear(
    @RequestBody @Valid CrearGrupoPeticion entrada
  ) {
    this.grupoServicio.crear(entrada);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping(path = "/{nombre}")
  public ResponseEntity<Void> actualizar(
    @PathVariable String nombre,
    @RequestBody @Valid ActualizarGrupoPeticion entrada
  ) {
    this.grupoServicio.actualizar(nombre, entrada);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{nombre}")
  public ResponseEntity<Void> actualizar(
    @PathVariable String nombre
  ) {
    this.grupoServicio.eliminar(nombre);

    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/{nombre}")
  public ResponseEntity<Grupo> encontrar(
    @PathVariable String nombre
  ) {
    return ResponseEntity.ofNullable(this.grupoServicio.encontrar(nombre));
  }

  @GetMapping
  public ResponseEntity<List<Grupo>> buscar(
    @RequestParam String nombre
  ) {
    return ResponseEntity.ofNullable(this.grupoServicio.buscar(nombre));
  }
}
