package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.Categoria;

public interface CategoriaDAO {
    List<Categoria> getAllCategorias();
    Categoria getCategoriaById(int id);
    void addCategoria(Categoria categoria);
    void updateCategoria(Categoria categoria);
    void desactivarCategoria(int id);
}
