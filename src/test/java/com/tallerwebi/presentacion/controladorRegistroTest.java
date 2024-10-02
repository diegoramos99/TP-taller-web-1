package com.tallerwebi.presentacion;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class controladorRegistroTest {

        ControladorRegistro controladorRegistro=new ControladorRegistro();
    @Test
    public void siExisteEmailYPaswordRegistroExitoso(){
    // given
    //when
    //then

    givenNoexisteUsuario();


    ModelAndView mav= whenRegistroUsuario("diego@gmail.com","123","123");


    thenElRegistroEsExitoso(mav);
    }




    private void givenNoexisteUsuario() {
    }

    private ModelAndView whenRegistroUsuario(String email,String contracena,String validarContracena ) {
        String emailVacio=email;
        String contracenaUsada=contracena;
        String validarContracenaUsada=validarContracena;
      ModelAndView mav =  controladorRegistro.registrar(emailVacio ,contracena,validarContracena);
        return mav;
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat( mav.getViewName(), equalToIgnoringCase("redirect:/login"));

    }


 @Test
public void siElEmailElRegistroFalla(){
     givenNoexisteUsuario();

     ModelAndView mav= whenRegistroUsuario("","123","123");

     thenElRegistroFalla(mav);


 }
private void thenElRegistroFalla(ModelAndView mav) {
    assertThat( mav.getViewName(), equalToIgnoringCase("redirect:/registro"));
    assertThat( mav.getModel().get("error").toString(), equalToIgnoringCase("el email es obligatorio"));

}


    @Test
    public void siLasContracenasSonDistintaFalla(){
        // given
        //when
        //then

        givenNoexisteUsuario();


        ModelAndView mav= whenRegistroUsuario("diego@gmail.com" ,"123","abc");


        thenElRegistroFallaSiSonDistintas(mav);
    }

    private void thenElRegistroFallaSiSonDistintas(ModelAndView mav) {
        assertThat( mav.getViewName(), equalToIgnoringCase("redirect:/registro"));
        assertThat( mav.getModel().get("contraceñaDistinta").toString(), equalToIgnoringCase("las contraceñas deben ser iguales"));

    }

}
