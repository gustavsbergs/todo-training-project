package com.bergsgustavs.todotrainingproject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Value("${rest.url}")
    private String restUrl;

    @GetMapping("/home")
    public String home(final ModelMap model) {
        model.addAttribute("restUrl", restUrl);
        return "home";
    }
}
