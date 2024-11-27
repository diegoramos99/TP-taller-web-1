package com.tallerwebi.dominio;
public class DatosIMC {

    private String clasificacion;
    private String genero;
    private double altura;
    private double peso;
    private Integer edad;
    private double imc;

    // Constructor vacío
    public DatosIMC() {
    }

    // Constructor con parámetros
    public DatosIMC(String clasificacion, String genero, double altura, double peso, Integer edad, double imc) {
        this.clasificacion = clasificacion;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
        this.imc = imc;
    }

    // Getters y Setters
    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
}

