package co.edu.uniquindio.sporttracker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class Grupo {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    // La fecha de creación la asignará el servidor, no el cliente.
    private LocalDateTime fechaCreacion;

    @NotBlank
    @Size(max = 100)
    private String administrador; // Este será el 'username' del usuario admin

    // --- Getters y Setters ---
    // (Eclipse: Clic derecho -> Source -> Generate Getters and Setters)

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}