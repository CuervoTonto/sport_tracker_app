package co.edu.uniquindio.sporttracker.dto;

public class CompetenciaAsistencia {
    private String titulo;
    private Integer asistencias;

    public CompetenciaAsistencia() {
    }

    public String getTitulo() {
        return titulo;

    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }
}
