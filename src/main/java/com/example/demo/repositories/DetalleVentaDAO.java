package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.DetalleVenta;

public interface DetalleVentaDAO {
    void addDetalleVenta(DetalleVenta detalleVenta);
    List<DetalleVenta> getDetallesByVentaId(int idVenta);
}
