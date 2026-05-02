package com.example.demo.services;

import com.example.demo.model.Venta;

public interface VentaService {
    Integer iniciarVenta(Integer idUsuario);
    void agregarDetalle(Integer idVenta, Integer idProducto, Integer cantidad);
    Venta getVentaConDetalles(Integer idVenta);
    Double calcularTotal(Integer idVenta);
    void confirmarVenta(Integer idVenta);
}
