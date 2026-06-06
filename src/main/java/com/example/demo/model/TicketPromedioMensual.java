package com.example.demo.model;

public class TicketPromedioMensual {
    private Integer ano;
    private Integer mes;
    private Double promedio;

    public TicketPromedioMensual() {}

    public TicketPromedioMensual(Integer ano, Integer mes, Double promedio) {
        this.ano = ano;
        this.mes = mes;
        this.promedio = promedio;
    }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public Double getPromedio() { return promedio; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }
}
