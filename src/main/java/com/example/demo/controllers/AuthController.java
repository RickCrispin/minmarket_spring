package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.services.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(String username, String password, HttpSession session, Model model) {
        var usuario = authService.authUsuario(username, password);
        if (usuario != null) {
            session.setAttribute("userLogged", usuario);
            return "redirect:/ventas";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
