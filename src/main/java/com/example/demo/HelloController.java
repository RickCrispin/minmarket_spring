package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.CategoriaService;
import com.example.demo.services.ProductoService;

@Controller
public class HelloController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public HelloController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/ventas")
    public String ventas(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("carrito", java.util.List.of());
        model.addAttribute("totalCarrito", 0.0);
        return "venta/ventas";
    }

    @GetMapping("/gestion")
    public String gestion(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("categorias", categoriaService.getAllCategorias());
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

    @GetMapping("/principal")
    public String principal(){
        return "principal";
    }
}
