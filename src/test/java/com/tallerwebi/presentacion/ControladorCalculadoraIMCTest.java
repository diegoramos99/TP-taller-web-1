package com.tallerwebi.presentacion;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

public class ControladorCalculadoraIMCTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ControladorCalculadoraIMC controlador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controlador).build();
    }



    /*
    @Test
    public void mostrarCalculadoraTest() throws Exception {
        mockMvc.perform(get("/calculadoraIMC"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculadoraIMC.html"));
    }

     */

    @Test
    public void calcularIMCHombreNormalTest() {
        ModelAndView modelAndView = controlador.calcularIMC("hombre", 180, 75, 30);

        double imcReal = (double) modelAndView.getModel().get("imc");
        System.out.println("IMC calculado: " + imcReal); // Para depuración

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Normal", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.8, modelAndView.getModel().get("altura"));
        assertEquals(75.0, modelAndView.getModel().get("peso"));
        assertEquals(30, modelAndView.getModel().get("edad"));
        assertEquals(23.15, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerBajoPesoTest() {
        ModelAndView modelAndView = controlador.calcularIMC("mujer", 160, 45, 28);
        //queria comparar double con un objeto, entonces tengo q castear el double para q lo convierta en objeto
        // casteo el Double
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.6, modelAndView.getModel().get("altura"));
        assertEquals(45.0, modelAndView.getModel().get("peso"));
        assertEquals(28, modelAndView.getModel().get("edad"));
        assertEquals(17.58, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombreObesidadTest() {
        ModelAndView modelAndView = controlador.calcularIMC("hombre", 175, 95, 40);

        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Obesidad", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.75, modelAndView.getModel().get("altura"));
        assertEquals(95.0, modelAndView.getModel().get("peso"));
        assertEquals(40, modelAndView.getModel().get("edad"));
        assertEquals(31.02, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerSobrepesoTest() {
        ModelAndView modelAndView = controlador.calcularIMC("mujer", 165, 70, 25);

        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.65, modelAndView.getModel().get("altura"));
        assertEquals(70.0, modelAndView.getModel().get("peso"));
        assertEquals(25, modelAndView.getModel().get("edad"));
        assertEquals(25.71, imcReal, 0.01);
    }
    @Test
    public void calcularIMCHombrePesoInsuficienteTest() {
        ModelAndView modelAndView = controlador.calcularIMC("hombre", 180, 60, 22);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.8, modelAndView.getModel().get("altura"));
        assertEquals(60.0, modelAndView.getModel().get("peso"));
        assertEquals(22, modelAndView.getModel().get("edad"));
        assertEquals(18.52, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombreSobrepesoTest() {
        ModelAndView modelAndView = controlador.calcularIMC("hombre", 190, 95, 30);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.9, modelAndView.getModel().get("altura"));
        assertEquals(95.0, modelAndView.getModel().get("peso"));
        assertEquals(30, modelAndView.getModel().get("edad"));
        assertEquals(26.3, imcReal, 0.1);
    }

    @Test
    public void calcularIMCMujerObesidadTest() {
        ModelAndView modelAndView = controlador.calcularIMC("mujer", 160, 85, 45);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Obesidad", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.6, modelAndView.getModel().get("altura"));
        assertEquals(85.0, modelAndView.getModel().get("peso"));
        assertEquals(45, modelAndView.getModel().get("edad"));
        assertEquals(33.2, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombrePesoInsuficienteExtremoTest() {
        ModelAndView modelAndView = controlador.calcularIMC("hombre", 175, 50, 30);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.75, modelAndView.getModel().get("altura"));
        assertEquals(50.0, modelAndView.getModel().get("peso"));
        assertEquals(30, modelAndView.getModel().get("edad"));
        assertEquals(16.33, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerSobrepesoExtremoTest() {
        ModelAndView modelAndView = controlador.calcularIMC("mujer", 170, 80, 50);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.7, modelAndView.getModel().get("altura"));
        assertEquals(80.0, modelAndView.getModel().get("peso"));
        assertEquals(50, modelAndView.getModel().get("edad"));
        assertEquals(27.68, imcReal, 0.01);
    }

    @Test
    public void calcularIMCMujerObesidadExtremaTest() {
        ModelAndView modelAndView = controlador.calcularIMC("mujer", 165, 100, 40);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Obesidad", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.65, modelAndView.getModel().get("altura"));
        assertEquals(100.0, modelAndView.getModel().get("peso"));
        assertEquals(40, modelAndView.getModel().get("edad"));
        assertEquals(36.73, imcReal, 0.01);
    }

    @Test
    public void calcularIMCHombreNormalLímiteSuperiorTest() {
        ModelAndView modelAndView = controlador.calcularIMC("hombre", 175, 77, 28);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Sobrepeso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.75, modelAndView.getModel().get("altura"));
        assertEquals(77.0, modelAndView.getModel().get("peso"));
        assertEquals(28, modelAndView.getModel().get("edad"));
        assertEquals(25.1, imcReal, 0.1);
    }

    @Test
    public void calcularIMCMujerNormalLímiteInferiorTest() {
        ModelAndView modelAndView = controlador.calcularIMC("mujer", 160, 48, 22);
        double imcReal = (Double) modelAndView.getModel().get("imc");

        assertEquals("resultadoIMC", modelAndView.getViewName());
        assertEquals("Bajo peso", modelAndView.getModel().get("clasificacion"));
        assertEquals(1.6, modelAndView.getModel().get("altura"));
        assertEquals(48.0, modelAndView.getModel().get("peso"));
        assertEquals(22, modelAndView.getModel().get("edad"));
        assertEquals(18.75, imcReal, 0.01);
    }

}