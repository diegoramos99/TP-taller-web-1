package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioAlimento {
    List<Alimento> BuscarAlimentoPorNombre(String nombre);
    Alimento obtenerAlimentoPorId(Long id);
    void guardarRegistroAlimento(RegistroComida registroComida);
    List<RegistroComida> obtenerRegistrosPorFecha(String fecha);

    void eliminarRegistroAlimento(Long id);

    List<Alimento> getAlimentos();

    void actualizarAlimento(Alimento alimento);

    void eliminarAlimento(Long id);

    void guardar(Alimento alimento);

    List<Alimento> BuscarAlimentoPorNombreYCategoria(String search);
}
