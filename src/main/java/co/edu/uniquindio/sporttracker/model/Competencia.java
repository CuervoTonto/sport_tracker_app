package co.edu.uniquindio.sporttracker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Competencia {

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotBlank
    private String descripcion;

    @Size(max = 100)
    private String ciudadNombre; // Puede ser nulo (SET NULL en el SQL)

    // --- Getters y Setters ---
    // (Eclipse: Clic derecho -> Source -> Generate Getters and Setters)

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }
}