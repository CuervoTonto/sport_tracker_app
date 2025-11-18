package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.AvanceMetasDTO;

public class AvanceMetasMapper implements RowMapper<AvanceMetasDTO> {
    @Override
    public AvanceMetasDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        AvanceMetasDTO dto = new AvanceMetasDTO();
        dto.setId(r.getLong("id"));
        dto.setUsuario(r.getString("usuario_username"));
        dto.setObjetivo(r.getFloat("kilometros_objetivos"));
        dto.setActual(r.getFloat("kilometros_actuales"));
        dto.setPorcentaje(r.getDouble("porc_avance"));
        return dto;
    }
}
