package co.edu.uniquindio.sporttracker.servicios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;


@Service
public class GeneradorPDF {
    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generarPDFDesdeHTML(String plantilla, Context contexto) throws IOException {
        String html = templateEngine.process(plantilla, contexto);

        String baseUrl = this.getClass().getClassLoader().getResource("static").toExternalForm();

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, baseUrl); 
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }
    
}
