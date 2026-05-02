package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/gestion")
    public String gestion() {
        return "redirect:/usuario";
    }

    @GetMapping("/publicidad")
    public String publicidad() {
        return "general/publicidad";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "general/contacto";
    }

    @GetMapping("/hello")
    public String HelloWorld(){
        return "hello";
    }

    @GetMapping("/principal")
    public String principal(){
        return "principal";
    }
}
