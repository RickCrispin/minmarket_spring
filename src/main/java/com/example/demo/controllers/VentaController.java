package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Usuario;
import com.example.demo.model.Venta;
import com.example.demo.services.ProductoService;
import com.example.demo.services.VentaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class VentaController {
    private final VentaService ventaService;
    private final ProductoService productoService;

    public VentaController(VentaService ventaService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @GetMapping("/ventas")
    public String ventas(Model model, HttpSession session) {
        model.addAttribute("productos", productoService.getAllProductos());

        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        Venta ventaActiva = null;
        if (ventaActivaId != null) {
            ventaActiva = ventaService.getVentaConDetalles(ventaActivaId);
            if (ventaActiva != null && "Concretado".equalsIgnoreCase(ventaActiva.getEstado())) {
                session.removeAttribute("ventaActivaId");
                ventaActiva = null;
                ventaActivaId = null;
            }
        }

        model.addAttribute("ventaActiva", ventaActiva);
        model.addAttribute("detallesVenta", ventaActiva != null ? ventaActiva.getDetalles() : java.util.List.of());
        model.addAttribute("totalVenta", ventaActiva != null && ventaActiva.getTotal() != null ? ventaActiva.getTotal() : 0.0);
        model.addAttribute("ventaActivaId", ventaActivaId);
        return "venta/ventas";
    }

    @PostMapping("/ventas/iniciar")
    public String iniciarVenta(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("userLogged");
        if (usuario == null) {
            return "redirect:/login";
        }

        Integer ventaId = ventaService.iniciarVenta(usuario.getId());
        session.setAttribute("ventaActivaId", ventaId);
        return "redirect:/ventas";
    }

    @PostMapping("/ventas/detalle")
    public String agregarDetalle(@RequestParam Integer idProducto,
                                 @RequestParam Integer cantidad,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        if (ventaActivaId == null) {
            return "redirect:/ventas";
        }

        try {
            ventaService.agregarDetalle(ventaActivaId, idProducto, cantidad);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("stockError", e.getMessage());
        }
        return "redirect:/ventas";
    }

    @PostMapping("/ventas/calcular")
    public String calcularTotal(HttpSession session) {
        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        if (ventaActivaId == null) {
            return "redirect:/ventas";
        }

        ventaService.calcularTotal(ventaActivaId);
        return "redirect:/ventas";
    }

    @PostMapping("/ventas/confirmar")
    public String confirmarVenta(HttpSession session) {
        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        if (ventaActivaId == null) {
            return "redirect:/ventas";
        }

        ventaService.confirmarVenta(ventaActivaId);
        session.removeAttribute("ventaActivaId");
        return "redirect:/ventas";
    }
}
