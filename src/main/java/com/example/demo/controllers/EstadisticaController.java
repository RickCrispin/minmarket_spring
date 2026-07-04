package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CategoriaIngresos;
import com.example.demo.model.CategoriaMes;
import com.example.demo.model.DiaVenta;
import com.example.demo.model.ProductoVendido;
import com.example.demo.model.TicketPromedioMensual;
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

        int safeTop = normalizePositiveOrDefault(top, 10);
        int safeMonths = normalizePositiveOrDefault(months, 12);

        List<ProductoVendido> topProductos = estadisticaService.getTopProductosVendidos(safeTop);
        List<VentaMensual> ventasMensuales = estadisticaService.getVentasMensuales(safeMonths);
        List<DiaVenta> ventasPorDia = estadisticaService.getVentasPorDiaSemana(safeMonths);
        List<TicketPromedioMensual> ticketPromedio = estadisticaService.getTicketPromedioMensual(safeMonths);
        List<CategoriaIngresos> topCategorias = estadisticaService.getTopCategoriasPorIngresos(safeMonths, safeTop);
        List<ProductoVendido> productosMenosVendidos = estadisticaService.getProductosMenosVendidos(safeTop);
        List<VentaMensual> promedioDiarioMensual = estadisticaService.getVentasPromedioDiarioMensual(safeMonths);

        // mapear el ticket promedio en VentaMensual
        List<VentaMensual> ticketPromedioAsVenta = new ArrayList<>();
        for (TicketPromedioMensual t : ticketPromedio) {
            VentaMensual v = new VentaMensual();
            v.setAno(t.getAno());
            v.setMes(t.getMes());
            v.setTotal(t.getPromedio());
            ticketPromedioAsVenta.add(v);
        }

        // Serie de tiempo para categoria (ingresos mensuales)
        List<VentaMensual> categoriaSerie = new ArrayList<>();
        if (!topCategorias.isEmpty()) {
            Integer idCat = topCategorias.get(0).getIdCategoria();
            if (idCat != null) {
                categoriaSerie = estadisticaService.getVentasMensualesPorCategoria(safeMonths, idCat);
            }
        }
        // Obtener un top de categorais por mes
        List<CategoriaMes> topCategoriaPorMes = estadisticaService.getTopCategoriaPorMes(safeMonths);
        List<VentaMensual> productoSerie = new ArrayList<>();
        if (!productosMenosVendidos.isEmpty()) {
            Integer idProd = productosMenosVendidos.get(0).getIdProducto();
            if (idProd != null) {
                productoSerie = estadisticaService.getCantidadMensualPorProducto(safeMonths, idProd);
            }
        }

  
        double overallMax = computeOverallMax(ventasMensuales, promedioDiarioMensual, ticketPromedioAsVenta, categoriaSerie, productoSerie);
        if (overallMax <= 0.0) overallMax = 1.0;

        // calcular máximo para normalizar la serie temporal (evitar división por cero)
        double maxTotal = ventasMensuales.stream()
            .mapToDouble(this::getTotalValue)
            .max()
            .orElse(0.0);
        if (maxTotal <= 0.0) {
            maxTotal = 1.0;
        }

        model.addAttribute("topProductos", topProductos);
        model.addAttribute("ventasMensuales", ventasMensuales);
        model.addAttribute("ventasSeriePath", buildSeriesPath(ventasMensuales, maxTotal));
        model.addAttribute("ventasSerieAreaPath", buildSeriesAreaPath(ventasMensuales, maxTotal));
        model.addAttribute("ventasSeriePoints", buildSeriesPoints(ventasMensuales, maxTotal));
        model.addAttribute("ventasSerieYTicks", buildYAxisTicks(maxTotal));
        model.addAttribute("ventasSerieXAxisLabel", "Mes / Año");
        model.addAttribute("ventasSerieYAxisLabel", "Ventas (S/.)");
        model.addAttribute("ventasSerieMaxLabel", String.format(Locale.US, "%.2f", maxTotal));
        model.addAttribute("activePage", "estadisticas");
        model.addAttribute("headerTitle", "Estadísticas");
        model.addAttribute("ventasPorDia", ventasPorDia);
        model.addAttribute("ticketPromedioMensual", ticketPromedio);
        model.addAttribute("topCategoriasIngresos", topCategorias);
        model.addAttribute("productosMenosVendidos", productosMenosVendidos);

        // Agregar series de tiempo para las otras métricas
        model.addAttribute("promedioDiarioSeriePath", buildSeriesPath(promedioDiarioMensual, overallMax));
        model.addAttribute("promedioDiarioSerieAreaPath", buildSeriesAreaPath(promedioDiarioMensual, overallMax));
        model.addAttribute("promedioDiarioSeriePoints", buildSeriesPoints(promedioDiarioMensual, overallMax));

        model.addAttribute("ticketPromedioSeriePath", buildSeriesPath(ticketPromedioAsVenta, overallMax));
        model.addAttribute("ticketPromedioSerieAreaPath", buildSeriesAreaPath(ticketPromedioAsVenta, overallMax));
        model.addAttribute("ticketPromedioSeriePoints", buildSeriesPoints(ticketPromedioAsVenta, overallMax));

        model.addAttribute("categoriaSeriePath", buildSeriesPath(categoriaSerie, overallMax));
        model.addAttribute("categoriaSerieAreaPath", buildSeriesAreaPath(categoriaSerie, overallMax));
        model.addAttribute("categoriaSeriePoints", buildSeriesPointsWithCategory(categoriaSerie, overallMax, topCategoriaPorMes));

        model.addAttribute("productoSeriePath", buildSeriesPath(productoSerie, overallMax));
        model.addAttribute("productoSerieAreaPath", buildSeriesAreaPath(productoSerie, overallMax));
        model.addAttribute("productoSeriePoints", buildSeriesPoints(productoSerie, overallMax));

        return "estadisticas/estadisticas";
    }

    private int normalizePositiveOrDefault(int value, int defaultValue) {
        return value > 0 ? value : defaultValue;
    }

    private List<Map<String, Object>> buildSeriesPoints(List<VentaMensual> ventasMensuales, double maxTotal) {
        List<Map<String, Object>> points = new ArrayList<>();
        if (ventasMensuales.isEmpty()) {
            return points;
        }

        for (int index = 0; index < ventasMensuales.size(); index++) {
            VentaMensual ventaMensual = ventasMensuales.get(index);
            double total = getTotalValue(ventaMensual);
            double x = calculateSeriesX(index, ventasMensuales.size());
            double y = calculateSeriesY(total, maxTotal);
            Integer mesValue = ventaMensual.getMes();
            Integer anoValue = ventaMensual.getAno();
            String mesLabel = mesValue == null ? "00" : (mesValue < 10 ? "0" + mesValue : String.valueOf(mesValue));
            String anoLabel = anoValue == null ? "0" : String.valueOf(anoValue);

            Map<String, Object> point = new LinkedHashMap<>();
            point.put("x", x);
            point.put("y", y);
            point.put("label", mesLabel + "/" + anoLabel);
            point.put("total", total);
            points.add(point);
        }

        return points;
    }

    private List<Map<String, Object>> buildSeriesPointsWithCategory(List<VentaMensual> ventasMensuales, double maxTotal, List<CategoriaMes> categoriasPorMes) {
        List<Map<String, Object>> points = new ArrayList<>();
        if (ventasMensuales.isEmpty()) {
            return points;
        }

        for (int index = 0; index < ventasMensuales.size(); index++) {
            VentaMensual ventaMensual = ventasMensuales.get(index);
            double total = getTotalValue(ventaMensual);
            double x = calculateSeriesX(index, ventasMensuales.size());
            double y = calculateSeriesY(total, maxTotal);
            Integer mesValue = ventaMensual.getMes();
            Integer anoValue = ventaMensual.getAno();
            String mesLabel = mesValue == null ? "00" : (mesValue < 10 ? "0" + mesValue : String.valueOf(mesValue));
            String anoLabel = anoValue == null ? "0" : String.valueOf(anoValue);

            Map<String, Object> point = new LinkedHashMap<>();
            point.put("x", x);
            point.put("y", y);
            point.put("label", mesLabel + "/" + anoLabel);
            point.put("total", total);

            // Nombre de categoria por mes
            String catName = null;
            if (categoriasPorMes != null && !categoriasPorMes.isEmpty()) {
                for (CategoriaMes cm : categoriasPorMes) {
                    if (cm.getAno() != null && cm.getMes() != null && cm.getAno().equals(anoValue) && cm.getMes().equals(mesValue)) {
                        catName = cm.getNombreCategoria();
                        break;
                    }
                }
            }
            point.put("category", catName == null ? "" : catName);
            points.add(point);
        }

        return points;
    }

    private List<Map<String, Object>> buildYAxisTicks(double maxTotal) {
        List<Map<String, Object>> ticks = new ArrayList<>();
        double chartTop = 24.0;
        double chartBottom = 252.0;
        double chartHeight = chartBottom - chartTop;
        double[] percentages = { 1.0, 0.75, 0.5, 0.25, 0.0 };

        for (double percentage : percentages) {
            double value = maxTotal * percentage;
            double y = chartTop + (chartHeight * (1.0 - percentage));

            Map<String, Object> tick = new LinkedHashMap<>();
            tick.put("y", y);
            tick.put("label", String.format(Locale.US, "S/. %.2f", value));
            ticks.add(tick);
        }

        return ticks;
    }

    private String buildSeriesPath(List<VentaMensual> ventasMensuales, double maxTotal) {
        if (ventasMensuales.isEmpty()) {
            return "";
        }

        StringBuilder path = new StringBuilder();
        for (int index = 0; index < ventasMensuales.size(); index++) {
            VentaMensual ventaMensual = ventasMensuales.get(index);
            double total = getTotalValue(ventaMensual);
            double x = calculateSeriesX(index, ventasMensuales.size());
            double y = calculateSeriesY(total, maxTotal);

            if (index == 0) {
                path.append(String.format(Locale.US, "M %.2f %.2f", x, y));
            } else {
                path.append(String.format(Locale.US, " L %.2f %.2f", x, y));
            }
        }
        return path.toString();
    }

    private String buildSeriesAreaPath(List<VentaMensual> ventasMensuales, double maxTotal) {
        if (ventasMensuales.isEmpty()) {
            return "";
        }

        double chartBottom = 300.0 - 48.0;
        StringBuilder areaPath = new StringBuilder();
        double startX = calculateSeriesX(0, ventasMensuales.size());
        double startY = calculateSeriesY(getTotalValue(ventasMensuales.get(0)), maxTotal);
        areaPath.append(String.format(Locale.US, "M %.2f %.2f", startX, chartBottom));
        areaPath.append(String.format(Locale.US, " L %.2f %.2f", startX, startY));

        for (int index = 1; index < ventasMensuales.size(); index++) {
            VentaMensual ventaMensual = ventasMensuales.get(index);
            double total = getTotalValue(ventaMensual);
            double x = calculateSeriesX(index, ventasMensuales.size());
            double y = calculateSeriesY(total, maxTotal);
            areaPath.append(String.format(Locale.US, " L %.2f %.2f", x, y));
        }

        double endX = calculateSeriesX(ventasMensuales.size() - 1, ventasMensuales.size());
        areaPath.append(String.format(Locale.US, " L %.2f %.2f Z", endX, chartBottom));
        return areaPath.toString();
    }

    private double calculateSeriesX(int index, int totalPoints) {
        double chartLeft = 56.0;
        double chartRight = 24.0;
        double chartWidth = 720.0 - chartLeft - chartRight;
        if (totalPoints <= 1) {
            return chartLeft + (chartWidth / 2.0);
        }
        return chartLeft + (chartWidth * index / (double) (totalPoints - 1));
    }

    private double calculateSeriesY(double total, double maxTotal) {
        double chartTop = 24.0;
        double chartBottom = 48.0;
        double chartHeight = 300.0 - chartTop - chartBottom;
        return chartTop + ((maxTotal - total) / maxTotal) * chartHeight;
    }

    private double getTotalValue(VentaMensual ventaMensual) {
        Double totalValue = ventaMensual.getTotal();
        if (totalValue == null) {
            return 0.0;
        }
        return totalValue;
    }

    // Calcular el valor máximo entre varias listas de VentaMensual
    // SafeVarargs sirve para indicar que el método acepta un número variable de argumentos de tipo List<VentaMensual>
    @SafeVarargs
    private double computeOverallMax(List<VentaMensual>... lists) {
        double max = 0.0;
        for (List<VentaMensual> list : lists) {
            if (list == null) continue;
            for (VentaMensual v : list) {
                double val = getTotalValue(v);
                if (val > max) max = val;
            }
        }
        return max;
    }
}
