package co.edu.uniquindio.sporttracker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public class Progreso {

    private Long id; // Autoincrementado

    @NotNull
    @PositiveOrZero
    private Float kilometrosRecorridos;

    @NotNull
    private LocalDateTime fecha; // La tabla usa DATETIME

    @NotBlank
    private String usuarioUsername; // La FK al usuario

    // --- Getters y Setters ---
    

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

    public String getUsuarioUsername() {
        return usuarioUsername;
    }

    public void setUsuarioUsername(String usuarioUsername) {
        this.usuarioUsername = usuarioUsername;
    }
}
