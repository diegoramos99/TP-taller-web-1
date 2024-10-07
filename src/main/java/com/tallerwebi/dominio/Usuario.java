package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String rol;
    private Boolean activo = false;
    private String nombre;
    private String apellido;
    private String objetivoSalud;
    private String preferenciaAlimenticia;
    private String restrincionesAlimentarias;
    private String informacionAdicional;



    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroComida> registrosComida = new ArrayList<>();

    public List<RegistroComida> getRegistrosComida() {
        return registrosComida;
    }

    public void setRegistrosComida(List<RegistroComida> registrosComida) {
        this.registrosComida = registrosComida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRestrincionesAlimentarias() {
        return restrincionesAlimentarias;
    }

    public void setRestrincionesAlimentarias(String restrincionesAlimentarias) {
        this.restrincionesAlimentarias = restrincionesAlimentarias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivoSalud() {
        return objetivoSalud;
    }

    public void setObjetivoSalud(String objetivoSalud) {
        this.objetivoSalud = objetivoSalud;
    }

    public String getPreferenciaAlimenticia() {
        return preferenciaAlimenticia;
    }

    public void setPreferenciaAlimenticia(String preferenciaAlimenticia) {
        this.preferenciaAlimenticia = preferenciaAlimenticia;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }
}
