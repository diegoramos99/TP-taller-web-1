package com.tallerwebi.infraestructura;

import com.tallerwebi.model.Usuario;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
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


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})


public class RepositorioUsuarioTest {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    RepositorioUsuario repositorioUsuario;




    @Test
   @Transactional
    @Rollback
    public void queBusqueUsuarioConEmailYPassword(){
        //given
        Usuario usuario=new Usuario();
        usuario.setEmail("test@gmail.com");
        usuario.setPassword("123");
        sessionFactory.getCurrentSession().save(usuario);
        //when
   Usuario usuarioBuscado= repositorioUsuario.buscarUsuario(usuario.getEmail(),usuario.getPassword());
     assertThat(usuarioBuscado.getId(), notNullValue());
     assertThat(usuarioBuscado.getPassword(),equalToIgnoringCase("123"));
     assertThat(usuarioBuscado.getEmail(),equalToIgnoringCase("test@gmail.com"));
    }
    @Test
    @Transactional
    @Rollback
    public void queguardeUnUsuario(){

        Usuario usuario=new Usuario();
        usuario.setEmail("test@gmail.com");
        usuario.setPassword("123");
        //when
        repositorioUsuario.guardar(usuario);
        //then
        assertThat(usuario.getId(),notNullValue());
    }
    @Test
    @Transactional
    @Rollback
    public void queBusquePorEmail(){
        //given
        Usuario usuario=new Usuario();
        usuario.setEmail("test@gmail.com");
        usuario.setPassword("123");
        sessionFactory.getCurrentSession().save(usuario);
        //when
        Usuario usuarioBuscado = repositorioUsuario.buscar(usuario.getEmail());
        //then
        assertThat(usuarioBuscado.getEmail(),equalToIgnoringCase(usuario.getEmail()));
    }
    @Test
    @Transactional
    @Rollback
    public void queModifiqueUnUsuario(){
        //given
        Usuario usuario=new Usuario();
        usuario.setEmail("test@gmail.com");
        usuario.setPassword("123");
        sessionFactory.getCurrentSession().save(usuario);


        //when
        String emailModificado="esteEsOtroGmail@gmail.com";
        usuario.setEmail(emailModificado);
        repositorioUsuario.modificar(usuario);
        Usuario usuarioBuscado=(Usuario) sessionFactory.getCurrentSession()
                        .createCriteria(Usuario.class)
                        .add(Restrictions.eq("email",emailModificado))
                        .uniqueResult();
        // then
        assertThat(usuarioBuscado.getEmail(),equalToIgnoringCase(emailModificado));
    }
}
