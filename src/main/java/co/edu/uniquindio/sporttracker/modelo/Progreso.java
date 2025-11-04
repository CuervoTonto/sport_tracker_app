package co.edu.uniquindio.sporttracker.modelo;

import java.time.LocalDateTime;

public class Progreso {
  private Long id;

  private Float kilometrosRecorridos;

  private LocalDateTime fecha;

  private String usuario;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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
