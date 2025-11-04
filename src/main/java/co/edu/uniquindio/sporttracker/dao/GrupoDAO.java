package co.edu.uniquindio.sporttracker.dao;

import co.edu.uniquindio.sporttracker.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class GrupoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para convertir una fila SQL en un objeto Grupo
    private final RowMapper<Grupo> grupoRowMapper = (ResultSet rs, int rowNum) -> {
        Grupo grupo = new Grupo();
        grupo.setNombre(rs.getString("nombre"));
        // Convertimos el Timestamp de SQL a LocalDateTime de Java
        Timestamp fechaCreacionTimestamp = rs.getTimestamp("fecha_creacion");
        if (fechaCreacionTimestamp != null) {
            grupo.setFechaCreacion(fechaCreacionTimestamp.toLocalDateTime());
        }
        grupo.setAdministrador(rs.getString("administrador"));
        return grupo;
    };

    // --- CREATE (SQL INSERT) ---
    public Grupo save(Grupo grupo) {
        String sql = "INSERT INTO grupos (nombre, fecha_creacion, administrador) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                grupo.getNombre(),
                grupo.getFechaCreacion(),
                grupo.getAdministrador()
        );
        return grupo;
    }

    // --- READ (SQL SELECT ALL) ---
    public List<Grupo> findAll() {
        String sql = "SELECT * FROM grupos";
        return jdbcTemplate.query(sql, grupoRowMapper);
    }

    // --- READ (SQL SELECT ONE) ---
    public Optional<Grupo> findById(String nombre) {
        String sql = "SELECT * FROM grupos WHERE nombre = ?";
        try {
            Grupo grupo = jdbcTemplate.queryForObject(sql, new Object[]{nombre}, grupoRowMapper);
            return Optional.of(grupo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // --- UPDATE (SQL UPDATE) ---
    // Un administrador de sistema solo debería poder reasignar el admin de un grupo.
    // El nombre (PK) y la fecha de creación no deberían cambiar.
    public Grupo update(String nombre, Grupo grupo) {
        String sql = "UPDATE grupos SET administrador = ? WHERE nombre = ?";
        jdbcTemplate.update(sql,
                grupo.getAdministrador(),
                nombre
        );
        return grupo;
    }

    // --- DELETE (SQL DELETE) ---
    public void deleteById(String nombre) {
        String sql = "DELETE FROM grupos WHERE nombre = ?";
        jdbcTemplate.update(sql, nombre);
    }

    // --- Helper (SQL SELECT COUNT) ---
    public boolean existsById(String nombre) {
        String sql = "SELECT COUNT(*) FROM grupos WHERE nombre = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{nombre}, Integer.class);
        return count != null && count > 0;
    }
}