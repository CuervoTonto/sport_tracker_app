package co.edu.uniquindio.sporttracker.maapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.MiembrosPorGrupoDTO;

public class MiembrosPorGrupoMapper implements RowMapper<MiembrosPorGrupoDTO> {
    @Override
    public MiembrosPorGrupoDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        MiembrosPorGrupoDTO dto = new MiembrosPorGrupoDTO();
        dto.setGrupo(r.getString("grupo"));
        dto.setMiembros(r.getInt("miembros"));
        return dto;
    }
}
