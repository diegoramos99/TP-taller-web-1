package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Receta;
import org.hibernate.Criteria;
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
        return recetas;
    }

    @Override
    public Receta obtenerReceta(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Receta) session.createCriteria(Receta.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public void guardarReceta(Receta receta) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(receta);
    }

    @Override
    public List<Receta> buscarReceta() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Receta.class).list();
    }

    @Override
    public void actualizarReceta(Receta receta) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(receta);
    }

    @Override
    public void eliminarReceta(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Receta.class, id));
    }

    @Override
    public List<Receta> buscarRecetaPorNombreYingrediente(String search) {
        final Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Receta.class);

        if (search != null && !search.isEmpty()) {
            criteria.add(
                    Restrictions.or(
                            Restrictions.ilike("nombre", "%" + search + "%"),
                            Restrictions.ilike("ingredientes", "%" + search + "%")
                    )
            );
        }
        return (List<Receta>) criteria.list();
    }
}
