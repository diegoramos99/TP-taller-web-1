package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioAlimento {
    List<Alimento> BuscarAlimentoPorNombre(String nombre);
}
