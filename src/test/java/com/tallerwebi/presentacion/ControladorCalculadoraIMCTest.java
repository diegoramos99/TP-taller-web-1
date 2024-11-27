package com.tallerwebi.presentacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControladorCalculadoraIMCTest {

    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioCalculoNutricional servicioCalculoNutricionalMock;
    private ServicioPerfilUsuario servicioPerfilUsuarioMock;
    private ControladorCalculadoraIMC controladorCalculadoraIMCTestMock;

    @BeforeEach
    public void init() {
        usuarioMock = mock(Usuario.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        requestMock.getSession().setAttribute("EMAIL", "test@example.com");
        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");

        servicioCalculoNutricionalMock = mock(ServicioCalculoNutricional.class);
        servicioPerfilUsuarioMock = mock(ServicioPerfilUsuario.class);
        controladorCalculadoraIMCTestMock=new ControladorCalculadoraIMC(servicioCalculoNutricionalMock,servicioPerfilUsuarioMock);
    }



    @Test
    public void calcularIMCHombreNormalTest() {
        double altura = 180; // en cm
        double peso = 75;
        Integer edad = 30;
        String genero = "hombre";

        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Normal", genero, altura / 100.0, peso, edad, 23.15);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);



        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC(genero, altura, peso, edad,requestMock);
        System.out.println(modelAndView.getViewName());

        double imcReal = (double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Normal", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.8, modelAndView.getModel().get("altura"));
        assertEquals(75.0, modelAndView.getModel().get("peso"));
        assertEquals(30.0, modelAndView.getModel().get("edad"));
        assertEquals(23.15, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerBajoPesoTest() {
        double altura = 160; // en cm
        double peso = 45;
        Integer edad = 28;
        String genero = "mujer";

        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Bajo peso", genero, altura / 100.0, peso, edad, 17.58);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC(genero, 160, 45, 28, requestMock);



        System.out.println(modelAndView.getViewName());
        //queria comparar double con un objeto, entonces tengo q castear el double para q lo convierta en objeto
        // casteo el Double
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.6, modelAndView.getModel().get("altura"));
        assertEquals(45.0, modelAndView.getModel().get("peso"));
        assertEquals(28.0, modelAndView.getModel().get("edad"));
        assertEquals(17.58, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombreObesidadTest() {
        double altura = 175; // en cm
        double peso = 95;
        Integer edad = 40;
        String genero = "hombre";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Obesidad", genero, altura / 100.0, peso, edad, 31.02);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC(genero, altura, peso, edad, requestMock);

        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Obesidad", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.75, modelAndView.getModel().get("altura"));
        assertEquals(95.0, modelAndView.getModel().get("peso"));
        assertEquals(40.0, modelAndView.getModel().get("edad"));
        assertEquals(31.02, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerSobrepesoTest() {

        double altura = 165; // en cm
        double peso = 70;
        Integer edad = 25;
        String genero = "mujer";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Sobrepeso", genero, altura / 100.0, peso, edad, 25.71);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);

        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("mujer", 165, 70, 25, requestMock);

        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.65, modelAndView.getModel().get("altura"));
        assertEquals(70.0, modelAndView.getModel().get("peso"));
        assertEquals(25.0, modelAndView.getModel().get("edad"));
        assertEquals(25.71, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombrePesoInsuficienteTest() {
        double altura = 180; // en cm
        double peso = 60;
        Integer edad = 22;
        String genero = "hombre";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Bajo peso", genero, altura / 100.0, peso, edad, 18.52);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);

        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("hombre", 180, 60, 22, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.8, modelAndView.getModel().get("altura"));
        assertEquals(60.0, modelAndView.getModel().get("peso"));
        assertEquals(22.0, modelAndView.getModel().get("edad"));
        assertEquals(18.52, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombreSobrepesoTest() {
        double altura = 190; // en cm
        double peso = 95;
        Integer edad = 30;
        String genero = "hombre";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Sobrepeso", genero, altura / 100.0, peso, edad, 26.3);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("hombre", 190, 95, 30, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.9, modelAndView.getModel().get("altura"));
        assertEquals(95.0, modelAndView.getModel().get("peso"));
        assertEquals(30.0, modelAndView.getModel().get("edad"));
        assertEquals(26.3, imcReal, 0.1);
    }

    @Test
    public void calcularIMCMujerObesidadTest() {
        double altura = 160; // en cm
        double peso = 85;
        Integer edad = 45;
        String genero = "mujer";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Obesidad", genero, altura / 100.0, peso, edad, 33.2);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("mujer", 160, 85, 45, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Obesidad", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.6, modelAndView.getModel().get("altura"));
        assertEquals(85.0, modelAndView.getModel().get("peso"));
        assertEquals(45.0, modelAndView.getModel().get("edad"));
        assertEquals(33.2, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombrePesoInsuficienteExtremoTest() {
        double altura = 175; // en cm
        double peso = 50;
        Integer edad = 30;
        String genero = "hombre";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Bajo peso", genero, altura / 100.0, peso, edad, 16.33);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("hombre", 175, 50, 30, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.75, modelAndView.getModel().get("altura"));
        assertEquals(50.0, modelAndView.getModel().get("peso"));
        assertEquals(30.0, modelAndView.getModel().get("edad"));
        assertEquals(16.33, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerSobrepesoExtremoTest() {
        double altura = 170; // en cm
        double peso = 80;
        Integer edad = 50;
        String genero = "mujer";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Sobrepeso", genero, altura / 100.0, peso, edad, 27.68);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("mujer", 170, 80, 50, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.7, modelAndView.getModel().get("altura"));
        assertEquals(80.0, modelAndView.getModel().get("peso"));
        assertEquals(50.0, modelAndView.getModel().get("edad"));
        assertEquals(27.68, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerObesidadExtremaTest() {
        double altura = 165; // en cm
        double peso = 100;
        Integer edad = 40;
        String genero = "mujer";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Obesidad", genero, altura / 100.0, peso, edad, 36.73);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("mujer", 165, 100, 40, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Obesidad", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.65, modelAndView.getModel().get("altura"));
        assertEquals(100.0, modelAndView.getModel().get("peso"));
        assertEquals(40.0, modelAndView.getModel().get("edad"));
        assertEquals(36.73, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombreNormalLímiteSuperiorTest() {
        double altura = 175; // en cm
        double peso = 77;
        Integer edad = 28;
        String genero = "hombre";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Sobrepeso", genero, altura / 100.0, peso, edad, 25.1);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("hombre", 175, 77, 28, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.75, modelAndView.getModel().get("altura"));
        assertEquals(77.0, modelAndView.getModel().get("peso"));
        assertEquals(28.0, modelAndView.getModel().get("edad"));
        assertEquals(25.1, imcReal, 0.1);
    }

    @Test
    public void calcularIMCMujerNormalLímiteInferiorTest() {
        double altura = 160; // en cm
        double peso = 48;
        Integer edad = 22;
        String genero = "mujer";
        Usuario usuario=new Usuario();

        DatosIMC datosIMC = new DatosIMC("Bajo peso", genero, altura / 100.0, peso, edad, 18.75);


        when(requestMock.getSession().getAttribute("EMAIL")).thenReturn("test@example.com");
        when(servicioCalculoNutricionalMock.calcularIMC(genero, altura / 100.0, peso,  edad)).thenReturn(datosIMC);
        when(servicioPerfilUsuarioMock.buscarUsuarioPoreEmail("test@example.com")).thenReturn(usuario);
        ModelAndView modelAndView = controladorCalculadoraIMCTestMock.calcularIMC("mujer", 160, 48, 22, requestMock);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.6, modelAndView.getModel().get("altura"));
        assertEquals(48.0, modelAndView.getModel().get("peso"));
        assertEquals(22.0, modelAndView.getModel().get("edad"));
        assertEquals(18.75, imcReal, 0.01);
    }

}