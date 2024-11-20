package com.tallerwebi.dominio;
import com.tallerwebi.infraestructura.RepositorioReceta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioRecetaTest {

    private Usuario usuarioMock;
    private HttpServletRequest httpServletRequestMock;
    private HttpSession sessionMock;
    private RepositorioReceta repositorioRecetaMock;
    private ServicioReceta servicioRecetaMock;
    @BeforeEach
    public void init(){
        usuarioMock=mock(Usuario.class);
        when(usuarioMock.getPreferenciaAlimenticia()).thenReturn("vegano");
        repositorioRecetaMock=mock(RepositorioReceta.class);
        servicioRecetaMock=new ServicioRecetaImpl(repositorioRecetaMock);
        httpServletRequestMock=mock(HttpServletRequest.class);
        sessionMock=mock(HttpSession.class);
    }

    @Test
    public void queAlBuscarBuscarRecetaPorNombretraigaLaRecetaCorrecta() {
        // Configuración de datos de prueba
        List<Receta> recetas = new ArrayList<>();
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        Receta receta2 = new Receta();
        receta2.setNombre("arroz con pollo");

        Receta receta3 = new Receta();
        receta3.setNombre("arroz blanco");

        Receta receta4 = new Receta();
        receta4.setNombre("arroz frito");

        recetas.add(receta1);
        recetas.add(receta2);
        recetas.add(receta3);
        recetas.add(receta4);

        // Mock del repositorio para devolver las recetas
        when(repositorioRecetaMock.buscarReceta("arroz")).thenReturn(recetas);

        // Actuar
        List<Receta> resultado = servicioRecetaMock.BuscarRecetaPorNombre("arroz");

        // Afirmaciones
        assertNotNull(resultado);
        assertEquals(4, resultado.size()); // Todas las recetas contienen "arroz"
        assertThat(resultado.get(0).getNombre(), equalToIgnoringCase("arroz"));
        assertThat(resultado.get(1).getNombre(), equalToIgnoringCase("arroz con pollo"));
        assertThat(resultado.get(2).getNombre(), equalToIgnoringCase("arroz blanco"));
        assertThat(resultado.get(3).getNombre(), equalToIgnoringCase("arroz frito"));

}
    @Test
    public void queAlobtenerRecetaPorIdtraigaLaRecetaCorrecta() {
        Receta receta1 = new Receta();
        receta1.setId(1L);

        when(repositorioRecetaMock.obtenerReceta(1L)).thenReturn(receta1);

        // Actuar
        Long idBuscado=1L;
        Receta resultado = servicioRecetaMock.obtenerRecetaPorId(idBuscado);

        // Afirmaciones
        assertNotNull(resultado);
        assertThat(resultado.getId().toString(), equalToIgnoringCase(idBuscado.toString()));


    }
    @Test
    public void queAlBuscarReceetaTraigaTodasLasRecetas() {
        // Configuración de datos de prueba
        List<Receta> recetas = new ArrayList<>();
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        Receta receta2 = new Receta();
        receta2.setNombre("arroz con pollo");

        Receta receta3 = new Receta();
        receta3.setNombre("arroz blanco");

        Receta receta4 = new Receta();
        receta4.setNombre("arroz frito");

        recetas.add(receta1);
        recetas.add(receta2);
        recetas.add(receta3);
        recetas.add(receta4);
        int largoDELista=recetas.size();
        // Mock del repositorio para devolver las recetas
        when(repositorioRecetaMock.buscarReceta()).thenReturn(recetas);

        // Actuar
        List<Receta> resultado = servicioRecetaMock.getRecetas();

        // Afirmaciones
        assertNotNull(resultado);
        assertEquals(largoDELista, resultado.size()); // Todas las recetas contienen "arroz"
        assertThat(resultado.get(0).getNombre(), equalToIgnoringCase("arroz"));
        assertThat(resultado.get(1).getNombre(), equalToIgnoringCase("arroz con pollo"));
        assertThat(resultado.get(2).getNombre(), equalToIgnoringCase("arroz blanco"));
        assertThat(resultado.get(3).getNombre(), equalToIgnoringCase("arroz frito"));


    }
    @Test
    public void queAlActualizarUnaRecetaSeModifiqueCorrectamente() {
        List<Receta> recetas = new ArrayList<>();
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        doNothing().when(repositorioRecetaMock).actualizarReceta(receta1);
        servicioRecetaMock.actualizarReceta(receta1);

        verify(repositorioRecetaMock, times(1)).actualizarReceta(receta1);

    }
    @Test
    public void queAlguardarUnaRecetaSealmaceneCorrectamente() {
        List<Receta> recetas = new ArrayList<>();
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        doNothing().when(repositorioRecetaMock).guardarReceta(receta1);
        servicioRecetaMock.gurdarReceta(receta1);

        verify(repositorioRecetaMock, times(1)).guardarReceta(receta1);

    }
    @Test
    public void queAlEliminarUnaRecetaSeBorreCorrectamente() {
        List<Receta> recetas = new ArrayList<>();
        Receta receta1 = new Receta();
        receta1.setNombre("arroz");

        doNothing().when(repositorioRecetaMock).eliminarReceta(1L);
        Long recetaAeliminar=1L;
        servicioRecetaMock.eliminarReceta(recetaAeliminar);

        verify(repositorioRecetaMock, times(1)).eliminarReceta(recetaAeliminar);

    }
}
