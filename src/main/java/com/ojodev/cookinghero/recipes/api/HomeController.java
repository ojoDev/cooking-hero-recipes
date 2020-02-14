package com.ojodev.cookinghero.recipes.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.HttpMethod;

@Controller
public class HomeController {

    @RequestMapping(value = {"/swagger", "/api"}, method = RequestMethod.GET)
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
