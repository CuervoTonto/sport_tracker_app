package co.edu.uniquindio.sporttracker.modelo;

import java.time.LocalDateTime;

public class Grupo {
  private String nombre;

  private LocalDateTime creacion;

  private String administrador;

  public Grupo() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public LocalDateTime getCreacion() {
    return creacion;
  }

  public void setCreacion(LocalDateTime creacion) {
    this.creacion = creacion;
  }

  public String getAdministrador() {
    return administrador;
  }

  public void setAdministrador(String administrador) {
    this.administrador = administrador;
  }
}
