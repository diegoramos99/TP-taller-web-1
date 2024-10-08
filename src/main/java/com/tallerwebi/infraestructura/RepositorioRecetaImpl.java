package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Receta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository("repositorioReceta")
public class RepositorioRecetaImpl implements RepositorioReceta{

    private SessionFactory sessionFactory;

    @Autowired
    public void RepositorioRecetaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Receta> buscarReceta(String nombre) {
        return List.of();
    }

    @Override
    public Receta obtenerReceta(Long id) {
        return null;
    }
}
