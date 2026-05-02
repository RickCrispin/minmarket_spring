package com.example.demo.repositories;

import com.example.demo.model.Venta;

public interface VentaDAO {
    Integer createVenta(Integer idUsuario);
    Venta getVentaById(int idVenta);
    void updateTotal(int idVenta, Double total);
    void updateEstado(int idVenta, String estado);
}
