package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.ServicioAlimento;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorAlimentoTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private ServicioAlimento servicioAlimentoMock;

    @BeforeEach
    public void init() {
        servicioAlimentoMock = mock(ServicioAlimento.class);
        com.tallerwebi.presentacion.ControladorAlimentoTest controladorAlimento = new com.tallerwebi.presentacion.ControladorAlimentoTest();
        this.mockMvc = MockMvcBuilders.standaloneSetup(controladorAlimento).build();
    }

    @Test
    public void debeRetornarLaPaginaDeBuscarAlimentos() throws Exception {
        // Crea un mock del HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("EMAIL", "usuario@example.com");

        // Realiza la solicitud con el mock del request
        MvcResult result = this.mockMvc.perform(get("/ver-Buscar-Alimento")
                        .sessionAttr("EMAIL", "usuario@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("buscarAlimento"))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("buscarAlimento"));
    }

    @Test
    public void debeMostrarAlimentosBuscados() throws Exception {
        String termino = "manzana";
        String comida = "fruta";
        String fecha = "2024-10-01";
        List<Alimento> resultados = new ArrayList<>();
        resultados.add(new Alimento());

        when(servicioAlimentoMock.BuscarAlimentoPorNombre(termino)).thenReturn(resultados);

        MvcResult result = this.mockMvc.perform(get("/buscarAlimentos")
                        .param("termino", termino)
                        .param("comida", comida)
                        .param("fecha", fecha))
                .andExpect(status().isOk())
                .andExpect(view().name("buscarAlimento"))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getModel().get("alimentos"), is(resultados));
        assertThat(modelAndView.getModel().get("termino"), is(termino));
        assertThat(modelAndView.getModel().get("comida"), is(comida));
        assertThat(modelAndView.getModel().get("fecha"), is(fecha));

        assertThat(servicioAlimentoMock.BuscarAlimentoPorNombre(termino), is(resultados));
    }

    @Test
    public void debeRetornarLaPaginaConParametrosComidaYFecha() throws Exception {
        String comida = "verdura";
        String fecha = "2024-10-01";

        // Establece el atributo EMAIL en la sesión
        this.mockMvc.perform(get("/ver-Buscar-Alimento")
                        .param("comida", comida)
                        .param("fecha", fecha)
                        .sessionAttr("EMAIL", "usuario@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("buscarAlimento"))
                .andExpect(model().attribute("comida", comida))
                .andExpect(model().attribute("fecha", fecha));
    }
}
