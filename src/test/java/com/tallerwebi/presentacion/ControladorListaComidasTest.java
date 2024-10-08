package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioListaDeComidas;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class ControladorListaComidasTest {

    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioListaDeComidas servicioListaDeComidasMock;
    private ControladorListaDeComidas controladorListaDeComidasMock=new ControladorListaDeComidas();




    @Test
    public void queAlHacerClickEnListaDeComidasTeLleveALaVistaListaDeComidas(){

   ModelAndView mav= whenHagasClickTeLleveAunaVista();

    thenEsLaVistaCorrecta(mav);

}

    private void thenEsLaVistaCorrecta(ModelAndView mav) {
        assertThat(mav.getViewName().toString(), equalToIgnoringCase("listaDeComidas")) ;
    }

    private ModelAndView whenHagasClickTeLleveAunaVista() {
      ModelAndView mav  =controladorListaDeComidasMock.irAListaDeComidas();
    return mav;
    }

    @Test
    public void queAlEntrarALaVistaListaComidasMuestreLosAlimentosParaLaSemana(){

        whenMuestreLosAlimentos(usuarioMock);
        thenLosAlimentosSonCorrectos();
    }

    private void thenLosAlimentosSonCorrectos() {
    }

    private void whenMuestreLosAlimentos(Usuario usuarioMock) {
        ModelAndView mav=controladorListaDeComidasMock.mostrarAlimentosDisponibles(usuarioMock);
    }
}
