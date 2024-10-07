package com.tallerwebi.dominio;

public interface ServicioPerfilUsuario {
    Usuario buscarUsuario(String email, String password);

    Usuario buscarUsuarioPoreEmail(String email);

    Usuario modificarUsuario(Usuario usuario,Usuario usuarioConDatos);

}

