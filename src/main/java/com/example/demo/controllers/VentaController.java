package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

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
        // Calcular total automáticamente
        Double totalVenta = 0.0;
        if (ventaActiva != null && ventaActivaId != null) {
            totalVenta = ventaService.calcularTotal(ventaActivaId);
        }
        model.addAttribute("totalVenta", totalVenta);
        model.addAttribute("ventaActivaId", ventaActivaId);
        return "venta/ventas";
    }

    @GetMapping("/ventas/historial")
    public String historial(Model model, HttpSession session) {
        java.util.List<com.example.demo.model.Venta> ventas = ventaService.getAllVentas();
        // cargar detalles para cada venta
        java.util.List<com.example.demo.model.Venta> ventasConDetalles = new java.util.ArrayList<>();
        for (com.example.demo.model.Venta v : ventas) {
            com.example.demo.model.Venta full = ventaService.getVentaConDetalles(v.getId());
            ventasConDetalles.add(full != null ? full : v);
        }
        model.addAttribute("ventas", ventasConDetalles);

        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        model.addAttribute("ventaActivaId", ventaActivaId);
        return "venta/historial";
    }

    @GetMapping("/ventas/boleta/{id}")
    public String boleta(@PathVariable Integer id, Model model) {
        Venta venta = ventaService.getVentaConDetalles(id);
        if (venta == null) {
            return "redirect:/ventas/historial";
        }
        model.addAttribute("venta", venta);
        return "venta/boleta";
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

    @PostMapping("/ventas/continuar")
    public String continuarVenta(@RequestParam Integer idVenta, HttpSession session) {
        session.setAttribute("ventaActivaId", idVenta);
        return "redirect:/ventas";
    }

    @PostMapping("/ventas/confirmar/{id}")
    public String confirmarVentaById(@PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            ventaService.confirmarVenta(id);
            Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
            if (ventaActivaId != null && ventaActivaId.equals(id)) {
                session.removeAttribute("ventaActivaId");
            }
            return "redirect:/ventas/boleta/" + id;
        } catch (IllegalStateException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("detalleError", e.getMessage());
            return "redirect:/ventas/historial";
        }
    }

    @PostMapping("/ventas/cancelar/{id}")
    public String cancelarVentaById(@PathVariable Integer id, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            ventaService.cancelarVenta(id);
            Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
            if (ventaActivaId != null && ventaActivaId.equals(id)) {
                session.removeAttribute("ventaActivaId");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("detalleError", e.getMessage());
        }
        return "redirect:/ventas/historial";
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
            // Calcular total automáticamente después de agregar detalle
            ventaService.calcularTotal(ventaActivaId);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("detalleError", e.getMessage());
        }
        return "redirect:/ventas";
    }

    @PostMapping("/ventas/detalle/eliminar")
    public String eliminarDetalle(@RequestParam Integer idDetalle,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        if (ventaActivaId == null) {
            return "redirect:/ventas";
        }

        try {
            ventaService.eliminarDetalle(ventaActivaId, idDetalle);
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("detalleError", e.getMessage());
        }
        return "redirect:/ventas";
    }

    @PostMapping("/ventas/confirmar")
    public String confirmarVenta(HttpSession session, RedirectAttributes redirectAttributes) {
        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        if (ventaActivaId == null) {
            return "redirect:/ventas";
        }

        try {
            ventaService.confirmarVenta(ventaActivaId);
            session.removeAttribute("ventaActivaId");
            return "redirect:/ventas/boleta/" + ventaActivaId;
        } catch (IllegalStateException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("detalleError", e.getMessage());
            return "redirect:/ventas";
        }
    }

    @PostMapping("/ventas/cancelar")
    public String cancelarVenta(HttpSession session, RedirectAttributes redirectAttributes) {
        Integer ventaActivaId = (Integer) session.getAttribute("ventaActivaId");
        if (ventaActivaId == null) {
            return "redirect:/ventas";
        }

        try {
            ventaService.cancelarVenta(ventaActivaId);
            session.removeAttribute("ventaActivaId");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("detalleError", e.getMessage());
        }
        return "redirect:/ventas";
    }
}
