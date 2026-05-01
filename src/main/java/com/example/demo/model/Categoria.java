package com.example.demo.model;


public class Categoria {
    private Integer id;
    private String nombre;
    private String descripcion;

    public Categoria(){

    }

    public Categoria(Integer id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombre;
    }

    public void setNombres(String nombre) {
        this.nombre = nombre;
    }

    public String getDescipcion() {
        return this.descripcion;
    }

    public void setDescipcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
