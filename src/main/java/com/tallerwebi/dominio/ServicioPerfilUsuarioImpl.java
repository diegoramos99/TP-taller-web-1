package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioPerfilUsuario")@Transactional
public class ServicioPerfilUsuarioImpl implements ServicioPerfilUsuario {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioPerfilUsuarioImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }


    @Override
    public Usuario buscarUsuario(String email, String password) {
        Usuario usuario =repositorioUsuario.buscarUsuario(email,password);
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPoreEmail(String email) {
        Usuario usuario=repositorioUsuario.buscar(email);
        return usuario;
    }
    @Override
    public Usuario modificarUsuario(Usuario usuario,Usuario usuarioConDatos){

        usuario.setObjetivoSalud(usuarioConDatos.getObjetivoSalud());
        usuario.setInformacionAdicional(usuarioConDatos.getInformacionAdicional());
        usuario.setPreferenciaAlimenticia(usuarioConDatos.getPreferenciaAlimenticia());
        usuario.setRestrincionesAlimentarias(usuarioConDatos.getRestrincionesAlimentarias());

         repositorioUsuario.modificar(usuario);

        return usuario;
    }

    @Override
    public void actualizarEstadoPremium(boolean b, Long id) {

        repositorioUsuario.actualizarEstadoPremium(b,id);
    }

    @Override
    public void actualizarUsuario(Usuario usuarioActivo) {

        repositorioUsuario.actualizar(usuarioActivo);
    }

    @Override
    public void actualizarIMC(String email) {
    Usuario usuarioBuscado= repositorioUsuario.buscar(email);
    repositorioUsuario.actualizar(usuarioBuscado);
    }


}
