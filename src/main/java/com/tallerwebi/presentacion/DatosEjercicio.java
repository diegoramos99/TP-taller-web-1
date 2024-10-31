package com.tallerwebi.presentacion;

public class DatosEjercicio {


    private String nombre;
    private Integer series;
    private Integer repeticiones;
    private Double descanso;
    private Long idEjercicio;
    private String dia;

    public DatosEjercicio() {

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Long getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Long idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
}
