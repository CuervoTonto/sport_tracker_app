package co.edu.uniquindio.sporttracker.dto;

public class AvanceMetasDTO {
    private Long id;
    private String usuario;
    private Float objetivo;
    private Float actual;
    private Double porcentaje;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public Float getObjetivo() { return objetivo; }
    public void setObjetivo(Float objetivo) { this.objetivo = objetivo; }
    public Float getActual() { return actual; }
    public void setActual(Float actual) { this.actual = actual; }
    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }
}
