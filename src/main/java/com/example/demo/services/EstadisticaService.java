package com.example.demo.services;

import java.util.List;
import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;

public interface EstadisticaService {
    List<ProductoVendido> getTopProductosVendidos(int limit);
    List<VentaMensual> getVentasMensuales(int monthsBack);
    java.util.List<com.example.demo.model.DiaVenta> getVentasPorDiaSemana(int monthsBack);
    java.util.List<com.example.demo.model.TicketPromedioMensual> getTicketPromedioMensual(int monthsBack);
    java.util.List<com.example.demo.model.CategoriaIngresos> getTopCategoriasPorIngresos(int monthsBack, int limit);
    java.util.List<ProductoVendido> getProductosMenosVendidos(int limit);
    java.util.List<VentaMensual> getVentasPromedioDiarioMensual(int monthsBack);
    java.util.List<VentaMensual> getVentasMensualesPorCategoria(int monthsBack, int categoriaId);
    java.util.List<VentaMensual> getCantidadMensualPorProducto(int monthsBack, int productoId);
}
