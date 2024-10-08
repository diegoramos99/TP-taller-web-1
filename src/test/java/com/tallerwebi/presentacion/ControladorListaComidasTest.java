package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioListaDeComidas;
import com.tallerwebi.dominio.ServicioListaDeComidasImpl;
import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class ControladorListaComidasTest {

    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioListaDeComidasImpl servicioListaDeComidasImplMock;
    private ServicioPerfilUsuario servicioPerfilUsuarioMock;
    private ControladorListaDeComidas controladorListaDeComidasMock=new ControladorListaDeComidas(servicioListaDeComidasImplMock,servicioPerfilUsuarioMock);




    @Test
    public void queAlHacerClickEnListaDeComidasTeLleveALaVistaListaDeComidas(){

   ModelAndView mav= whenHagasClickTeLleveAunaVista();

    thenEsLaVistaCorrecta(mav);

}

    private void thenEsLaVistaCorrecta(ModelAndView mav) {
        assertThat(mav.getViewName().toString(), equalToIgnoringCase("redirect:/listaDeComidas")) ;
    }

    private ModelAndView whenHagasClickTeLleveAunaVista() {
      ModelAndView mav  =controladorListaDeComidasMock.irAListaDeComidas();
    return mav;
    }

   /** @Test
    public void queAlEntrarALaVistaListaComidasMuestreLosAlimentosParaLaSemana(){

        ModelAndView mav= whenMuestreLosAlimentos(usuarioMock);
        thenLosAlimentosSonCorrectos(mav);
    }

    private void thenLosAlimentosSonCorrectos(ModelAndView mav) {
    }

    private ModelAndView whenMuestreLosAlimentos(Usuario usuarioMock) {
        ModelAndView mav=controladorListaDeComidasMock.mostrarAlimentosDisponibles(usuarioMock);
    return mav;
    }
   **/
}
