package co.edu.uniquindio.sporttracker.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.sporttracker.dto.usuario.CrearUsuarioPeticion;
import co.edu.uniquindio.sporttracker.modelo.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {
    @Override
    @Nullable
    public Usuario mapRow(@NonNull ResultSet source, int rowNum) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsername(source.getString("username"));
        usuario.setPrimerNombre(source.getString("primer_nombre"));
        usuario.setSegundoNombre(source.getString("segundo_nombre"));
        usuario.setPrimerApellido(source.getString("primer_apellido"));
        usuario.setSegundoApellido(source.getString("segundo_apellido"));
        usuario.setFechaNacimiento(LocalDate.parse(source.getString("fecha_nacimiento")));
        usuario.setCorreo(source.getString("correo"));
        usuario.setCelular(source.getString("celular"));
        usuario.setClave(source.getString("contrasenia"));
        
        return usuario;
    }
    
    public Usuario desdePeticion(CrearUsuarioPeticion peticion) {
        Usuario usuario = new Usuario();
        usuario.setUsername(peticion.getUsername());
        usuario.setPrimerNombre(peticion.getPrimerNombre());
        usuario.setSegundoNombre(peticion.getSegundoNombre());
        usuario.setPrimerApellido(peticion.getPrimerApellido());
        usuario.setSegundoApellido(peticion.getSegundoApellido());
        usuario.setFechaNacimiento(peticion.getFechaNacimiento());
        usuario.setCorreo(peticion.getCorreo());
        usuario.setCelular(peticion.getCelular());
        usuario.setClave(peticion.getClave());
        
        return usuario;
    }
}