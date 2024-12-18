package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Usuario;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario);
    Usuario buscar(String email);
    void modificar(Usuario usuario);

    void actualizarEstadoPremium(boolean b, Long id);

    void actualizar(Usuario usuarioActivo);
}

