package co.edu.uniquindio.sporttracker.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.sporttracker.mapper.ProgresoMapper;
import co.edu.uniquindio.sporttracker.modelo.Progreso;

@Repository
public class ProgresoRepositorio {
  @Autowired
  private JdbcTemplate plantilla;

  public void crear(Progreso progreso) {
    String sql = "INSERT INTO progresos (usuario_username, kilometros_recorridos, fecha) VALUES (?, ?, ?)";

    this.plantilla.update(sql, progreso.getUsuario(), progreso.getKilometrosRecorridos(), progreso.getFecha());
  }

  public void actualiar(Long id, Progreso progreso) {
    String sql = "UPDATE progresos SET usuario_username = ?, kilometros_recorridos = ?, fecha = ? WHERE id = ?";

    this.plantilla.update(sql, progreso.getUsuario(), progreso.getKilometrosRecorridos(), progreso.getFecha(), id);
  }

  public void eliminar(Long id) {
    String sql = "DELETE FROM progresos WHERE id = ?";

    this.plantilla.update(sql, id);
  }

  public Optional<Progreso> encontrarPorId(Long id) {
    String sql = "SELECT * FROM progresos WHERE id = ? LIMIT 1";

    return Optional.ofNullable(
      this.plantilla.queryForObject(sql, new ProgresoMapper(), id)
    );
  }

  public List<Progreso> encontrarPorFecha(LocalDate fecha) {
    String sql = "SELECT * FROM progresos WHERE DATE(fecha) = ?";

    return this.plantilla.query(sql, new ProgresoMapper(), fecha);
  }

  public List<Progreso> obtenerTodos() {
    return this.plantilla.query("SELECT * FROM progresos", new ProgresoMapper());
  }
}
