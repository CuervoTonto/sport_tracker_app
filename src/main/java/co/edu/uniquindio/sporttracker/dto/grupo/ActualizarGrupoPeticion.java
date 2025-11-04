package co.edu.uniquindio.sporttracker.dto.grupo;

import jakarta.validation.constraints.NotNull;

public class ActualizarGrupoPeticion {
  @NotNull
  private String nombre;

  @NotNull
  private String administrador;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getAdministrador() {
    return administrador;
  }

  public void setAdministrador(String administrador) {
    this.administrador = administrador;
  }
}
