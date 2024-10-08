package com.tallerwebi.dominio;

import javax.persistence.*;


@Entity
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ingredientes;
    @Column(length = 300)
    private String preparacion;

    public Receta(String nombre) {this.nombre = nombre;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getIngredientes() {return ingredientes;}
    public void setIngredientes(String ingredientes) {this.ingredientes = ingredientes;}

    public String getPreparacion() {return preparacion;}
    public void setPreparacion(String preparacion) {this.preparacion = preparacion;}
}