package com.tallerwebi.infraestructura;

import com.tallerwebi.model.RegistroComida;

import java.util.List;

public interface RepositorioRegistroComida {
    void guardar(RegistroComida registroComida);
    List<RegistroComida> buscarRegistroComidaPorFecha(String fecha);
}
