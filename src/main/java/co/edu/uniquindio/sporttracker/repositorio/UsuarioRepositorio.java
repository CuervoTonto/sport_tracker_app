package co.edu.uniquindio.sporttracker.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.sporttracker.mapper.UsuarioMapper;
import co.edu.uniquindio.sporttracker.modelo.Usuario;

@Repository
public class UsuarioRepositorio {
    private final JdbcTemplate plantilla;

    @Autowired
    public UsuarioRepositorio(JdbcTemplate jdbcTemplate) {
        this.plantilla = jdbcTemplate;
    }
    
    /**
     * @param usuario la informacion de usaurio a insertar
     * 
     * @return numero de registros guardados
     */
    public void crear(Usuario usuario) {
        String sql =
            "INSERT INTO usuarios ("
                + "username"
                + ", primer_nombre"
                + ", segundo_nombre"
                + ", primer_apellido"
                + ", segundo_apellido"
                + ", fecha_nacimiento"
                + ", correo"
                + ", celular"
                + ", contrasenia"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        ;

        this.plantilla.update(
            sql,
            usuario.getUsername(),
            usuario.getPrimerNombre(),
            usuario.getSegundoNombre(),
            usuario.getPrimerApellido(),
            usuario.getSegundoApellido(),
            usuario.getFechaNacimiento(),
            usuario.getCorreo(),
            usuario.getCelular(),
            usuario.getClave()
        );
    }
    
    /**
     * @param username username del usuario a actualizar
     * @param usuario nueva informacion del usuario
     * 
     * @return numero de usuarios actualizados
     */
    public int actualizar(String username, Usuario usuario) {
        String sql =
            "UPDATE usuarios SET"
                + " username = ?"
                + ", primer_nombre ?"
                + ", segundo_nombre = ?"
                + ", primer_apellido = ?"
                + ", segundo_apellido = ?"
                + ", fecha_nacimiento = ?"
                + ", correo = ?"
                + ", celular = ?"
                + ", contrasenia = ?"
            + ") WHERE username = ?"
        ;

        return this.plantilla.update(
            sql,
            usuario.getUsername(),
            usuario.getPrimerNombre(),
            usuario.getSegundoNombre(),
            usuario.getPrimerApellido(),
            usuario.getSegundoApellido(),
            usuario.getFechaNacimiento(),
            usuario.getCorreo(),
            usuario.getCelular(),
            usuario.getClave(),
            username
        );
    }
    
    /**
     * @param username username del usuario a eliminar
     *
     * @return numero de usuarios eliminados
     */
    public int eliminar(String username) {
        String sql = "DELETE usuarios FROM usuarios WHERE username = ?";
        
        return this.plantilla.update(sql, username);
    }
    
    /**
     * tiene todos los usuarios
     *
     * @return usuarios almacenados
     */
    public List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM usuarios";
        
        return this.plantilla.query(sql, new UsuarioMapper());
    }
}
