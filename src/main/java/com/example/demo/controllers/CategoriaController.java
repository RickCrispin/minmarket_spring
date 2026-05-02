package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Categoria;
import com.example.demo.services.CategoriaService;

@Controller
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categoria")
    public String categoria(Model model){
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        model.addAttribute("categoria", new Categoria());
        return "producto/categoria";
    }

    @PostMapping("/categoria")
    public String addCategoria(@ModelAttribute Categoria categoria){
        categoriaService.addCategoria(categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/categoria/edit/{id}")
    public String editCategoria(@PathVariable int id, Model model){
        Categoria categoria = categoriaService.getCategoriaById(id);
        model.addAttribute("categoria", categoria);
        return "producto/formCategoria";
    }

    @PostMapping("/categoria/edit/{id}")
    public String updateCategoria(@PathVariable int id, @ModelAttribute Categoria categoria){
        categoria.setId(id);
        categoriaService.updateCategoria(categoria);
        return "redirect:/categoria";
    }

    @PostMapping("/categoria/{id}")
    public String desactivarCategoria(@PathVariable int id){
        categoriaService.desactivarCategoria(id);
        return "redirect:/categoria";
    }
}
