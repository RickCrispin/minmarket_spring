package com.example.demo.model;

public class CategoriaIngresos {
	private Integer idCategoria;
	private String nombreCategoria;
	private Double totalIngresos;

	public CategoriaIngresos() {}

	public CategoriaIngresos(Integer idCategoria, String nombreCategoria, Double totalIngresos) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.totalIngresos = totalIngresos;
	}

	public Integer getIdCategoria() { return idCategoria; }
	public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }
	public String getNombreCategoria() { return nombreCategoria; }
	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
	public Double getTotalIngresos() { return totalIngresos; }
	public void setTotalIngresos(Double totalIngresos) { this.totalIngresos = totalIngresos; }
}
