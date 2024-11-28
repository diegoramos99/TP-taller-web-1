package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Receta;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositorioReceta {

    List<Receta> buscarReceta(String nombre);

    Receta obtenerReceta(Long id);

    void guardarReceta(Receta receta);

    List<Receta> buscarReceta();

    void actualizarReceta(Receta receta);

    void eliminarReceta(Long id);

    List<Receta> buscarRecetaPorNombreYingrediente(String search);
}

