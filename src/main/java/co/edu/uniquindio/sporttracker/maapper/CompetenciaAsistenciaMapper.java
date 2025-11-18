package co.edu.uniquindio.sporttracker.maapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import co.edu.uniquindio.sporttracker.dto.CompetenciaAsistencia;

public class CompetenciaAsistenciaMapper implements RowMapper<CompetenciaAsistencia> {
    @Override
    @Nullable
    public CompetenciaAsistencia mapRow(@NonNull ResultSet r, int arg1) throws SQLException {
        CompetenciaAsistencia ca = new CompetenciaAsistencia();

        ca.setAsistencias(r.getInt("asistencias"));
        ca.setTitulo(r.getString("titulo"));

        return ca;
    }
}
