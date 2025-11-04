package co.edu.uniquindio.sporttracker.dao;

import co.edu.uniquindio.sporttracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
   // RowMapper para convertir una fila SQL en un objeto Usuario
    private final RowMapper<Usuario> usuarioRowMapper = (ResultSet rs, int rowNum) -> {
        Usuario usuario = new Usuario();
        usuario.setUsername(rs.getString("username"));
        usuario.setPrimerNombre(rs.getString("primer_nombre"));
        usuario.setSegundoNombre(rs.getString("segundo_nombre"));
        usuario.setPrimerApellido(rs.getString("primer_apellido"));
        usuario.setSegundoApellido(rs.getString("segundo_apellido"));
        usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        usuario.setCorreo(rs.getString("correo"));
        usuario.setCelular(rs.getString("celular"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        return usuario;
    };

    // --- CREATE (SQL INSERT) ---
    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, correo, celular, contrasenia) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql,
                usuario.getUsername(),
                usuario.getPrimerNombre(),
                usuario.getSegundoNombre(),
                usuario.getPrimerApellido(),
                usuario.getSegundoApellido(),
                usuario.getFechaNacimiento(),
                usuario.getCorreo(),
                usuario.getCelular(),
                usuario.getContrasenia()
        );
        return usuario;
    }

    // --- READ (SQL SELECT ALL) ---
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios";
        // Usamos el RowMapper para convertir cada fila en un objeto Usuario
        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    // --- READ (SQL SELECT ONE) ---
    public Optional<Usuario> findById(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        try {
            // queryForObject espera exactamente un resultado.
            Usuario usuario = jdbcTemplate.queryForObject(sql, new Object[]{username}, usuarioRowMapper);
            return Optional.of(usuario);
        } catch (EmptyResultDataAccessException e) {
            // Si no encuentra nada, queryForObject lanza una excepción. La atrapamos.
            return Optional.empty();
        }
    }

    // --- UPDATE (SQL UPDATE) ---
    public Usuario update(String username, Usuario usuario) {
        String sql = "UPDATE usuarios SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, " +
                     "segundo_apellido = ?, fecha_nacimiento = ?, correo = ?, celular = ?, contrasenia = ? " +
                     "WHERE username = ?";
        
        jdbcTemplate.update(sql,
                usuario.getPrimerNombre(),
                usuario.getSegundoNombre(),
                usuario.getPrimerApellido(),
                usuario.getSegundoApellido(),
                usuario.getFechaNacimiento(),
                usuario.getCorreo(),
                usuario.getCelular(),
                usuario.getContrasenia(),
                username // El 'username' va al final para el WHERE
        );
        return usuario;
    }

    // --- DELETE (SQL DELETE) ---
    public void deleteById(String username) {
        String sql = "DELETE FROM usuarios WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    // --- Helper (SQL SELECT COUNT) ---
    public boolean existsById(String username) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ?";
        // queryForObject puede devolver un solo valor, como un número.
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        return count != null && count > 0;
    }
}