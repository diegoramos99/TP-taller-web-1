package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioListaDeComidasImpl implements RepositorioListaDeComidas {
    @Autowired
    SessionFactory sessionFactory;
    public RepositorioListaDeComidasImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Alimento> buscarAlimentos() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Alimento>) session.createCriteria(Alimento.class).list();
    }

}
