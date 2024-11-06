package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RegistroComida;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("repositorioRegistroComida")
public class RepositorioRegistroComidaimpl implements RepositorioRegistroComida {


    private SessionFactory sessionFactory;

    @Autowired
    public void RepositorioRegistroComidaimpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void guardar(RegistroComida registroComida) {
        try {
            sessionFactory.getCurrentSession().save(registroComida);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el registro de comida", e);
        }
    }

    @Override
    public List<RegistroComida> buscarRegistroComidaPorFecha(String fecha) {
        String sql = "FROM RegistroComida rc WHERE rc.fecha = :fecha";
        Query<RegistroComida> query = sessionFactory.getCurrentSession().createQuery(sql, RegistroComida.class);
        query.setParameter("fecha", fecha);

        return query.getResultList();
    }

    @Override
    public void eliminarRegistroComida(Long id) {
        RegistroComida registroComida = sessionFactory.getCurrentSession().get(RegistroComida.class, id);
        sessionFactory.getCurrentSession().delete(registroComida);
    }


}
