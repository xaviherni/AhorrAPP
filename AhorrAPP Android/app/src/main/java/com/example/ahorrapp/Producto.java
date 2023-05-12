package com.example.ahorrapp;

public class Producto {

    private Long id;
    private String nombre;
    private double precio;
    private String supermercado;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getSupermercado() {
        return supermercado;
    }
    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }
    public Producto() {
        super();
    }
}
