package com.example.demo.model;

public class VentaMensual {
    private Integer ano;
    private Integer mes;
    private Double total;

    public VentaMensual() {}

    public VentaMensual(Integer ano, Integer mes, Double total) {
        this.ano = ano;
        this.mes = mes;
        this.total = total;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
