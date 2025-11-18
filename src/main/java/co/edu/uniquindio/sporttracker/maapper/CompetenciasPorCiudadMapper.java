package co.edu.uniquindio.sporttracker.maapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.CompetenciasPorCiudadDTO;

public class CompetenciasPorCiudadMapper implements RowMapper<CompetenciasPorCiudadDTO> {
    @Override
    public CompetenciasPorCiudadDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        CompetenciasPorCiudadDTO dto = new CompetenciasPorCiudadDTO();
        dto.setCiudad(r.getString("ciudad"));
        dto.setTotalCompetencias(r.getInt("total_competencias"));
        return dto;
    }
}
