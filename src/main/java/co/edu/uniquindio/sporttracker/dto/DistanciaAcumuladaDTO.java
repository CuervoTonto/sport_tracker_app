package co.edu.uniquindio.sporttracker.dto;

public class DistanciaAcumuladaDTO {
    
    private String usuario;
    private Double kmTotales;
    private Double kmPromedioDiario;

    public DistanciaAcumuladaDTO() {
        // Constructor vacío necesario
    }

    // --- GETTERS (Para leer) ---
    public String getUsuario() {
        return usuario;
    }

    public Double getKmTotales() {
        return kmTotales;
    }

    public Double getKmPromedioDiario() {
        return kmPromedioDiario;
    }

    // --- SETTERS (Para escribir - ESTOS SON LOS QUE TE FALTAN) ---
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setKmTotales(Double kmTotales) {
        this.kmTotales = kmTotales;
    }

    public void setKmPromedioDiario(Double kmPromedioDiario) {
        this.kmPromedioDiario = kmPromedioDiario;
    }
}
