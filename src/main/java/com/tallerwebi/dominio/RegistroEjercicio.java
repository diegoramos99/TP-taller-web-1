package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class RegistroEjercicio {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Ejercicio ejercicio;

    private Integer series;
    private Integer repeticiones;
    private Double descanso;
    private String dia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Double getDescanso() {
        return descanso;
    }

    public void setDescanso(Double descanso) {
        this.descanso = descanso;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }
}
