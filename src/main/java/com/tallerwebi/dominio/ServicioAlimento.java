package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.util.List;

public interface ServicioAlimento {
    List<Alimento> BuscarAlimentoPorNombre(String nombre);
    Alimento obtenerAlimentoPorId(Long id);
    void guardarRegistroAlimento(RegistroComida registroComida);
    List<RegistroComida> obtenerRegistrosPorFecha(String fecha);
}
