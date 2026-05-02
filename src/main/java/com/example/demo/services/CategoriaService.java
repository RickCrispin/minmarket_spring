package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Categoria;

public interface CategoriaService {
    List<Categoria> getAllCategorias();
    Categoria getCategoriaById(int id);
    void addCategoria(Categoria categoria);
    void updateCategoria(Categoria categoria);
    void desactivarCategoria(int id);
}
