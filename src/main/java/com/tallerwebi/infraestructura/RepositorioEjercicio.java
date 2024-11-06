package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Ejercicio;
import com.tallerwebi.dominio.RegistroEjercicio;

import java.util.List;

public interface RepositorioEjercicio {
    List<Ejercicio> traerTodosLosEjerciciosPorNombreYCategoria(String nombre);

        Ejercicio traerEjercicioPorId(Long idEjercicio);

    void registrarRutina(RegistroEjercicio registroEjercicio);

    List<RegistroEjercicio> traerTodosLosEjerciciosPorDia(String dia);

    void eliminarRegistroEjercicio(Long id);
}
