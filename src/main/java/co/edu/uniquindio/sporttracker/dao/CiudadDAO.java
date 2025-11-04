package co.edu.uniquindio.sporttracker.dao;

import co.edu.uniquindio.sporttracker.model.Ciudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CiudadDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Ciudad> ciudadRowMapper = (ResultSet rs, int rowNum) -> {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(rs.getString("nombre"));
        ciudad.setDepartamentoNombre(rs.getString("departamento_nombre"));
        return ciudad;
    };

    // --- READ (SQL SELECT ALL) ---
    public List<Ciudad> findAll() {
        // Ordenamos por nombre para que el dropdown se vea bien
        String sql = "SELECT * FROM ciudades ORDER BY nombre ASC";
        return jdbcTemplate.query(sql, ciudadRowMapper);
    }
}
