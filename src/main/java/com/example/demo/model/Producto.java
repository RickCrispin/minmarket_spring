package com.example.demo.model;

import java.time.LocalDateTime;

public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Integer idCategoria;
    private String estado;
    private LocalDateTime fechaRegistro;
    private Categoria categoria;

    public Producto(){

    }
    public Producto(Integer id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Integer idCategoria,
        String estado,
        LocalDateTime fechaRegistro
    ){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }
    public Producto(Integer id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Integer idCategoria,
        String estado,
        LocalDateTime fechaRegistro,
        Categoria categoria
    ){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
