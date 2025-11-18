package co.edu.uniquindio.sporttracker.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.sporttracker.dto.CompetenciasPorCiudadDTO;
import co.edu.uniquindio.sporttracker.dto.MetasActivasDTO;
import co.edu.uniquindio.sporttracker.dto.MiembrosPorGrupoDTO;
import co.edu.uniquindio.sporttracker.maapper.CompetenciasPorCiudadMapper;
import co.edu.uniquindio.sporttracker.maapper.MetasActivasMapper;
import co.edu.uniquindio.sporttracker.maapper.MiembrosPorGrupoMapper;

@Repository
public class ReporteSimpleDAO {

    @Autowired
    private JdbcTemplate template;

    // 1. Usuarios por ciudad de competencia
    public List<CompetenciasPorCiudadDTO> competenciasPorCiudad() {
        String query = """
            SELECT c.ciudad_nombre AS ciudad,
                   COUNT(*)        AS total_competencias
            FROM competencias c
            GROUP BY c.ciudad_nombre
            ORDER BY total_competencias DESC
        """;
        return template.query(query, new CompetenciasPorCiudadMapper());
    }

    // 2. Miembros por grupo
    public List<MiembrosPorGrupoDTO> miembrosPorGrupo() {
        String query = """
            SELECT g.nombre AS grupo,
                   COUNT(gu.usuario_username) AS miembros
            FROM grupos g
            LEFT JOIN grupo_usuarios gu ON gu.grupo_nombre = g.nombre
            GROUP BY g.nombre
            ORDER BY miembros DESC, g.nombre
        """;
        return template.query(query, new MiembrosPorGrupoMapper());
    }

    // 3. Metas activas por usuario
    public List<MetasActivasDTO> metasActivas() {
        String query = """
            SELECT m.usuario_username,
                   COUNT(*) AS metas_activas
            FROM metas m
            WHERE CURRENT_DATE BETWEEN m.fecha_inicio AND m.fecha_fin
            GROUP BY m.usuario_username
            ORDER BY metas_activas DESC
        """;
        return template.query(query, new MetasActivasMapper());
    }
}
