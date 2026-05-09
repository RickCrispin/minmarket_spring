package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.DetalleVenta;

public interface DetalleVentaDAO {
    void addDetalleVenta(DetalleVenta detalleVenta);
    void updateDetalleVentaCantidad(Integer idVenta, Integer idProducto, Integer cantidad, Double precioUnitario);
    void deleteDetalleVenta(Integer idDetalle);
    DetalleVenta getDetalleById(Integer idDetalle);
    DetalleVenta getDetalleByVentaAndProducto(Integer idVenta, Integer idProducto);
    List<DetalleVenta> getDetallesByVentaId(int idVenta);
}
