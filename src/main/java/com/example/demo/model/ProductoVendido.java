package com.example.demo.model;

public class ProductoVendido {
    private Integer idProducto;
    private String nombre;
    private Integer totalCantidad;
    private Double totalIngresos;

    public ProductoVendido() {}

    public ProductoVendido(Integer idProducto, String nombre, Integer totalCantidad, Double totalIngresos) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.totalCantidad = totalCantidad;
        this.totalIngresos = totalIngresos;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotalCantidad() {
        return totalCantidad;
    }

    public void setTotalCantidad(Integer totalCantidad) {
        this.totalCantidad = totalCantidad;
    }

    public Double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }
}
