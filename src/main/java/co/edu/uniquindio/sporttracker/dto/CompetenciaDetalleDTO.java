package co.edu.uniquindio.sporttracker.dto;

public class CompetenciaDetalleDTO {
    private String titulo;
    private String ciudad;
    private String departamento;
    private Integer competidores;

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public Integer getCompetidores() { return competidores; }
    public void setCompetidores(Integer competidores) { this.competidores = competidores; }
}