package co.edu.uniquindio.sporttracker.dao;

import co.edu.uniquindio.sporttracker.model.Progreso;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ProgresoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para convertir una fila SQL en un objeto Progreso
    private final RowMapper<Progreso> progresoRowMapper = (ResultSet rs, int rowNum) -> {
        Progreso progreso = new Progreso();
        progreso.setId(rs.getLong("id"));
        progreso.setKilometrosRecorridos(rs.getFloat("kilometros_recorridos"));
        // Convertimos el Timestamp de SQL (DATETIME) a LocalDateTime de Java
        progreso.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
        progreso.setUsuarioUsername(rs.getString("usuario_username"));
        return progreso;
    };

    // --- CREATE (SQL INSERT con ID Auto-generado) ---
    public Progreso save(Progreso progreso) {
        String sql = "INSERT INTO progresos (kilometros_recorridos, fecha, usuario_username) " +
                     "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, progreso.getKilometrosRecorridos());
            // Convertimos LocalDateTime a Timestamp para SQL
            ps.setTimestamp(2, Timestamp.valueOf(progreso.getFecha()));
            ps.setString(3, progreso.getUsuarioUsername());
            return ps;
        }, keyHolder);

        // Obtenemos el ID generado y lo asignamos al objeto
        if (keyHolder.getKey() != null) {
            progreso.setId(keyHolder.getKey().longValue());
        }

        return progreso;
    }

    // --- READ (SQL SELECT ALL) ---
    public List<Progreso> findAll() {
        String sql = "SELECT * FROM progresos";
        return jdbcTemplate.query(sql, progresoRowMapper);
    }
    
    // --- READ (SQL SELECT por Usuario) ---
    // Útil para el admin: ver todo el progreso de un usuario
    public List<Progreso> findAllByUsuario(String username) {
        String sql = "SELECT * FROM progresos WHERE usuario_username = ?";
        return jdbcTemplate.query(sql, new Object[]{username}, progresoRowMapper);
    }

    // --- READ (SQL SELECT ONE por ID) ---
    public Optional<Progreso> findById(Long id) {
        String sql = "SELECT * FROM progresos WHERE id = ?";
        try {
            Progreso progreso = jdbcTemplate.queryForObject(sql, new Object[]{id}, progresoRowMapper);
            return Optional.of(progreso);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // --- UPDATE (SQL UPDATE por ID) ---
    public Progreso update(Long id, Progreso progreso) {
        String sql = "UPDATE progresos SET kilometros_recorridos = ?, fecha = ?, usuario_username = ? " +
                     "WHERE id = ?";
        
        jdbcTemplate.update(sql,
                progreso.getKilometrosRecorridos(),
                progreso.getFecha(),
                progreso.getUsuarioUsername(),
                id // El 'id' va al final para el WHERE
        );
        progreso.setId(id); // Asignamos el ID al objeto devuelto
        return progreso;
    }

    // --- DELETE (SQL DELETE por ID) ---
    public void deleteById(Long id) {
        String sql = "DELETE FROM progresos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // --- Helper (SQL SELECT COUNT por ID) ---
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM progresos WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}