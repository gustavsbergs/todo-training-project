package com.bergsgustavs.todotrainingproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/home")
    public String helloWorld(Model model){
        model.addAttribute("home", model);
        return "home";
    }
}
