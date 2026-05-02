package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Producto;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(int id);
    void addProducto(Producto producto);
    void updateProducto(Producto producto);
    void desactivarProducto(int id);
}
