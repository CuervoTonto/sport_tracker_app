package co.edu.uniquindio.sporttracker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class Meta {

    // A diferencia de los otros, el ID será un Long y no lo validamos
    // (porque lo genera la base de datos)
    private Long id;

    @NotNull
    @PositiveOrZero
    private Float kilometrosObjetivos;

    @NotNull
    @PositiveOrZero
    private Float kilometrosActuales;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotBlank
    private String usuarioUsername; // La FK al usuario

    // --- Getters y Setters ---
    // (Eclipse: Clic derecho -> Source -> Generate Getters and Setters)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getKilometrosObjetivos() {
        return kilometrosObjetivos;
    }

    public void setKilometrosObjetivos(Float kilometrosObjetivos) {
        this.kilometrosObjetivos = kilometrosObjetivos;
    }

    public Float getKilometrosActuales() {
        return kilometrosActuales;
    }

    public void setKilometrosActuales(Float kilometrosActuales) {
        this.kilometrosActuales = kilometrosActuales;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuarioUsername() {
        return usuarioUsername;
    }

    public void setUsuarioUsername(String usuarioUsername) {
        this.usuarioUsername = usuarioUsername;
    }
}
