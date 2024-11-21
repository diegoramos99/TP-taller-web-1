package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RegistroEjercicio;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tallerwebi.dominio.Ejercicio;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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

    @Override
    public List<RegistroEjercicio> traerTodosLosEjercicios(String nombreRutinaSeleccionada, Usuario usuario) {
        final Session session = sessionFactory.getCurrentSession();

        List<RegistroEjercicio> registros = session.createCriteria(RegistroEjercicio.class)
                .add(Restrictions.eq("nombreRutina", nombreRutinaSeleccionada))
                .list();

        if (registros.isEmpty()) {
            return registros;
        } else {
            for (RegistroEjercicio registro : registros) {
                registro.setUsuario(usuario);
                session.update(registro);
            }
        }


        return registros;
    }

    @Override
    public List<Ejercicio> getEjercicios() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Ejercicio.class).list();
    }

    @Override
    public void actualizarEjercicio(Ejercicio ejercicio) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(ejercicio);
    }

    @Override
    public void guardarEjercicio(Ejercicio ejercicio) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(ejercicio);
    }

    @Override
    public void eliminarEjercicio(Long id) {
        final Session session = sessionFactory.getCurrentSession();

        List<RegistroEjercicio> registrosEjercicios = session.createCriteria(RegistroEjercicio.class)
                .add(Restrictions.eq("ejercicio.id", id))
                .list();

        for (RegistroEjercicio registro : registrosEjercicios) {
            session.delete(registro);
        }

        Ejercicio ejercicio = session.load(Ejercicio.class, id);
        session.delete(ejercicio);
    }


}
