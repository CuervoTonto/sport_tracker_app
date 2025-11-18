package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.DistanciaAcumuladaDTO;

public class DistanciaAcumuladaMapper implements RowMapper<DistanciaAcumuladaDTO> {
    @Override
    public DistanciaAcumuladaDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        DistanciaAcumuladaDTO dto = new DistanciaAcumuladaDTO();
        dto.setUsuario(r.getString("usuario_username"));
        dto.setKmTotales(r.getDouble("km_totales"));
        dto.setKmPromedioDiario(r.getDouble("km_prom_dia"));
        return dto;
    }
}