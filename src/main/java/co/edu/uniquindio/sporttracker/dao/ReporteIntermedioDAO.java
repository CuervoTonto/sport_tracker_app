package co.edu.uniquindio.sporttracker.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
// IMPORTANTE: Debes crear estos DTOs y Mappers (puedes generarlos rápido con tu IDE)
import co.edu.uniquindio.sporttracker.dto.*; 
import co.edu.uniquindio.sporttracker.maapper.*;

@Repository
public class ReporteIntermedioDAO {

    @Autowired
    private JdbcTemplate template;

    public List<DistanciaAcumuladaDTO> distanciaAcumulada() {
        String query = """
            SELECT p.usuario_username,
                   SUM(p.kilometros_recorridos) AS km_totales,
                   ROUND(SUM(p.kilometros_recorridos) / NULLIF(DATEDIFF(MAX(p.fecha), MIN(p.fecha)) + 1, 0), 2) AS km_prom_dia
            FROM progresos p
            GROUP BY p.usuario_username
            ORDER BY km_totales DESC
        """;
        return template.query(query, new DistanciaAcumuladaMapper());
    }

    public List<AvanceMetasDTO> avanceMetas() {
        String query = """
            SELECT m.id, m.usuario_username, m.kilometros_objetivos, m.kilometros_actuales,
                   ROUND(100 * m.kilometros_actuales / NULLIF(m.kilometros_objetivos,0), 1) AS porc_avance
            FROM metas m
            ORDER BY porc_avance DESC
        """;
        return template.query(query, new AvanceMetasMapper());
    }

    public List<CompetenciaDetalleDTO> competenciaDetalle() {
        String query = """
            SELECT co.titulo, co.ciudad_nombre, ci.departamento_nombre, COUNT(cm.usuario_username) AS competidores
            FROM competencias co
            LEFT JOIN competidores cm ON cm.competencia_titulo = co.titulo
            LEFT JOIN ciudades ci ON ci.nombre = co.ciudad_nombre
            GROUP BY co.titulo, co.ciudad_nombre, ci.departamento_nombre
            ORDER BY competidores DESC, co.titulo
        """;
        return template.query(query, new CompetenciaDetalleMapper());
    }

    public List<ActividadGrupoDTO> actividadesPorGrupo() {
        String query = """
            SELECT a.grupo_nombre, COUNT(*) AS actividades_mes
            FROM actividades a
            WHERE a.fecha BETWEEN DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') AND LAST_DAY(CURRENT_DATE)
            GROUP BY a.grupo_nombre
            ORDER BY actividades_mes DESC
        """;
        return template.query(query, new ActividadGrupoMapper());
    }
}
