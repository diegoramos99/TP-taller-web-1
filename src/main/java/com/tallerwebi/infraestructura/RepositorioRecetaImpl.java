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
        final Session session = sessionFactory.getCurrentSession();
        List<Receta> recetas = session.createCriteria(Receta.class).add(Restrictions.ilike("nombre","%" + nombre + "%")).list();
        System.out.println("Buscando recetas con el nombre: " + nombre + ", Resultados: " + recetas.size());
        return recetas;
    }

    @Override
    public Receta obtenerReceta(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Receta) session.createCriteria(Receta.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public Receta guardarReceta(Receta receta) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(receta);
        return null;
    }
}
