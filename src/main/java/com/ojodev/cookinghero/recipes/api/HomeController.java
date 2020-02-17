package com.ojodev.cookinghero.recipes.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping(value = {"/swagger", "/api"})
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
