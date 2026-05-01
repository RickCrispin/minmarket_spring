package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {
    private Integer id;
    private Integer idUsuario;
    private LocalDateTime fechaVenta;
    private Double total;
    private String estado;

    private List<DetalleVenta> detalles;

    
    public Venta() {
    }
    public Venta(Integer id, Integer idUsuario, LocalDateTime fechaVenta, Double total, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
    }

    public Venta(Integer id, Integer idUsuario, LocalDateTime fechaVenta, Double total, String estado, List<DetalleVenta> detalles) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.detalles = detalles;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaVenta() {
        return this.fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleVenta> getDetalles() {
        return this.detalles;
    }
    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
    
}