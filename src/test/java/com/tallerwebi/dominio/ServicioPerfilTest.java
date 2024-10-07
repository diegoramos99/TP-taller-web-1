package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPerfilTest {
    private Usuario usuarioMock;
    private HttpServletRequest httpServletRequestMock;
    private HttpSession sessionMock;
    private RepositorioUsuario repositorioUsuarioMock;
    @BeforeEach
    public void init(){
        usuarioMock=mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("die.eramos@gmail.com");
        when(usuarioMock.getPassword()).thenReturn("123");
        repositorioUsuarioMock=mock(RepositorioUsuario.class);
        when(repositorioUsuarioMock.buscarUsuario(usuarioMock.getEmail(), usuarioMock.getPassword())).thenReturn(usuarioMock);
        when(repositorioUsuarioMock.buscar(usuarioMock.getEmail())).thenReturn(usuarioMock);
        Mockito.doNothing().when(repositorioUsuarioMock).modificar(usuarioMock);

        httpServletRequestMock=mock(HttpServletRequest.class);
        sessionMock=mock(HttpSession.class);
    }
    @Test
    public void queAlConsultarPorElEmailYPasswordDevuelvaUsuarioCorrespondiente(){
        //given
        String email=usuarioMock.getEmail();
        String password=usuarioMock.getPassword();
        //when

         Usuario usuarioBuscado=   whenConsultePorEmailYPasword(email,password);

        //then
        String emailEsperado="die.eramos@gmail.com";
        String passwordEsperada="123";
        thenElUsuarioEsElCorrecto(usuarioBuscado , emailEsperado , passwordEsperada);
    }

    private Usuario whenConsultePorEmailYPasword(String email,String password) {
        Usuario usuarioBuscado=repositorioUsuarioMock.buscarUsuario(email,password);
      return  usuarioBuscado;
    }

    private void thenElUsuarioEsElCorrecto(Usuario usuarioBuscado,String emailEsperado,String passwordEsperada) {
    assertThat(usuarioBuscado.getEmail(),equalToIgnoringCase(emailEsperado));
        assertThat(usuarioBuscado.getPassword(),equalToIgnoringCase(passwordEsperada));
    }
    @Test
    public void queSePuedaBuscarUnUsuarioPorEmail(){
        //given
        String email=usuarioMock.getEmail();
        String password=usuarioMock.getPassword();
        //when

        Usuario usuarioBuscado=   whenConsultePorEmail(email);

        //then
        String emailEsperado="die.eramos@gmail.com";
        thenElUsuarioBuscadoPorEmailEsCorrecto(usuarioBuscado , emailEsperado );
    }

    private Usuario whenConsultePorEmail(String email) {
        Usuario usuarioBuscado=repositorioUsuarioMock.buscar(email);
        return  usuarioBuscado;
    }

    private void thenElUsuarioBuscadoPorEmailEsCorrecto(Usuario usuarioBuscado, String emailEsperado) {
        assertThat(usuarioBuscado.getEmail(),equalToIgnoringCase(emailEsperado));

    }

    @Test
    public void queSePuedamodificarUnUsuario(){

        //given
        Mockito.when(usuarioMock.getEmail()).thenReturn("test@gmail.com").thenReturn("prueba@gmail.com");


        //when
        String emailOriginal=usuarioMock.getEmail();
        usuarioMock.setEmail("prueba@gmail.com");
        whenModificaUnUsuario();
        String emailCambiado=usuarioMock.getEmail();

        //then
        Mockito.verify(usuarioMock).setEmail("prueba@gmail.com");
        assertThat(emailCambiado,equalToIgnoringCase("prueba@gmail.com"));
    }

    private void whenModificaUnUsuario() {


        repositorioUsuarioMock.modificar(usuarioMock);

    }
}
