package co.edu.uniquindio.sporttracker.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import co.edu.uniquindio.sporttracker.modelo.Progreso;

public class ProgresoMapper implements RowMapper<Progreso> {
  @Override
  @Nullable
  public Progreso mapRow(@NonNull ResultSet fuente, int arg1) throws SQLException {
    Progreso progreso = new Progreso();
    progreso.setId(fuente.getLong("id"));
    progreso.setUsuario(fuente.getString("usuario_username"));
    progreso.setKilometrosRecorridos(fuente.getFloat("kilometros_recorridos"));
    progreso.setFecha(fuente.getObject("fecha", LocalDateTime.class));

    return progreso;
  }
}
