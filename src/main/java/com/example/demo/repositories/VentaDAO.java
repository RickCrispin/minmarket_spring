package com.example.demo.repositories;

import com.example.demo.model.Venta;

public interface VentaDAO {
    Integer createVenta(Integer idUsuario);
    Venta getVentaById(int idVenta);
    java.util.List<com.example.demo.model.Venta> getAllVentas();
    void updateTotal(int idVenta, Double total);
    void updateEstado(int idVenta, String estado);
    void deleteVenta(int idVenta);
}
