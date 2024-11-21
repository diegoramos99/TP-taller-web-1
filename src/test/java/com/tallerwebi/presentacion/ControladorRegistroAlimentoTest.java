package com.tallerwebi.presentacion;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistroAlimentoTest {

    @InjectMocks
    private ControladorRegistrarAlimento controladorRegistroAlimento;

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

    private Usuario usuarioMock;
    private List<RegistroComida> registrosMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioMock = new Usuario();
        usuarioMock.setEmail("test@usuario.com");

        registrosMock = new ArrayList<>();
        RegistroComida registro = new RegistroComida();
        Alimento alimento = new Alimento();
        alimento.setNombre("Manzana");
        alimento.setCalorias(52.0);
        alimento.setProteinas(14.0);
        alimento.setGrasas(0.2);
        alimento.setCarbohidratos(0.3);
        registro.setFecha("2024-10-21");
        registro.setTipoComida("Cena");
        registro.setAlimento(alimento);
        registrosMock.add(registro);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("EMAIL")).thenReturn("test@usuario.com");
    }

    @Test
    public void verRegistrarAlimentosSinSesionDeberiaRedirigirALogin() {
        when(session.getAttribute("EMAIL")).thenReturn(null);

        ModelAndView modelAndView = controladorRegistroAlimento.verRegistrarAlimentos("2024-10-21", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void verRegistrarAlimentosConSesionDeberiaMostrarVista() {
        when(servicioAlimento.obtenerRegistrosPorFecha(anyString())).thenReturn(registrosMock);
        when(servicioLogin.consultarUsuarioPorEmail(anyString())).thenReturn(usuarioMock);
        when(servicioCalculoNutricional.calcularCaloriasDiarias(any(Usuario.class))).thenReturn(2000.0);
        Macronutrientes macros = new Macronutrientes(100.0, 50.0, 70.0);
        when(servicioCalculoNutricional.calcularMacronutrientes(2000.0)).thenReturn(macros);

        ModelAndView modelAndView = controladorRegistroAlimento.verRegistrarAlimentos("2024-10-21", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarAlimento"));
        assertThat(modelAndView.getModel().get("totalCalorias"), notNullValue());
    }

    @Test
    public void registrarAlimentoSinSesionDeberiaRedirigirALogin() {
        when(session.getAttribute("EMAIL")).thenReturn(null);

        ModelAndView modelAndView = controladorRegistroAlimento.alimentoSeleccionado(1L, "Desayuno", "2024-10-21", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void registrarAlimentoSinTipoComidaDeberiaMostrarError() {
        ModelAndView modelAndView = controladorRegistroAlimento.alimentoSeleccionado(1L, "", "2024-10-21", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarAlimento"));
        assertThat(modelAndView.getModel().get("mensaje").toString(), equalToIgnoringCase("El tipo de comida es requerido."));
    }

    @Test
    public void registrarAlimentoSinFechaDeberiaMostrarError() {
        ModelAndView modelAndView = controladorRegistroAlimento.alimentoSeleccionado(1L, "Almuerzo", "", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarAlimento"));
        assertThat(modelAndView.getModel().get("mensaje").toString(), equalToIgnoringCase("La fecha es requerida."));
    }

    @Test
    public void registrarAlimentoUsuarioNoEncontradoDeberiaMostrarError() {
        when(servicioLogin.consultarUsuarioPorEmail(anyString())).thenReturn(null);

        ModelAndView modelAndView = controladorRegistroAlimento.alimentoSeleccionado(1L, "Cena", "2024-10-21", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarAlimento"));
        assertThat(modelAndView.getModel().get("mensaje").toString(), equalToIgnoringCase("Usuario no encontrado."));
    }

    @Test
    public void registrarAlimentoCorrectamenteDeberiaRedirigirAVerRegistrarAlimentos() {
        Alimento alimento = new Alimento();
        alimento.setNombre("Manzana");
        alimento.setCalorias(52.0);
        alimento.setProteinas(14.0);
        alimento.setGrasas(0.2);
        alimento.setCarbohidratos(0.3);
        when(servicioAlimento.obtenerAlimentoPorId(1L)).thenReturn(alimento);
        when(servicioLogin.consultarUsuarioPorEmail(anyString())).thenReturn(usuarioMock);

        ModelAndView modelAndView = controladorRegistroAlimento.alimentoSeleccionado(1L, "Cena", "2024-10-21", request);

        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/ver-Registrar-alimentos?fecha=2024-10-21"));
        verify(servicioAlimento, times(1)).guardarRegistroAlimento(any(RegistroComida.class));
    }
}
