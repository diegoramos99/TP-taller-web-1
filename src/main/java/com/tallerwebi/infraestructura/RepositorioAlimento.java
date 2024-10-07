package com.tallerwebi.infraestructura;


import com.tallerwebi.model.Alimento;

import java.util.List;

public interface RepositorioAlimento {

      List<Alimento> buscarAlimento(String nombre);
      Alimento obtenerAlimento(Long id);
}
