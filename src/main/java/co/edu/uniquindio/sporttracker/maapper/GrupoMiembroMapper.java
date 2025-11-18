package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.edu.uniquindio.sporttracker.dto.GrupoMiembros;

public class GrupoMiembroMapper implements RowMapper<GrupoMiembros> {
    @Override
    public GrupoMiembros mapRow(ResultSet r, int arg1) throws SQLException {
        GrupoMiembros gm = new GrupoMiembros();

        gm.setNombre(r.getString("nombre"));
        gm.setNumUsuarios(r.getInt("num_usuarios"));

        return gm;
    }
}
