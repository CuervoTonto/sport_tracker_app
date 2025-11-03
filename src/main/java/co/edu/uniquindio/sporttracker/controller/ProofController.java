package co.edu.uniquindio.sporttracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProofController {
    @GetMapping
    public String proof() {
        return "proof";
    }
}
