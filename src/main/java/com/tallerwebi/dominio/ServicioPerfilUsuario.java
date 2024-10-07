package com.tallerwebi.dominio;

import com.tallerwebi.model.Usuario;

public interface ServicioPerfilUsuario {
    Usuario buscarUsuario(String email, String password);

    Usuario buscarUsuarioPoreEmail(String email);

    Usuario modificarUsuario(Usuario usuario,Usuario usuarioConDatos);

}

