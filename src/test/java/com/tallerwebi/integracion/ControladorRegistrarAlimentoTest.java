package com.tallerwebi.integracion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import com.tallerwebi.presentacion.ControladorRegistrarAlimento;
import com.tallerwebi.presentacion.Macronutrientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorRegistrarAlimentoTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private ServicioAlimento servicioAlimentoMock;
    private ServicioLogin servicioLoginMock;
    private ServicioCalculoNutricional servicioCalculoNutricionalMock;


    @BeforeEach
    public void init() {
        servicioAlimentoMock = mock(ServicioAlimento.class);
        servicioLoginMock = mock(ServicioLogin.class);
        servicioCalculoNutricionalMock = mock(ServicioCalculoNutricional.class);

        ControladorRegistrarAlimento controladorRegistrarAlimento = new ControladorRegistrarAlimento(servicioAlimentoMock, servicioLoginMock, servicioCalculoNutricionalMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controladorRegistrarAlimento).build();
    }

    @Test
    public void debeRetornarLaPaginaDeRegistrarAlimentos() throws Exception {
        String fecha = "2024-10-01";
        List<RegistroComida> alimentos = new ArrayList<>();

        when(servicioAlimentoMock.obtenerRegistrosPorFecha(fecha)).thenReturn(alimentos);

        Usuario mockUsuario = new Usuario();
        mockUsuario.setEmail("usuario@example.com");

        when(servicioLoginMock.consultarUsuarioPorEmail("usuario@example.com")).thenReturn(mockUsuario);


        Double caloriasDiariasEsperadas = 2500.0;
        when(servicioCalculoNutricionalMock.calcularCaloriasDiarias(mockUsuario)).thenReturn(caloriasDiariasEsperadas);


        Macronutrientes macronutrientesEsperados = new Macronutrientes();
        macronutrientesEsperados.setCarbohidratos(caloriasDiariasEsperadas * 0.50 / 4);
        macronutrientesEsperados.setProteinas(caloriasDiariasEsperadas * 0.30 / 4);
        macronutrientesEsperados.setGrasas(caloriasDiariasEsperadas * 0.20 / 9);
        when(servicioCalculoNutricionalMock.calcularMacronutrientes(caloriasDiariasEsperadas)).thenReturn(macronutrientesEsperados);


        MvcResult result = this.mockMvc.perform(get("/ver-Registrar-alimentos")
                        .param("fecha", fecha)
                        .sessionAttr("EMAIL", "usuario@example.com"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarAlimento"));
        assertThat(modelAndView.getModel().get("alimentos"), is(alimentos));

        assertThat(modelAndView.getModel().get("totalCaloriasDiarias"), is(Math.round(caloriasDiariasEsperadas)));
        assertThat(modelAndView.getModel().get("macronutrientes"), is(macronutrientesEsperados));

    }


    @Test
    public void debeRetornarMensajeCuandoTipoComidaEsNulo() throws Exception {
        Long id = 1L;
        String fechaStr = "2024-10-01";

        MvcResult result = this.mockMvc.perform(post("/registrarAlimentoSelecionado/{id}", id)
                        .param("comida", "")
                        .param("fecha", fechaStr)
                        .sessionAttr("EMAIL", "usuario@example.com")) // Establecer el atributo en la sesión
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getModel().get("mensaje"), is("El tipo de comida es requerido."));
    }

    @Test
    public void debeRetornarMensajeCuandoFechaEsNula() throws Exception {
        Long id = 1L;
        String tipoDeComida = "desayuno";

        MvcResult result = this.mockMvc.perform(post("/registrarAlimentoSelecionado/{id}", id)
                        .param("comida", tipoDeComida)
                        .param("fecha", "")
                        .sessionAttr("EMAIL", "usuario@example.com")) // Establecer el atributo en la sesión
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getModel().get("mensaje"), is("La fecha es requerida."));
    }


    @Test
    public void debeRetornarMensajeCuandoUsuarioNoEsEncontrado() throws Exception {
        Long id = 1L;
        String tipoDeComida = "desayuno";
        String fechaStr = "2024-10-01";
        Alimento alimentoMock = new Alimento();

        when(servicioAlimentoMock.obtenerAlimentoPorId(id)).thenReturn(alimentoMock);
        when(servicioLoginMock.consultarUsuarioPorEmail("usuario@example.com")).thenReturn(null);

        MvcResult result = this.mockMvc.perform(post("/registrarAlimentoSelecionado/{id}", id)
                        .param("comida", tipoDeComida)
                        .param("fecha", fechaStr)
                        .sessionAttr("EMAIL", "usuario@example.com")) // Establecer el atributo en la sesión
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getModel().get("mensaje"), is("Usuario no encontrado."));
    }
}
