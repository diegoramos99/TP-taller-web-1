package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.ServicioLoginImpl;
import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorPerfilTest {


    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioPerfilUsuario servicioPerfilUsuarioMock;
    private ControladorPerfilUsuario controladorPerfilUsuarioMock;

    @BeforeEach
    public void init(){
        usuarioMock = mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("dami@unlam.com");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioPerfilUsuarioMock = mock(ServicioPerfilUsuario.class);
        controladorPerfilUsuarioMock=mock(ControladorPerfilUsuario.class);
    }
@Test
    public void queSeCargueLosDatosDelUsuarioNullAlIngresarPorPrimeraVez(){
    // given
    //when
    //then
    ModelMap model=new ModelMap();
    model.put("nombre",null);
    model.put("apellido",null);
    model.put("objetivoSalud",null);
    model.put("preferenciaAlimenticia",null);
    model.put("restrincionesAlimentarias",null);

    ModelAndView mav=new ModelAndView("perfilusuario",model);
    when(controladorPerfilUsuarioMock.mostrarDatosDelUsuario()).thenReturn(model);
    givenNoexisteUsuario();


   ModelMap mav1= whenMuestraLosDatosDelUsuario();


    thenLosDatosSonCorrectos(mav1);


}

    private void thenLosDatosSonCorrectos(ModelMap mav) {
        assertThat( mav.get("nombre"),nullValue());
        assertThat( mav.get("apellido"), nullValue());
        assertThat( mav.get("objetivoSalud"), nullValue());
        assertThat( mav.get("preferenciaAlimenticia"), nullValue());
        assertThat( mav.get("restrincionesAlimentarias"), nullValue());
    }

    private ModelMap whenMuestraLosDatosDelUsuario() {
    return  controladorPerfilUsuarioMock.mostrarDatosDelUsuario();
    }

    private void givenNoexisteUsuario() {
    }


    @Test
    public void queAlHacerClickAlFormularioMeLleveAlFormulario(){
        when(controladorPerfilUsuarioMock.mostrarVista()).thenReturn(new ModelAndView("formulario"));
        givenExistePerfil();

        ModelAndView mav= whenRecibaLaVista();

        String vistaEsperada="formulario";
        thenLaVistaEsCorrecta(mav , vistaEsperada);
    }

    private void thenLaVistaEsCorrecta(ModelAndView mav ,String vistaEsperada) {
    assertThat(mav.getViewName(),equalToIgnoringCase(vistaEsperada));
    }
    private ModelAndView whenRecibaLaVista() {
    return  controladorPerfilUsuarioMock.mostrarVista();
    }
    private void givenExistePerfil() {
    }



    @Test
     public void queAlCompletarElFormularioVayaALaVistaPerfilUsuario(){
        when(controladorPerfilUsuarioMock.irAlPerfil()).thenReturn(new ModelAndView("perfilusuario"));
        givenExistePerfil();

        ModelAndView mavs= whenVayaALaVistaCorrecta();

        String vistaEsperada="perfilusuario";
        thenLaVistaEsCorrecta(mavs,vistaEsperada);

    }

    private ModelAndView whenVayaALaVistaCorrecta() {
    return controladorPerfilUsuarioMock.irAlPerfil();

    }

    @Test
    public void queAlCompletarElFormularioLosDatosSeVeanEnElPerfilUsuario(){
        ModelMap model=new ModelMap();
        model.put("objetivoSalud","perdida_grasa");
        model.put("preferenciaAlimenticia","omnivoro");
        model.put("restrincionesAlimentarias","relleno de variable");
        model.put("informacionAdicional","otroRelleno");
        ModelAndView mav=new ModelAndView("perfilusuario",model);

        when(controladorPerfilUsuarioMock.enviarDatosDelFormulario(usuarioMock,requestMock)).thenReturn(mav);
        givenExistePerfil();


      ModelAndView mav1= whenEnvieLosDatosAlaVistaPerfilUsuario();


        String valor1Esperado="perdida_grasa";
        String valor2Esperado="omnivoro";
        String valor3Esperado="relleno de variable";
        String valor4Esperado="otroRelleno";


        thenLosDatosSeanLosMismoDelFormulario(mav1 ,valor1Esperado,valor2Esperado,valor3Esperado,valor4Esperado);

    }

    private void thenLosDatosSeanLosMismoDelFormulario(ModelAndView mav,String valor1 , String valor2 , String valor3 , String valor4) {
        assertThat(mav.getModel().get("objetivoSalud").toString(),equalToIgnoringCase(valor1));
        assertThat(mav.getModel().get("preferenciaAlimenticia").toString(),equalToIgnoringCase(valor2));
        assertThat(mav.getModel().get("restrincionesAlimentarias").toString(),equalToIgnoringCase(valor3));
        assertThat(mav.getModel().get("informacionAdicional").toString(),equalToIgnoringCase(valor4));

    }

    private ModelAndView whenEnvieLosDatosAlaVistaPerfilUsuario() {
        return  controladorPerfilUsuarioMock.enviarDatosDelFormulario(usuarioMock,requestMock);

    }


}


