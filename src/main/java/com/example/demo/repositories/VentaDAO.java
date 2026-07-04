package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.Venta;

public interface VentaDAO {
    Integer createVenta(Integer idUsuario);
    Venta getVentaById(int idVenta);
    List<Venta> getAllVentas();
    void updateTotal(int idVenta, Double total);
    void updateEstado(int idVenta, String estado);
    void deleteVenta(int idVenta);
}
