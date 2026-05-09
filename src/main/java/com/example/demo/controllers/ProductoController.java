package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Producto;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.ProductoService;

@Controller
public class ProductoController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/producto")
    public String producto(Model model) {
        model.addAttribute("productos", productoService.getAllProductosAdmin());
        return "producto/productos";
    }

    @GetMapping("/producto/add")
    public String addProductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "producto/formProductoAdd";
    }

    @PostMapping("/producto")
    public String addProducto(@ModelAttribute Producto producto) {
        productoService.addProducto(producto);
        return "redirect:/producto";
    }

    @GetMapping("/producto/edit/{id}")
    public String editProducto(@PathVariable int id, Model model) {
        Producto producto = productoService.getProductoById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "producto/formProducto";
    }

    @PostMapping("/producto/edit/{id}")
    public String updateProducto(@PathVariable int id, @ModelAttribute Producto producto) {
        producto.setId(id);
        productoService.updateProducto(producto);
        return "redirect:/producto";
    }

    @PostMapping("/producto/{id}")
    public String desactivarProducto(@PathVariable int id) {
        productoService.desactivarProducto(id);
        return "redirect:/producto";
    }
}
