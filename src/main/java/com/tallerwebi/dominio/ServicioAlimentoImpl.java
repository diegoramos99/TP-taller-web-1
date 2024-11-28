package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioAlimento;
import com.tallerwebi.infraestructura.RepositorioRegistroComida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioAlimento")
@Transactional
public class ServicioAlimentoImpl implements ServicioAlimento {

    private final RepositorioAlimento repositorioAlimento;
    private final RepositorioRegistroComida repositorioRegistroComida;

    @Autowired
    public ServicioAlimentoImpl(RepositorioAlimento repositorioAlimento, RepositorioRegistroComida repositorioRegistroComida) {
        this.repositorioAlimento = repositorioAlimento;
        this.repositorioRegistroComida = repositorioRegistroComida;
    }

    @Override
    public List<Alimento> BuscarAlimentoPorNombre(String nombre) {
        return repositorioAlimento.buscarAlimento(nombre);
    }

    @Override
    public Alimento obtenerAlimentoPorId(Long id) {
        return repositorioAlimento.obtenerAlimento(id);
    }

    @Override
    public void guardarRegistroAlimento(RegistroComida registroComida) {
        repositorioRegistroComida.guardar(registroComida);
    }

    @Override
    public List<RegistroComida> obtenerRegistrosPorFecha(String fecha) {
        return repositorioRegistroComida.buscarRegistroComidaPorFecha(fecha);
    }

    @Override
    public void eliminarRegistroAlimento(Long id) {
        repositorioRegistroComida.eliminarRegistroComida(id);
    }

    @Override
    public List<Alimento> getAlimentos() {
        return repositorioAlimento.traerTodosLosAlimentos();
    }

    @Override
    public void actualizarAlimento(Alimento alimento) {
        repositorioAlimento.actualizarAlimento(alimento);
    }

    @Override
    public void eliminarAlimento(Long id) {
        repositorioAlimento.eliminarAlimento(id);
    }

    @Override
    public void guardar(Alimento alimento) {
        repositorioAlimento.guardar(alimento);
    }

    @Override
    public List<Alimento> BuscarAlimentoPorNombreYCategoria(String search) {
        return repositorioAlimento.buscarAlimentoPorNombreYCategoria(search);
    }


}
