package com.ojodev.cookinghero.recipes.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/swagger", "/api"})
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
