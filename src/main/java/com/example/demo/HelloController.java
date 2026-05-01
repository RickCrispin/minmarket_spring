package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/ventas")
    public String ventas() {
        return "venta/main";
    }

    @GetMapping("/gestion")
    public String gestion() {
        return "producto/gestion";
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
}
