package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.ActividadGrupoDTO;

public class ActividadGrupoMapper implements RowMapper<ActividadGrupoDTO> {
    @Override
    public ActividadGrupoDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        ActividadGrupoDTO dto = new ActividadGrupoDTO();
        dto.setGrupo(r.getString("grupo_nombre"));
        dto.setActividadesMes(r.getInt("actividades_mes"));
        return dto;
    }
}
