package co.edu.uniquindio.sporttracker.dao;

import co.edu.uniquindio.sporttracker.model.Competencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class CompetenciaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para convertir una fila SQL en un objeto Competencia
    private final RowMapper<Competencia> competenciaRowMapper = (ResultSet rs, int rowNum) -> {
        Competencia competencia = new Competencia();
        competencia.setTitulo(rs.getString("titulo"));
        competencia.setDescripcion(rs.getString("descripcion"));
        competencia.setCiudadNombre(rs.getString("ciudad_nombre"));
        return competencia;
    };

    // --- CREATE (SQL INSERT) ---
    public Competencia save(Competencia competencia) {
        String sql = "INSERT INTO competencias (titulo, descripcion, ciudad_nombre) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                competencia.getTitulo(),
                competencia.getDescripcion(),
                competencia.getCiudadNombre()
        );
        return competencia;
    }

    // --- READ (SQL SELECT ALL) ---
    public List<Competencia> findAll() {
        String sql = "SELECT * FROM competencias";
        return jdbcTemplate.query(sql, competenciaRowMapper);
    }

    // --- READ (SQL SELECT ONE) ---
    public Optional<Competencia> findById(String titulo) {
        String sql = "SELECT * FROM competencias WHERE titulo = ?";
        try {
            Competencia competencia = jdbcTemplate.queryForObject(sql, new Object[]{titulo}, competenciaRowMapper);
            return Optional.of(competencia);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // --- UPDATE (SQL UPDATE) ---
    public Competencia update(String titulo, Competencia competencia) {
        String sql = "UPDATE competencias SET descripcion = ?, ciudad_nombre = ? WHERE titulo = ?";
        // Nota: No actualizamos el 'titulo' porque es la clave primaria.
        jdbcTemplate.update(sql,
                competencia.getDescripcion(),
                competencia.getCiudadNombre(),
                titulo
        );
        return competencia;
    }

    // --- DELETE (SQL DELETE) ---
    public void deleteById(String titulo) {
        String sql = "DELETE FROM competencias WHERE titulo = ?";
        jdbcTemplate.update(sql, titulo);
    }

    // --- Helper (SQL SELECT COUNT) ---
    public boolean existsById(String titulo) {
        String sql = "SELECT COUNT(*) FROM competencias WHERE titulo = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{titulo}, Integer.class);
        return count != null && count > 0;
    }
}