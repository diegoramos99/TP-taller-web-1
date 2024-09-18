package com.tallerwebi.presentacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
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
}