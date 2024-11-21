package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ControladorRecetaTest {

    private ControladorReceta controladorRecetaMock;
    private Usuario usuarioMock;
    private DatosLogin datosLoginMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioReceta servicioRecetaMock;
    private ServicioImagen servicioImagenMock;

    @BeforeEach
    public void init() {
    // Preparación de datos de login
    datosLoginMock = new DatosLogin("dami@unlam.com", "123");
    usuarioMock = mock(Usuario.class);
    when(usuarioMock.getEmail()).thenReturn("dami@unlam.com");

    requestMock = mock(HttpServletRequest.class);
    sessionMock = mock(HttpSession.class);

    when(requestMock.getSession()).thenReturn(sessionMock);
    when(sessionMock.getAttribute("EMAIL")).thenReturn("dami@unlam.com");
    when(sessionMock.getAttribute("ROL")).thenReturn("ADMIN");

    // Mock de los servicios
    servicioRecetaMock = mock(ServicioReceta.class);
    servicioImagenMock = mock(ServicioImagen.class);

    // Controlador a probar
    controladorRecetaMock = new ControladorReceta(servicioRecetaMock, servicioImagenMock);
}
    @Test
    public void irALaVistaRecetaCuandoClickeaEnElNavLaSolapaReceta() {
        // preparacion
        ModelAndView mav = new ModelAndView("recetas.html");
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");
        when(controladorRecetaMock.mostrarRecetas(requestMock)).thenReturn(mav);

        // ejecucion
        ModelAndView modelAndView = controladorRecetaMock.mostrarRecetas(requestMock);

        // validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("recetas.html"));
    }

    @Test
    public void queAlBuscarRecetasTraigaLasRecetasYVayaALaVistaRecetas() {
        // preparacion
        ModelMap map = new ModelMap();
        List<Receta> receta = new ArrayList<>();
        Receta receta1 = new Receta("arroz Con Pollo");
        Receta receta2 = new Receta("arroz");
        receta.add(receta1);
        receta.add(receta2);

        map.put("recetas", receta);
        ModelAndView mav = new ModelAndView("recetas.html", map);
        // when(controladorRecetaMock.buscarRecetas("arroz")).thenReturn(mav);
        when(servicioRecetaMock.BuscarRecetaPorNombre("arroz")).thenReturn(receta);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");

        // ejecucion
        String nombreReceta = "arroz";
        ModelAndView modelAndView = controladorRecetaMock.buscarRecetas(nombreReceta);
        List<Receta> receta3 = (List<Receta>) modelAndView.getModel().get("recetas");

        // validacion
        assertTrue(receta3.get(0) instanceof Receta);
        assertTrue(receta3.get(1) instanceof Receta);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("recetas"));

    }

    @Test
    public void queAlBuscarRecetasPorIDTraigaLasRecetasDelIdCorrespondiente() {
        // preparacion
        ModelMap map = new ModelMap();
        List<Receta> receta = new ArrayList<>();
        Receta receta1 = new Receta("arroz Con Pollo");
        receta1.setId(1L);
        Receta receta2 = new Receta("arroz");
        receta2.setId(2L);
        receta.add(receta1);
        receta.add(receta2);

        map.put("recetas", receta);
        when(servicioRecetaMock.obtenerRecetaPorId(1L)).thenReturn(receta1);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");

        // ejecucion
        Long ID = 1L;
        ModelAndView modelAndView = controladorRecetaMock.obtenerRecetaPorId(ID);
        Receta receta3 = (Receta) modelAndView.getModel().get("receta");

        // validacion

        assertThat(receta3.getId().toString(), equalToIgnoringCase("1"));

    }

    @Test
    public void queAlmodificarRecetameLLeveALaVIstaModificarReceta() {
        // preparacion
        Receta receta1 = new Receta("arroz Con Pollo");
        receta1.setId(1L);

        when(servicioRecetaMock.obtenerRecetaPorId(1L)).thenReturn(receta1);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");

        // ejecucion
        Long ID = 1L;
        ModelAndView modelAndView = controladorRecetaMock.modificarReceta(ID, requestMock);
        // validacion

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("modificarReceta"));

    }

    @Test
    public void queAlmodificarRecetaElModelTengaLosDatosCorrectos() {
        // preparacion
        Receta receta1 = new Receta("arroz Con Pollo");
        receta1.setId(1L);
        receta1.setNombre("arroz");
        receta1.setCalorias(100L);


        when(servicioRecetaMock.obtenerRecetaPorId(1L)).thenReturn(receta1);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");

        // ejecucion
        Long ID = 1L;
        ModelAndView modelAndView = controladorRecetaMock.modificarReceta(ID, requestMock);
        Receta receta3 = (Receta) modelAndView.getModel().get("recetaSeleccionada");

        // validacion

        assertThat(receta3.getNombre(), equalToIgnoringCase("arroz"));
        assertThat(receta3.getCalorias().toString(), equalToIgnoringCase("100"));
        assertThat(receta3.getId().toString(), equalToIgnoringCase("1"));


    }

    @Test
    public void queAlagregarRecetameLleveALaVistaCorrecta() {
        ModelAndView mav = new ModelAndView("recetas.html");
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");
        when(controladorRecetaMock.agregarReceta(requestMock)).thenReturn(mav);

        // ejecucion
        ModelAndView modelAndView = controladorRecetaMock.agregarReceta(requestMock);

        // validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("agregarReceta"));
    }

    @Test
    public void queAlGuardarRecetaNosLleveAverListaRecetas() throws IOException {
        // preparacion
        Receta receta1 = new Receta("arroz Con Pollo");
        receta1.setId(1L);
        receta1.setNombre("arroz");
        receta1.setCalorias(100L);


        when(servicioRecetaMock.obtenerRecetaPorId(1L)).thenReturn(receta1);

        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");

        MultipartFile imagen = null;
        // ejecucion

        ModelAndView modelAndView = controladorRecetaMock.guardarReceta(receta1, imagen, requestMock);

        // validacion

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/ver-lista-recetas"));


    }

    @Test
    public void queAlGuardarnuevaRecetaNosLleveAListaDeREcetas() throws IOException {
        // preparacion
        Receta receta1 = new Receta("arroz Con Pollo");
        receta1.setId(1L);
        receta1.setNombre("arroz");
        receta1.setCalorias(100L);

        when(servicioRecetaMock.obtenerRecetaPorId(1L)).thenReturn(receta1);

        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");
        MultipartFile imagen = null;
        // ejecucion

        ModelAndView modelAndView = controladorRecetaMock.guardarNuevaReceta(receta1.getNombre(), receta1.getIngredientes(), receta1.getPreparacion(), receta1.getTiempo(), receta1.getCalorias(), imagen, requestMock);

        // validacion

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/ver-lista-recetas"));


    }

    @Test
    public void queAlEliminarRecetaNosLleveAListaDeRecetas() throws IOException {
        // preparacion
        Receta receta1 = new Receta("arroz Con Pollo");
        receta1.setId(1L);
        receta1.setNombre("arroz");
        receta1.setCalorias(100L);

        when(servicioRecetaMock.obtenerRecetaPorId(1L)).thenReturn(receta1);

        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("EMAIL")).thenReturn("123@gmail.com");
        MultipartFile imagen = null;
        // ejecucion

        ModelAndView modelAndView = controladorRecetaMock.eliminarReceta(receta1.getId());

        // validacion

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/ver-lista-recetas"));


    }
}
