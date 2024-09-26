package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.ServicioAlimento;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import com.tallerwebi.presentacion.ControladorAlimento;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        ControladorAlimento controladorAlimento = new ControladorAlimento(servicioAlimentoMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controladorAlimento).build();
    }

    @Test
    public void debeRetornarLaPaginaDeBuscarAlimentos() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ver-Buscar-Alimento"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("buscarAlimento"));
    }


    @Test
    public void debeRetornarLosAlimentosCuandoSeBuscaUnTermino() throws Exception {
         Alimento huevo = new Alimento();
         huevo.setNombre("Huevo");
         huevo.setCalorias(155);
         huevo.setProteinas(13);
         huevo.setGrasas(11);
         huevo.setCarbohidratos(1);
         huevo.setCategoria("Proteínas");

         List<Alimento> alimentosMock = new ArrayList<>();
         alimentosMock.add(huevo);

         when(servicioAlimentoMock.BuscarAlimentoPorNombre("Huevo")).thenReturn(alimentosMock);

         MvcResult result = mockMvc.perform(get("/buscarAlimentos")
                .param("termino", "Huevo"))
                .andExpect(status().isOk())
                .andReturn();

         ModelAndView modelAndView = result.getModelAndView();
         assert modelAndView != null;
         assertThat(modelAndView.getViewName(), equalToIgnoringCase("buscarAlimento"));


         List<Alimento> alimentosRetornados = (List<Alimento>) modelAndView.getModel().get("alimentos");
         assertThat(alimentosRetornados, hasSize(1));
         assertThat(alimentosRetornados, hasItem(huevo));

         String terminoBusqueda = (String) modelAndView.getModel().get("termino");
         assertThat(terminoBusqueda, equalTo("Huevo"));


}


}