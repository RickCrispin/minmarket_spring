package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.Producto;

public interface ProductoDAO {
    List<Producto> getAllProductos();
    Producto getProductoById(int id);
    void addProducto(Producto producto);
    void updateProducto(Producto producto);
    void desactivarProducto(int id);
}
