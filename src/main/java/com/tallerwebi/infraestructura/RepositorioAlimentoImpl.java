package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Alimento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository("repositorioAlimento")
public class RepositorioAlimentoImpl implements RepositorioAlimento {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAlimentoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Alimento> buscarAlimento(String nombre) {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Alimento>) session.createCriteria(Alimento.class)
                .add(Restrictions.ilike("nombre", "%" + nombre + "%"))
                .list();
    }

    @Override
    public Alimento obtenerAlimento(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Alimento) session.createCriteria(Alimento.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public List<Alimento> traerTodosLosAlimentos() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Alimento>) session.createCriteria(Alimento.class)
                .list();
    }
}

