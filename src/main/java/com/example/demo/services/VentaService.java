package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Venta;

public interface VentaService {
    Integer iniciarVenta(Integer idUsuario);
    void agregarDetalle(Integer idVenta, Integer idProducto, Integer cantidad);
    void eliminarDetalle(Integer idVenta, Integer idDetalle);
    Venta getVentaConDetalles(Integer idVenta);
    Double calcularTotal(Integer idVenta);
    void confirmarVenta(Integer idVenta);
    void cancelarVenta(Integer idVenta);
    List<Venta> getAllVentas();
}
