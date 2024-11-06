package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioAlimento;
import com.tallerwebi.infraestructura.RepositorioRegistroComida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServicioAlimentoTest {

    @Mock
    private RepositorioAlimento repositorioAlimento;

    @Mock
    private RepositorioRegistroComida repositorioRegistroComida;

    @InjectMocks
    private ServicioAlimentoImpl servicioAlimento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarAlimentoPorNombre_debeRetornarListaDeAlimentos() {
        // Datos de prueba
        String nombre = "Manzana";
        Alimento alimentoEsperado = new Alimento();
        alimentoEsperado.setId(1L);
        alimentoEsperado.setNombre("Manzana");
        alimentoEsperado.setCalorias(52.0);
        alimentoEsperado.setProteinas(14.0);
        alimentoEsperado.setGrasas(0.2);
        alimentoEsperado.setCarbohidratos(0.3);
        List<Alimento> alimentosEsperados = Arrays.asList(alimentoEsperado);

        when(repositorioAlimento.buscarAlimento(nombre)).thenReturn(alimentosEsperados);

        // Ejecución
        List<Alimento> resultado = servicioAlimento.BuscarAlimentoPorNombre(nombre);

        // Verificación
        assertEquals(alimentosEsperados, resultado);
        verify(repositorioAlimento, times(1)).buscarAlimento(nombre);
    }

    @Test
    void obtenerAlimentoPorId_debeRetornarAlimento() {
        // Datos de prueba
        Long id = 1L;
        Alimento alimentoEsperado = new Alimento();
        alimentoEsperado.setId(1L);
        alimentoEsperado.setNombre("Manzana");
        alimentoEsperado.setCalorias(52.0);
        alimentoEsperado.setProteinas(14.0);
        alimentoEsperado.setGrasas(0.2);
        alimentoEsperado.setCarbohidratos(0.3);
        when(repositorioAlimento.obtenerAlimento(id)).thenReturn(alimentoEsperado);

        // Ejecución
        Alimento resultado = servicioAlimento.obtenerAlimentoPorId(id);

        // Verificación
        assertEquals(alimentoEsperado, resultado);
        verify(repositorioAlimento, times(1)).obtenerAlimento(id);
    }

    @Test
    void guardarRegistroAlimento_debeLlamarAlRepositorio() {
        // Datos de prueba
        RegistroComida registroComida = new RegistroComida();
        registroComida.setId(1L);

        // Ejecución
        servicioAlimento.guardarRegistroAlimento(registroComida);

        // Verificación
        verify(repositorioRegistroComida, times(1)).guardar(registroComida);
    }

    @Test
    void obtenerRegistrosPorFecha_debeRetornarListaDeRegistros() {
        // Datos de prueba
        String fecha = "2024-10-21";
        RegistroComida registroComida = new RegistroComida();
        registroComida.setId(1L);
        registroComida.setFecha(fecha);
        registroComida.setTipoComida("Desayuno");

        RegistroComida registroComida2 = new RegistroComida();
        registroComida2.setId(2L);
        registroComida2.setFecha(fecha);
        registroComida2.setTipoComida("Almuerzo");
        List<RegistroComida> registrosEsperados = Arrays.asList(
              registroComida,
              registroComida2
        );

        when(repositorioRegistroComida.buscarRegistroComidaPorFecha(fecha)).thenReturn(registrosEsperados);

        // Ejecución
        List<RegistroComida> resultado = servicioAlimento.obtenerRegistrosPorFecha(fecha);

        // Verificación
        assertEquals(registrosEsperados, resultado);
        verify(repositorioRegistroComida, times(1)).buscarRegistroComidaPorFecha(fecha);
    }
}
