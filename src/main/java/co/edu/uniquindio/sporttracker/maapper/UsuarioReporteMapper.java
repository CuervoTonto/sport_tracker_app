package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.edu.uniquindio.sporttracker.dto.UsuarioReporteDTO;

public class UsuarioReporteMapper implements RowMapper<UsuarioReporteDTO> {
    @Override
    public UsuarioReporteDTO mapRow(ResultSet r, int arg1) throws SQLException {
        UsuarioReporteDTO ur = new UsuarioReporteDTO();

        ur.setUsername(r.getString("username"));
        ur.setNombres(r.getString("nombres"));
        ur.setApellidos(r.getString("apellidos"));
        ur.setCorreo(r.getString("correo"));
        ur.setCelular(r.getString("celular"));

        return ur;
    }
}
