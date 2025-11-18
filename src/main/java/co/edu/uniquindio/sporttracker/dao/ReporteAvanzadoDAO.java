package co.edu.uniquindio.sporttracker.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.sporttracker.dto.CompetenciaAsistencia;
import co.edu.uniquindio.sporttracker.dto.UsuarioReporteDTO;
import co.edu.uniquindio.sporttracker.maapper.CompetenciaAsistenciaMapper;
import co.edu.uniquindio.sporttracker.maapper.GrupoMiembroMapper;
import co.edu.uniquindio.sporttracker.maapper.UsuarioReporteMapper;

@Repository
public class ReporteAvanzadoDAO {
    @Autowired
    private JdbcTemplate template;

    // eventos con mas asistencia que el promedio
    public List<CompetenciaAsistencia> eventosAsistencia() {
        String query = ("""
            SELECT c.titulo, COUNT(comps.usuario_username) AS asistencias
            FROM competencias AS c
            INNER JOIN competidores AS comps ON c.titulo = comps.competencia_titulo
            GROUP BY c.titulo
            HAVING COUNT(comps.usuario_username) > (
                SELECT AVG(asistencias) 
                FROM (
                    SELECT comps.competencia_titulo AS competencia, COUNT(comps.usuario_username) AS asistencias
                    FROM competidores AS comps
                    GROUP BY comps.competencia_titulo
                ) AS sub
            )
        """);

        return template.query(query, new CompetenciaAsistenciaMapper());
    }

    // grupos con mas miembros que el promedio
    public List<?> gruposPopulares() {
        String query = """
            SELECT g.nombre, COUNT(gu.usuario_username) as num_usuarios
            FROM grupos AS g
            INNER JOIN grupo_usuarios AS gu ON gu.grupo_nombre = g.nombre
            GROUP BY g.nombre
            HAVING num_usuarios > (
                SELECT AVG(num_usuarios)
                FROM (
                    SELECT g.nombre, COUNT(gu.usuario_username) as num_usuarios
                    FROM grupos AS g
                    INNER JOIN grupo_usuarios AS gu ON gu.grupo_nombre = g.nombre
                    GROUP BY g.nombre
                ) as sub
            )
            ORDER BY num_usuarios DESC
            LIMIT 5
        """;

        return template.query(query, new GrupoMiembroMapper());
    }

    // usuarios del grupo mas grande
    public List<UsuarioReporteDTO> usuariosGrupo() {
        String query = """
            SELECT
                u.username,
                CONCAT(u.primer_nombre, ' ', u.segundo_nombre) AS nombres,
                CONCAT(u.primer_apellido, ' ', u.segundo_apellido) AS apellidos,
                u.correo,
                u.celular
            FROM usuarios AS u
            WHERE u.username IN (
                SELECT gu.usuario_username
                FROM grupo_usuarios AS gu
                WHERE gu.grupo_nombre = (
                    SELECT gu.grupo_nombre
                    FROM (
                        SELECT g.nombre, COUNT(gu.usuario_username) as num_usuarios
                        FROM grupos AS g
                        INNER JOIN grupo_usuarios AS gu ON gu.grupo_nombre = g.nombre
                        GROUP BY g.nombre
                        ORDER BY num_usuarios DESC
                        LIMIT 1
                    ) AS comps
                )
            )
        """;

        return template.query(query, new UsuarioReporteMapper());
    }
}
