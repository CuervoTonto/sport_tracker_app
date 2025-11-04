package co.edu.uniquindio.sporttracker.dto.usuario;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

public class ActualizarUsuarioPeticion {
  @NotNull
  @NotBlank
  private String username;

  @NotNull
  @NotBlank
  private String primerNombre;

  @Nullable
  private String segundoNombre;

  @NotNull
  @NotBlank
  private String primerApellido;

  @Nullable
  private String segundoApellido;

  @NotNull
  @Past
  private LocalDate fechaNacimiento;

  @NotNull
  @Email
  private String correo;

  @NotNull
  @PositiveOrZero
  private String celular;

  @Nullable
  @NotBlank
  private String clave;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPrimerNombre() {
    return primerNombre;
  }

  public void setPrimerNombre(String primerNombre) {
    this.primerNombre = primerNombre;
  }

  public String getSegundoNombre() {
    return segundoNombre;
  }

  public void setSegundoNombre(String segundoNombre) {
    this.segundoNombre = segundoNombre;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public void setPrimerApellido(String primerApellido) {
    this.primerApellido = primerApellido;
  }

  public String getSegundoApellido() {
    return segundoApellido;
  }

  public void setSegundoApellido(String segundoApellido) {
    this.segundoApellido = segundoApellido;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }
}
