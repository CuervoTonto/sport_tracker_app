package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.CompetenciaDetalleDTO;

public class CompetenciaDetalleMapper implements RowMapper<CompetenciaDetalleDTO> {
    @Override
    public CompetenciaDetalleDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        CompetenciaDetalleDTO dto = new CompetenciaDetalleDTO();
        dto.setTitulo(r.getString("titulo"));
        dto.setCiudad(r.getString("ciudad_nombre"));
        dto.setDepartamento(r.getString("departamento_nombre"));
        dto.setCompetidores(r.getInt("competidores"));
        return dto;
    }
}
