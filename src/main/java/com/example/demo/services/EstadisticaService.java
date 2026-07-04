package com.example.demo.services;

import java.util.List;

import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;

public interface EstadisticaService {
    List<ProductoVendido> getTopProductosVendidos(int limit);
    List<VentaMensual> getVentasMensuales(int monthsBack);
    List<com.example.demo.model.DiaVenta> getVentasPorDiaSemana(int monthsBack);
    List<com.example.demo.model.TicketPromedioMensual> getTicketPromedioMensual(int monthsBack);
    List<com.example.demo.model.CategoriaIngresos> getTopCategoriasPorIngresos(int monthsBack, int limit);
    List<ProductoVendido> getProductosMenosVendidos(int limit);
    List<VentaMensual> getVentasPromedioDiarioMensual(int monthsBack);
    List<VentaMensual> getVentasMensualesPorCategoria(int monthsBack, int categoriaId);
    List<VentaMensual> getCantidadMensualPorProducto(int monthsBack, int productoId);
    List<com.example.demo.model.CategoriaMes> getTopCategoriaPorMes(int monthsBack);
}
