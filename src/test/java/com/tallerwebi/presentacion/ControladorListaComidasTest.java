package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.ServicioListaDeComidasImpl;
import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorListaComidasTest {

    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioListaDeComidasImpl servicioListaDeComidasImplMock;
    private ServicioPerfilUsuario servicioPerfilUsuarioMock;
    private ControladorListaDeComidas controladorListaDeComidasMock=new ControladorListaDeComidas(servicioListaDeComidasImplMock,servicioPerfilUsuarioMock);

    @BeforeEach
    public void init() {
        usuarioMock = mock(Usuario.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioListaDeComidasImplMock = mock(ServicioListaDeComidasImpl.class);
        controladorListaDeComidasMock = mock(ControladorListaDeComidas.class);
    }

/*
    @Test
    public void queAlHacerClickEnListaDeComidasTeLleveALaVistaListaDeComidas(){
        ModelAndView mav =new ModelAndView("redirect:/listaDeComidas");
              MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("EMAIL", "test@example.com");

    when(controladorListaDeComidasMock.irAListaDeComidas(request)).thenReturn(mav);
   ModelAndView mav1= whenHagasClickTeLleveAunaVista();

    thenEsLaVistaCorrecta(mav1);

}


 */

    private void thenEsLaVistaCorrecta(ModelAndView mav) {
        assertThat(mav.getViewName().toString(), equalToIgnoringCase("redirect:/listaDeComidas")) ;
    }

      ModelAndView mav =new ModelAndView("redirect:/listaDeComidas");
              MockHttpServletRequest request = new MockHttpServletRequest();
    private ModelAndView whenHagasClickTeLleveAunaVista() {
      ModelAndView mav  =controladorListaDeComidasMock.irAListaDeComidas(request);
    return mav;
    }

@Test
    public void queAlEntrarALaVistaListaComidasMuestreLosAlimentosParaLaSemana(){
        when(usuarioMock.getPreferenciaAlimenticia()).thenReturn("vegano");
        ModelMap map=new ModelMap();
        List <List<Alimento>> alimentosList=new ArrayList<>();
        List <Alimento> alimentos=new ArrayList<>();
      List <Alimento> alimentos1=new ArrayList<>();

        Alimento alimento =new Alimento();
        alimento.setDieta("vegano");

        alimentos.add(alimento);

    alimentosList.add(alimentos);
    alimentosList.add(alimentos1);
        map.put("alimento",alimentosList);
        ModelAndView mav=new ModelAndView("/unaPagina",map);
        when(controladorListaDeComidasMock.mostrarAlimentosDisponibles(requestMock)).thenReturn(mav);

        ModelAndView mav1= whenMuestreLosAlimentos(usuarioMock);


        String dieta=usuarioMock.getPreferenciaAlimenticia();
        thenLosAlimentosSonCorrectos(mav1,dieta);
    }

    private void thenLosAlimentosSonCorrectos(ModelAndView mav1 , String dieta) {
        List <List <Alimento>> alimentos=(List <List <Alimento>>)mav1.getModel().get("alimento");
        assertThat(alimentos.get(0).get(0).getDieta().toString(),equalToIgnoringCase(dieta));


        // Comprobar que la dieta del primer alimento coincide con la dieta esperada
        assertThat(alimentos.get(0).get(0).getDieta(), equalToIgnoringCase(dieta));
    }

    private ModelAndView whenMuestreLosAlimentos(Usuario usuarioMock) {
        ModelAndView mav=controladorListaDeComidasMock.mostrarAlimentosDisponibles(requestMock);
         return mav;
    }

}
