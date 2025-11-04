package co.edu.uniquindio.sporttracker.dao;

import co.edu.uniquindio.sporttracker.model.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class MetaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para convertir una fila SQL en un objeto Meta
    private final RowMapper<Meta> metaRowMapper = (ResultSet rs, int rowNum) -> {
        Meta meta = new Meta();
        meta.setId(rs.getLong("id"));
        meta.setKilometrosObjetivos(rs.getFloat("kilometros_objetivos"));
        meta.setKilometrosActuales(rs.getFloat("kilometros_actuales"));
        meta.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        meta.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        meta.setUsuarioUsername(rs.getString("usuario_username"));
        return meta;
    };

    // --- CREATE (SQL INSERT con ID Auto-generado) ---
    // ¡Este es el método diferente!
    public Meta save(Meta meta) {
        String sql = "INSERT INTO metas (kilometros_objetivos, kilometros_actuales, fecha_inicio, fecha_fin, usuario_username) " +
                     "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Usamos una versión de .update() que nos permite obtener las claves generadas
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, meta.getKilometrosObjetivos());
            ps.setFloat(2, meta.getKilometrosActuales());
            ps.setDate(3, java.sql.Date.valueOf(meta.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(meta.getFechaFin()));
            ps.setString(5, meta.getUsuarioUsername());
            return ps;
        }, keyHolder);

        // Obtenemos el ID generado y lo asignamos al objeto meta
        if (keyHolder.getKey() != null) {
            meta.setId(keyHolder.getKey().longValue());
        }

        return meta;
    }

    // --- READ (SQL SELECT ALL) ---
    public List<Meta> findAll() {
        String sql = "SELECT * FROM metas";
        return jdbcTemplate.query(sql, metaRowMapper);
    }
    
    // --- READ (SQL SELECT por Usuario) ---
    // Un endpoint útil para el admin: ver todas las metas de un usuario específico
    public List<Meta> findAllByUsuario(String username) {
        String sql = "SELECT * FROM metas WHERE usuario_username = ?";
        return jdbcTemplate.query(sql, new Object[]{username}, metaRowMapper);
    }

    // --- READ (SQL SELECT ONE por ID) ---
    public Optional<Meta> findById(Long id) {
        String sql = "SELECT * FROM metas WHERE id = ?";
        try {
            Meta meta = jdbcTemplate.queryForObject(sql, new Object[]{id}, metaRowMapper);
            return Optional.of(meta);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // --- UPDATE (SQL UPDATE por ID) ---
    public Meta update(Long id, Meta meta) {
        String sql = "UPDATE metas SET kilometros_objetivos = ?, kilometros_actuales = ?, " +
                     "fecha_inicio = ?, fecha_fin = ?, usuario_username = ? " +
                     "WHERE id = ?";
        
        jdbcTemplate.update(sql,
                meta.getKilometrosObjetivos(),
                meta.getKilometrosActuales(),
                meta.getFechaInicio(),
                meta.getFechaFin(),
                meta.getUsuarioUsername(),
                id // El 'id' va al final para el WHERE
        );
        meta.setId(id); // Nos aseguramos de que el objeto devuelto tenga el ID
        return meta;
    }

    // --- DELETE (SQL DELETE por ID) ---
    public void deleteById(Long id) {
        String sql = "DELETE FROM metas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // --- Helper (SQL SELECT COUNT por ID) ---
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM metas WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
