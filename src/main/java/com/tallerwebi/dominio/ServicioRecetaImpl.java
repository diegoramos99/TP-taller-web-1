package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioReceta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@Service("servicioReceta")
@Transactional

public class ServicioRecetaImpl implements ServicioReceta {

    private final RepositorioReceta repositorioReceta;

    @Autowired
    public ServicioRecetaImpl(RepositorioReceta repositorioReceta, RepositorioReceta repositorioReceta1, RepositorioReceta repositorioReceta2) {
        this.repositorioReceta = repositorioReceta;
    }

    @Override
    public List<Receta> BuscarRecetaPorNombre(String nombre) {
        return repositorioReceta.buscarReceta(nombre);
    }

    @Override
    public Receta obtenerRecetaPorId(Long id) {
        return repositorioReceta.obtenerReceta(id);
    }
}
