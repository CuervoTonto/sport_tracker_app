package co.edu.uniquindio.sporttracker.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalAdvice {
    @ModelAttribute("route")
    public String route(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
