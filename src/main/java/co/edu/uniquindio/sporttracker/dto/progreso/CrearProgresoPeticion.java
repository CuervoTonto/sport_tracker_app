package co.edu.uniquindio.sporttracker.dto.progreso;

import java.time.LocalDateTime;

public class CrearProgresoPeticion {
  private Float kilometrosRecorridos;

  private LocalDateTime fecha;

  private String usuario;

  public Float getKilometrosRecorridos() {
    return kilometrosRecorridos;
  }

  public void setKilometrosRecorridos(Float kilometrosRecorridos) {
    this.kilometrosRecorridos = kilometrosRecorridos;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
}
