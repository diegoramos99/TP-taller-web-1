package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.RegistroEjercicio;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tallerwebi.dominio.Ejercicio;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioEjercicioImpl implements RepositorioEjercicio {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEjercicioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Ejercicio> traerTodosLosEjerciciosPorNombreYCategoria(String termino) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ejercicio.class);

        if (termino != null && !termino.isEmpty()) {
            criteria.add(
                    Restrictions.or(
                            Restrictions.ilike("nombre", "%" + termino + "%"),
                            Restrictions.ilike("categoria", "%" + termino + "%")
                    )
            );
        }

        return (List<Ejercicio>) criteria.list();
    }

    @Override
    public Ejercicio traerEjercicioPorId(Long idEjercicio) {
        final Session session = sessionFactory.getCurrentSession();
        return (Ejercicio) session.createCriteria(Ejercicio.class)
                .add(Restrictions.eq("id", idEjercicio))
                .uniqueResult();
    }

    @Override
    public void registrarRutina(RegistroEjercicio registroEjercicio) {
        sessionFactory.getCurrentSession().save(registroEjercicio);

    }

    @Override
    public List<RegistroEjercicio> traerTodosLosEjerciciosPorDia(String dia) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(RegistroEjercicio.class)
                .add(Restrictions.eq("dia", dia))
                .list();
    }

    @Override
    public void eliminarRegistroEjercicio(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(RegistroEjercicio.class, id));
    }


}
