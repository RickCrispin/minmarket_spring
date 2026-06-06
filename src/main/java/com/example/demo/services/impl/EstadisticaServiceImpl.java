package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;
import com.example.demo.repositories.EstadisticaDAO;
import com.example.demo.services.EstadisticaService;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    private final EstadisticaDAO estadisticaDAO;

    public EstadisticaServiceImpl(EstadisticaDAO estadisticaDAO) {
        this.estadisticaDAO = estadisticaDAO;
    }

    @Override
    public List<ProductoVendido> getTopProductosVendidos(int limit) {
        return estadisticaDAO.getTopProductosVendidos(limit);
    }

    @Override
    public List<VentaMensual> getVentasMensuales(int monthsBack) {
        return estadisticaDAO.getVentasMensuales(monthsBack);
    }

    @Override
    public java.util.List<com.example.demo.model.DiaVenta> getVentasPorDiaSemana(int monthsBack) {
        return estadisticaDAO.getVentasPorDiaSemana(monthsBack);
    }

    @Override
    public java.util.List<com.example.demo.model.TicketPromedioMensual> getTicketPromedioMensual(int monthsBack) {
        return estadisticaDAO.getTicketPromedioMensual(monthsBack);
    }

    @Override
    public java.util.List<com.example.demo.model.CategoriaIngresos> getTopCategoriasPorIngresos(int monthsBack, int limit) {
        return estadisticaDAO.getTopCategoriasPorIngresos(monthsBack, limit);
    }

    @Override
    public java.util.List<ProductoVendido> getProductosMenosVendidos(int limit) {
        return estadisticaDAO.getProductosMenosVendidos(limit);
    }

    @Override
    public java.util.List<VentaMensual> getVentasPromedioDiarioMensual(int monthsBack) {
        return estadisticaDAO.getVentasPromedioDiarioMensual(monthsBack);
    }

    @Override
    public java.util.List<VentaMensual> getVentasMensualesPorCategoria(int monthsBack, int categoriaId) {
        return estadisticaDAO.getVentasMensualesPorCategoria(monthsBack, categoriaId);
    }

    @Override
    public java.util.List<VentaMensual> getCantidadMensualPorProducto(int monthsBack, int productoId) {
        return estadisticaDAO.getCantidadMensualPorProducto(monthsBack, productoId);
    }
}
