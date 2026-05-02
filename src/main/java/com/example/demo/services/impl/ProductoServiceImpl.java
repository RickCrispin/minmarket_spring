package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repositories.ProductoDAO;
import com.example.demo.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoDAO.getAllProductos();
    }

    @Override
    public Producto getProductoById(int id) {
        return productoDAO.getProductoById(id);
    }

    @Override
    public void addProducto(Producto producto) {
        productoDAO.addProducto(producto);
    }

    @Override
    public void updateProducto(Producto producto) {
        productoDAO.updateProducto(producto);
    }

    @Override
    public void desactivarProducto(int id) {
        productoDAO.desactivarProducto(id);
    }
}
