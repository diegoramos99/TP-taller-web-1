package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Receta;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositorioReceta {

        List<Receta> buscarReceta(String nombre);
        Receta obtenerReceta(Long id);
        Receta guardarReceta(Receta receta);
    }

