package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;
import com.example.demo.services.EstadisticaService;

@Controller
public class EstadisticaController {

    private final EstadisticaService estadisticaService;

    public EstadisticaController(EstadisticaService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    @GetMapping("/estadisticas")
    public String estadisticas(Model model,
            @RequestParam(name = "top", required = false, defaultValue = "10") int top,
            @RequestParam(name = "months", required = false, defaultValue = "12") int months) {

        List<ProductoVendido> topProductos = estadisticaService.getTopProductosVendidos(top);
        List<VentaMensual> ventasMensuales = estadisticaService.getVentasMensuales(months);

        // calcular máximo para normalizar el ancho de las barras (evitar división por cero)
        double maxTotal = ventasMensuales.stream()
            .mapToDouble(v -> v.getTotal() != null ? v.getTotal() : 0.0)
            .max()
            .orElse(0.0);
        if (maxTotal <= 0.0) {
            maxTotal = 1.0; // para que no haya división por cero en la vista
        }

        model.addAttribute("topProductos", topProductos);
        model.addAttribute("ventasMensuales", ventasMensuales);
        model.addAttribute("maxVentaTotal", maxTotal);
        model.addAttribute("activePage", "estadisticas");
        model.addAttribute("headerTitle", "Estadísticas");

        return "estadisticas/estadisticas";
    }
}
