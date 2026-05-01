package com.example.demo.model;

public class DetalleVenta {
    private Integer id;
    private Integer idVenta;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Integer subTotal;

    private Producto producto;

    public DetalleVenta(){

    }

    public DetalleVenta(Integer id, Integer idVenta, Integer idProducto, Integer cantidad, Double precioUnitario, Integer subTotal){
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public DetalleVenta(Integer id, Integer idVenta, Integer idProducto, Integer cantidad, Double precioUnitario, Integer subTotal, Producto producto){
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.producto = producto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVenta() {
        return this.idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public Integer getSubTotal() {
        return this.subTotal;
    }
    
    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }
}
