package co.edu.uniquindio.sporttracker.dto;

public class GrupoMiembros {
    private String nombre;
    private Integer numUsuarios;

    public GrupoMiembros() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(Integer numUsuarios) {
        this.numUsuarios = numUsuarios;
    }
}
