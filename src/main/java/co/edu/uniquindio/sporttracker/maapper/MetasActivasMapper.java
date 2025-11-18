package co.edu.uniquindio.sporttracker.maapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.edu.uniquindio.sporttracker.dto.MetasActivasDTO;

public class MetasActivasMapper implements RowMapper<MetasActivasDTO> {
    @Override
    public MetasActivasDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        MetasActivasDTO dto = new MetasActivasDTO();
        dto.setUsuario(r.getString("usuario_username"));
        dto.setMetasActivas(r.getInt("metas_activas"));
        return dto;
    }
}
