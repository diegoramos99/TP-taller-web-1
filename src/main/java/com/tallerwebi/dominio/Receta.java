package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;


@Entity
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ingredientes;
    private Long tiempo;
    private Long calorias;
    private String imagen;
    @Column(length = 300)
    private String preparacion;

    public Receta(){
    }
    public Receta(String nombre) {this.nombre = nombre;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}



    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {this.preparacion = preparacion;}

    public Long getTiempo() {return tiempo;}

    public void setTiempo(Long tiempo) {this.tiempo = tiempo;}

    public Long getCalorias() {return calorias;}

    public void setCalorias(Long calorias) {this.calorias = calorias;}

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
}