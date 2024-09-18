package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioPerfilUsuario")
@Transactional
public class ServicioPerfilUsuarioImpl implements ServicioPerfilUsuario {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioPerfilUsuarioImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }


}
