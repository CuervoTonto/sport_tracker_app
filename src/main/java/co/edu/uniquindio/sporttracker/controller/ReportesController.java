package co.edu.uniquindio.sporttracker.controller;


import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

import co.edu.uniquindio.sporttracker.dao.ReporteAvanzadoDAO;
import co.edu.uniquindio.sporttracker.servicios.GeneradorPDF;

@Controller
@RequestMapping(path = "/reportes")
public class ReportesController {
    @Autowired
    private ReporteAvanzadoDAO repositorio;

    @Autowired
    private GeneradorPDF generadorPDF;

    @GetMapping(path = "")
    public String inicio() {
        return "pages/reportes/inicio";
    }

    @GetMapping(path = "/asistencias-competencias")
    public ResponseEntity<?> eventosAsistencia() throws IOException {
        Context context = new Context();

        context.setVariable("items", repositorio.eventosAsistencia());
        context.setVariable("fechaActual", new Date());

        byte[] pdfBytes = generadorPDF.generarPDFDesdeHTML(
            "pages/reportes/avanzados/competencias-asistencias",
            context
        );

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
    }

    @GetMapping(path = "/top-grupo-usuarios")
    public ResponseEntity<?> usuariosGrupoGrande() throws IOException {
        // model.addAttribute("items", repositorio.eventosAsistencia());
        Context context = new Context();

        context.setVariable("items", repositorio.usuariosGrupo());
        context.setVariable("fechaActual", new Date());

        byte[] pdfBytes = generadorPDF.generarPDFDesdeHTML(
            "pages/reportes/avanzados/usuarios-grupo-grande",
            context
        );

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
    }

    @GetMapping(path = "/top-grupos")
    public ResponseEntity<?> grupoMiembros() throws IOException {
        // model.addAttribute("items", repositorio.eventosAsistencia());
        Context context = new Context();

        context.setVariable("items", repositorio.gruposPopulares());
        context.setVariable("fechaActual", new Date());

        byte[] pdfBytes = generadorPDF.generarPDFDesdeHTML(
            "pages/reportes/avanzados/top-grupos",
            context
        );

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
    }
}
