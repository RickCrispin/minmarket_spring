package com.example.demo.model;

public class DiaVenta {
    private String nombreDia;
    private Double total;

    public DiaVenta() {}

    public DiaVenta(String nombreDia, Double total) {
        this.nombreDia = nombreDia;
        this.total = total;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
