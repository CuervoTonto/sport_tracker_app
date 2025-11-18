package co.edu.uniquindio.sporttracker.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

import co.edu.uniquindio.sporttracker.dao.ReporteAvanzadoDAO;
import co.edu.uniquindio.sporttracker.dao.ReporteSimpleDAO;
import co.edu.uniquindio.sporttracker.dao.ReporteIntermedioDAO; // Asegúrate de crear este DAO
import co.edu.uniquindio.sporttracker.servicios.GeneradorPDF;

@Controller
@RequestMapping(path = "/reportes")
public class ReportesController {

    @Autowired
    private ReporteAvanzadoDAO repositorioAvanzado; // DAO Original
    
    @Autowired
    private ReporteSimpleDAO repositorioSimple; // El que creamos hace un momento

    @Autowired
    private ReporteIntermedioDAO repositorioIntermedio; // El nuevo para los reportes intermedios

    @Autowired
    private GeneradorPDF generadorPDF; //

    @GetMapping(path = "")
    public String inicio() {
        return "pages/reportes/inicio"; //
    }

    // ==========================================
    // REPORTES AVANZADOS (Ya existentes)
    // ==========================================
    
    @GetMapping(path = "/asistencias-competencias")
    public ResponseEntity<?> eventosAsistencia() throws IOException {
        return generarReporte("Competencias Populares", repositorioAvanzado.eventosAsistencia(), "pages/reportes/avanzados/competencias-asistencias");
    }

    @GetMapping(path = "/top-grupo-usuarios")
    public ResponseEntity<?> usuariosGrupoGrande() throws IOException {
        return generarReporte("Usuarios del Grupo Más Grande", repositorioAvanzado.usuariosGrupo(), "pages/reportes/avanzados/usuarios-grupo-grande");
    }

    @GetMapping(path = "/top-grupos")
    public ResponseEntity<?> grupoMiembros() throws IOException {
        return generarReporte("Top Grupos Populares", repositorioAvanzado.gruposPopulares(), "pages/reportes/avanzados/top-grupos");
    }

    // ==========================================
    // REPORTES SIMPLES (Nuevos)
    // ==========================================

    @GetMapping(path = "/simple/competencias-ciudad")
    public ResponseEntity<?> reporteCompetenciasCiudad() throws IOException {
        return generarReporte("Competencias por Ciudad", repositorioSimple.competenciasPorCiudad(), "pages/reportes/simples/competencias-ciudad");
    }

    @GetMapping(path = "/simple/miembros-grupo")
    public ResponseEntity<?> reporteMiembrosGrupo() throws IOException {
        return generarReporte("Miembros por Grupo", repositorioSimple.miembrosPorGrupo(), "pages/reportes/simples/miembros-grupo");
    }

    @GetMapping(path = "/simple/metas-activas")
    public ResponseEntity<?> reporteMetasActivas() throws IOException {
        return generarReporte("Metas Activas por Usuario", repositorioSimple.metasActivas(), "pages/reportes/simples/metas-activas");
    }

    // ==========================================
    // REPORTES INTERMEDIOS (Nuevos)
    // ==========================================

    @GetMapping(path = "/intermedio/distancia")
    public ResponseEntity<?> reporteDistancia() throws IOException {
        return generarReporte("Distancia Acumulada por Usuario", repositorioIntermedio.distanciaAcumulada(), "pages/reportes/intermedios/distancia");
    }

    @GetMapping(path = "/intermedio/avance-metas")
    public ResponseEntity<?> reporteAvanceMetas() throws IOException {
        return generarReporte("Porcentaje de Avance de Metas", repositorioIntermedio.avanceMetas(), "pages/reportes/intermedios/avance-metas");
    }

    @GetMapping(path = "/intermedio/competencia-detalle")
    public ResponseEntity<?> reporteCompetenciaDetalle() throws IOException {
        return generarReporte("Detalle de Competidores por Competencia", repositorioIntermedio.competenciaDetalle(), "pages/reportes/intermedios/competencia-detalle");
    }

    @GetMapping(path = "/intermedio/actividades-grupo")
    public ResponseEntity<?> reporteActividadesGrupo() throws IOException {
        return generarReporte("Actividades Recientes por Grupo", repositorioIntermedio.actividadesPorGrupo(), "pages/reportes/intermedios/actividades-grupo");
    }

    // ==========================================
    // MÉTODO AUXILIAR (Para no repetir código)
    // ==========================================
    
    private ResponseEntity<?> generarReporte(String titulo, List<?> items, String plantilla) throws IOException {
        Context context = new Context();
        context.setVariable("tituloReporte", titulo);
        context.setVariable("items", items);
        context.setVariable("fechaActual", new Date());

        byte[] pdfBytes = generadorPDF.generarPDFDesdeHTML(plantilla, context);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
    }
}
