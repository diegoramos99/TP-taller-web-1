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
                Long idDeUsuario=usuario.getId();
                String nombre=usuario.getNombre();
                 String apellido=usuario.getApellido();
                 String email= usuario.getEmail();
                 String password= usuario.getPassword();
                usuarioConDatos.setId(idDeUsuario);
                usuarioConDatos.setNombre(nombre);
                usuarioConDatos.setApellido(apellido);
                usuarioConDatos.setEmail(email);
                usuarioConDatos.setPassword(password);
                repositorioUsuario.modificar(usuarioConDatos);
        return usuarioConDatos;
    }
}
