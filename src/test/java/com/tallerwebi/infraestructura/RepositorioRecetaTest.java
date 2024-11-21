package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Receta;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepositorioRecetaTest {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    RepositorioReceta repositorioReceta;

    @Test
    @Transactional
    @Rollback
    public void quetraigaUnaListaDeREcetas(){
        //given
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        Receta receta2 = new Receta();
        receta2.setNombre("arroz con pollo");

        Receta receta3 = new Receta();
        receta3.setNombre("arroz blanco");

        Receta receta4 = new Receta();
        receta4.setNombre("arroz frito");

        sessionFactory.getCurrentSession().save(receta1);
        sessionFactory.getCurrentSession().save(receta2);
        sessionFactory.getCurrentSession().save(receta3);
        sessionFactory.getCurrentSession().save(receta4);

        List<Receta> recetas=repositorioReceta.buscarReceta();
        assertTrue(recetas instanceof List, " es una lista");
        assertEquals(4, recetas.size(), "El tamaño de la lista debe ser 4");


    }

    @Test
    @Transactional
    @Rollback
    public void quetraigaUnaREcetaCorrectaDependiendoDelId(){
        //given
        Receta receta1 = new Receta();

        sessionFactory.getCurrentSession().save(receta1);
        Long Id=receta1.getId();
        Receta recetas=repositorioReceta.obtenerReceta(Id);
        assertThat(recetas.getId().toString(),equalToIgnoringCase(Id.toString()));

    }
    @Test
    @Transactional
    @Rollback
    public void queguardarRecetaSeAlmaceneCorrectamente(){
        //given
        Receta receta1 = new Receta();
        receta1.setId(1l);
        sessionFactory.getCurrentSession().save(receta1);
        RepositorioReceta repositorioReceta1=mock(RepositorioReceta.class);
       repositorioReceta1.guardarReceta(receta1);
        verify(repositorioReceta1, times(1)).guardarReceta(receta1);

    }
    @Test
    @Transactional
    @Rollback
    public void quetraigaUnaListaDeRecetasPorNombre(){
        // Given: Creación de recetas
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        Receta receta2 = new Receta();
        receta2.setNombre("arroz con pollo");

        Receta receta3 = new Receta();
        receta3.setNombre("arroz blanco");

        Receta receta4 = new Receta();
        receta4.setNombre("arroz frito");

        sessionFactory.getCurrentSession().save(receta1);
        sessionFactory.getCurrentSession().save(receta2);
        sessionFactory.getCurrentSession().save(receta3);
        sessionFactory.getCurrentSession().save(receta4);

        List<Receta> recetas = repositorioReceta.buscarReceta("arroz");

        assertThat(recetas, instanceOf(List.class));
        assertThat(recetas.size(), is(4));
        assertThat(recetas.get(0).getNombre(), equalToIgnoringCase("arroz"));
    }


    @Test
    @Transactional
    @Rollback
    public void queActualizarRecetaSeHaganCambiosEnLaRecetaCorrectamente(){
        //given
        final Session session = sessionFactory.getCurrentSession();
        Receta receta1 = new Receta();

        receta1.setNombre("arroz");
        sessionFactory.getCurrentSession().save(receta1);
        receta1.setNombre("arroz con pollo");
        Long id=receta1.getId();
        Receta recetaBuscada= session.get(Receta.class,id);
        assertThat(recetaBuscada.getNombre().toString(),equalToIgnoringCase("arroz con pollo"));
    }


    @Test
    @Transactional
    @Rollback
    public void queEliminarRecetaDesaparezcanDeLaBaseDeDatos(){
        //given
        final Session session = sessionFactory.getCurrentSession();
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");
        sessionFactory.getCurrentSession().save(receta1);
        Long id=  receta1.getId();

        Receta recetaBuscada1= session.get(Receta.class,id);
        repositorioReceta.eliminarReceta(id);
        Receta recetaBuscada2= session.get(Receta.class,id);
        assertNotNull(recetaBuscada1, "El objeto no debería ser null");
        assertNull(recetaBuscada2, "El objeto debería ser null");
    }

}
