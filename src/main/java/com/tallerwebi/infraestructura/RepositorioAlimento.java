package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Alimento;

import java.util.List;

public interface RepositorioAlimento {

      List<Alimento> buscarAlimento(String nombre);
      Alimento obtenerAlimento(Long id);
    List<Alimento> traerTodosLosAlimentos();

    void actualizarAlimento(Alimento alimento);

    void eliminarAlimento(Long id);

    void guardar(Alimento alimento);

    List<Alimento> buscarAlimentoPorNombreYCategoria(String search);
}
