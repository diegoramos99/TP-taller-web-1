package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


public class ControladorAlimentoTest {

    @InjectMocks
    private ControladorAlimento controladorAlimento;

    @Mock
    private ServicioAlimento servicioAlimento;

    @Mock
    private ServicioLogin servicioLogin;

    @Mock
    private ServicioCalculoNutricional servicioCalculoNutricional;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("EMAIL")).thenReturn("test@usuario.com");
        when(session.getAttribute("ROL")).thenReturn("ADMIN");

        // Mock de usuario
        Usuario usuarioMock = new Usuario();
        usuarioMock.setEmail("test@usuario.com");
        usuarioMock.setNombre("Test");
        usuarioMock.setRol("ADMIN");
        when(servicioLogin.consultarUsuarioPorEmail("test@usuario.com")).thenReturn(usuarioMock);

        Alimento alimentoMock = new Alimento();
        alimentoMock.setId(1L);
        alimentoMock.setNombre("Manzana");
        alimentoMock.setCalorias(52.0);
        alimentoMock.setProteinas(0.3);
        alimentoMock.setGrasas(0.2);
        alimentoMock.setCarbohidratos(14.0);
        alimentoMock.setCategoria("Fruta");
        alimentoMock.setCantidad("1 unidad");
        alimentoMock.setDieta("Vegana");
        when(servicioAlimento.obtenerAlimentoPorId(1L)).thenReturn(alimentoMock);

    }


    @Test
    public void modificarAlimentoSinSesionDeberiaRedirigirALogin() {
        when(session.getAttribute("EMAIL")).thenReturn(null);
        when(session.getAttribute("ROL")).thenReturn(null);

        ModelAndView modelAndView = controladorAlimento.modificarAlimento(1L, request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void modificarAlimentoConSesionAdminDeberiaMostrarVista() {
        when(session.getAttribute("EMAIL")).thenReturn("admin@usuario.com");
        when(session.getAttribute("ROL")).thenReturn("ADMIN");
        Alimento alimento = new Alimento();
        alimento.setId(1L);
        alimento.setNombre("Manzana");
        when(servicioAlimento.obtenerAlimentoPorId(1L)).thenReturn(alimento);

        ModelAndView modelAndView = controladorAlimento.modificarAlimento(1L, request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("modificarAlimento"));
        assertThat(modelAndView.getModel().get("alimentoSeleccionado"), notNullValue());
    }

    @Test
    public void guardarAlimentoSinSesionDeberiaRedirigirALogin() {
        when(session.getAttribute("EMAIL")).thenReturn(null);
        when(session.getAttribute("ROL")).thenReturn(null);

        ModelAndView modelAndView = controladorAlimento.guardarAlimento(1L, "Manzana", 52.0, 0.3, 0.2, 14.0, "Fruta", "1", "Vegana", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void guardarAlimentoNoExistenteDeberiaMostrarError() {
        when(session.getAttribute("EMAIL")).thenReturn("admin@usuario.com");
        when(session.getAttribute("ROL")).thenReturn("ADMIN");
        when(servicioAlimento.obtenerAlimentoPorId(1L)).thenReturn(null);

        ModelAndView modelAndView = controladorAlimento.guardarAlimento(1L, "Manzana", 52.0, 0.3, 0.2, 14.0, "Fruta", "1", "Vegana", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("error"));
        assertThat(modelAndView.getModel().get("mensaje").toString(), equalToIgnoringCase("El alimento no existe."));
    }

    @Test
    public void mostrarFormularioSinSesionDeberiaRedirigirALogin() {
        when(session.getAttribute("EMAIL")).thenReturn(null);
        when(session.getAttribute("ROL")).thenReturn(null);

        ModelAndView modelAndView = controladorAlimento.mostrarFormularioAgregarAlimento(request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void mostrarFormularioConSesionAdminDeberiaMostrarVista() {
        when(session.getAttribute("EMAIL")).thenReturn("admin@usuario.com");
        when(session.getAttribute("ROL")).thenReturn("ADMIN");

        ModelAndView modelAndView = controladorAlimento.mostrarFormularioAgregarAlimento(request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("agregarAlimento"));
        assertThat(modelAndView.getModel().get("alimento"), notNullValue());
    }




}
