package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Usuario;
import com.example.demo.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario")
    public String usuario(HttpSession session,Model model) {
        Usuario sessionUsuario = (Usuario) session.getAttribute("userLogged");
        if (sessionUsuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.getAllUsuarios(sessionUsuario.getId()));
        return "usuario/usuarios";
    }

    @GetMapping("/usuario/add")
    public String addUsuarioForm(HttpSession session, Model model) {
        Usuario sessionUsuario = (Usuario) session.getAttribute("userLogged");
        if (sessionUsuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuario/formUsuarioAdd";
    }

    @PostMapping("/usuario")
    public String addUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.addUsuario(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/usuario/edit/{id}")
    public String editUsuario(@PathVariable int id, Model model) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "usuario/formUsuario";
    }

    @PostMapping("/usuario/edit/{id}")
    public String updateUsuario(@PathVariable int id, @ModelAttribute Usuario usuario) {
        usuario.setId(id);
        usuarioService.updateUsuario(usuario);
        return "redirect:/usuario";
    }
}
