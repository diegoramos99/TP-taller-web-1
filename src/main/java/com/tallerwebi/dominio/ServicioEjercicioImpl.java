package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioEjercicio")
@Transactional
public class ServicioEjercicioImpl implements ServicioEjercicio{


     private final RepositorioEjercicio repoEjercicios;

    @Autowired
    public ServicioEjercicioImpl(RepositorioEjercicio repoEjercicios) {
        this.repoEjercicios = repoEjercicios;
    }

    @Override
    public List<Ejercicio> buscarEjercicioPorNombreYCategoria(String termino) {
        return repoEjercicios.traerTodosLosEjerciciosPorNombreYCategoria(termino);
    }

    @Override
        public Ejercicio ontenerEjecicioPorId(Long idEjercicio) {
        return repoEjercicios.traerEjercicioPorId(idEjercicio);
    }

    @Override
    public void registrarRutina(RegistroEjercicio registroEjercicio) {
        repoEjercicios.registrarRutina(registroEjercicio);
    }

    @Override
    public List<RegistroEjercicio> traerTodosLosEjerciciosPorDia(String dia) {
        return repoEjercicios.traerTodosLosEjerciciosPorDia(dia);
    }

    @Override
    public void eliminarRegistroEjercicio(Long id) {
        repoEjercicios.eliminarRegistroEjercicio(id);
    }

    @Override
    public List<RegistroEjercicio> traerRutina(String rutinaSelecionada, Usuario usuario) {
        return repoEjercicios.traerTodosLosEjercicios(rutinaSelecionada, usuario);
    }

}
