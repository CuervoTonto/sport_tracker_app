package co.edu.uniquindio.sporttracker.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import co.edu.uniquindio.sporttracker.modelo.Grupo;

public class GrupoMapper implements RowMapper<Grupo> {
  @Override
  @Nullable
  public Grupo mapRow(@NonNull ResultSet fuente, int arg1) throws SQLException {
    Grupo grupo = new Grupo();
    grupo.setNombre(fuente.getString("nombre"));
    grupo.setCreacion(fuente.getObject("fecha_creacion", LocalDateTime.class));
    grupo.setAdministrador(fuente.getString("administrador"));

    return grupo;
  }
}
