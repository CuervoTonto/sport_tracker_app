package co.edu.uniquindio.sporttracker.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.sporttracker.mapper.GrupoMapper;
import co.edu.uniquindio.sporttracker.modelo.Grupo;

@Repository
public class GrupoRepositorio {
  @Autowired
  private JdbcTemplate plantilla;

  public void crear(Grupo grupo) {
    String sql = "INSERT INTO grupos (nombre, fecha_creacion, administrador) VALUES (?, ?, ?)";

    this.plantilla.update(sql, grupo.getNombre(), grupo.getCreacion(), grupo.getAdministrador());
  }

  public void actualizar(String nombre, Grupo grupo) {
    String sql = "UPDATE grupos SET nombre = ?, fecha_creacion = ?, administrador = ? WHERE nombre = ?";

    this.plantilla.update(sql, grupo.getNombre(), grupo.getCreacion(), grupo.getAdministrador(), nombre);
  }

  public void eliminar(String nombre) {
    String sql = "DELETE FROM grupos WHERE nombre = ?";

    this.plantilla.update(sql, nombre);
  }

  public void existeIgnorando(String nombre, String ignorar) {
    String sql = "SELECT COUNT(nombre) FROM grupos WHERE nombre = ? AND nombre != ?";

    this.plantilla.queryForObject(sql, Integer.class, nombre, ignorar);
  }

  public Grupo encontrarPorNombre(String nombre) {
    String sql = "SELECT * FROM grupos WHERE nombre = ?";

    return this.plantilla.queryForObject(sql, new GrupoMapper(), nombre);
  }

  public List<Grupo> buscarPorNombre(String nombre) {
    String sql = "SELECT * FROM grupos WHERE nombre LIKE ?";

    return this.plantilla.query(sql, new GrupoMapper(), "%" + nombre + "%");
  }
}
