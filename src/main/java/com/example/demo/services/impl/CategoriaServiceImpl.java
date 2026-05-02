package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Categoria;
import com.example.demo.repositories.CategoriaDAO;
import com.example.demo.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO){
        this.categoriaDAO = categoriaDAO;
    }

    public List<Categoria> getAllCategorias(){
        return categoriaDAO.getAllCategorias();
    }
    public Categoria getCategoriaById(int id){
        return categoriaDAO.getCategoriaById(id);
    }
    public void addCategoria(Categoria categoria){
        categoriaDAO.addCategoria(categoria);
    }
    public void updateCategoria(Categoria categoria){
        categoriaDAO.updateCategoria(categoria);
    }
    public void desactivarCategoria(int id){
        categoriaDAO.desactivarCategoria(id);
    }
}