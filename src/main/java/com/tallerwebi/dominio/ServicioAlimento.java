package com.tallerwebi.dominio;

import com.tallerwebi.model.Alimento;
import com.tallerwebi.model.RegistroComida;

import java.util.List;

public interface ServicioAlimento {
    List<Alimento> BuscarAlimentoPorNombre(String nombre);
    Alimento obtenerAlimentoPorId(Long id);
    void guardarRegistroAlimento(RegistroComida registroComida);
    List<RegistroComida> obtenerRegistrosPorFecha(String fecha);
}
