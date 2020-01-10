package com.ojodev.cookinghero.recipes.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/swagger", "/api"})
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
