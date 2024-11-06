package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioEjercicio {
    List<Ejercicio> buscarEjercicioPorNombreYCategoria(String termino);

    Ejercicio ontenerEjecicioPorId(Long idEjercicio);

    void registrarRutina(RegistroEjercicio registroEjercicio);

    List<RegistroEjercicio> traerTodosLosEjerciciosPorDia(String dia);

    void eliminarRegistroEjercicio(Long id);

    List<RegistroEjercicio> traerRutina(String rutinaSelecionada, Usuario usuario);
}
