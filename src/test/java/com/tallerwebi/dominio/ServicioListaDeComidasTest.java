package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioAlimento;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioListaDeComidasTest {
    private Usuario usuarioMock;
    private HttpServletRequest httpServletRequestMock;
    private HttpSession sessionMock;
    private RepositorioAlimento repositorioAlimentoMock;
    private ServicioListaDeComidas servicioListaDeComidasMock;
    @BeforeEach
    public void init(){
        usuarioMock=mock(Usuario.class);
        when(usuarioMock.getPreferenciaAlimenticia()).thenReturn("vegano");
        repositorioAlimentoMock=mock(RepositorioAlimento.class);
        servicioListaDeComidasMock=mock(ServicioListaDeComidas.class);
        httpServletRequestMock=mock(HttpServletRequest.class);
        sessionMock=mock(HttpSession.class);
}
    @Test
    public void queTraigaTodosLosAlimentosRegistrados(){
        List<Alimento> alimentos=new ArrayList<>();
        Alimento alimento1=new Alimento();
        Alimento alimento2=new Alimento();
        Alimento alimento3=new Alimento();
        Alimento alimento4=new Alimento();
        alimentos.add(alimento1);
        alimentos.add(alimento2);
        alimentos.add(alimento3);
        alimentos.add(alimento4);
        when(repositorioAlimentoMock.traerTodosLosAlimentos()).thenReturn(alimentos);

        List <Alimento> whenAlimentos=whenTraigaTodosLosAlimentos();

        thenLosAlimentosSonCorrectos(whenAlimentos);
    }

    private void thenLosAlimentosSonCorrectos(List <Alimento> alimentos) {
        Alimento alimento1=alimentos.get(0);
        Alimento alimento2=alimentos.get(1);
        Alimento alimento3=alimentos.get(2);
        Alimento alimento4=alimentos.get(3);

        assertThat(alimento1, instanceOf(Alimento.class));
        assertThat(alimento2, instanceOf(Alimento.class));
        assertThat(alimento3, instanceOf(Alimento.class));
        assertThat(alimento4, instanceOf(Alimento.class));

    }

    private List<Alimento> whenTraigaTodosLosAlimentos() {

      List <Alimento> alimentos=repositorioAlimentoMock.traerTodosLosAlimentos();
        return alimentos;
    }
    @Test
    public void queSepareLosAlimentosPorTipo(){
        List <Alimento> alimentos1=new ArrayList<>();
        Alimento alimento1=new Alimento();
        Alimento alimento2=new Alimento();
        Alimento alimento3=new Alimento();
        Alimento alimento4=new Alimento();

        alimento1.setTipo("desayuno");
        alimento2.setTipo("almuerzo");
        alimento3.setTipo("merienda");
        alimento4.setTipo("cena");


        alimentos1.add(alimento1);
        alimentos1.add(alimento2);
        alimentos1.add(alimento3);
        alimentos1.add(alimento4);

        List<List<Alimento>> alimentosList=new ArrayList<>();
        List<Alimento> alimentosDes=new ArrayList<>();
        List<Alimento> alimentosAlm=new ArrayList<>();
        List<Alimento> alimentosMer=new ArrayList<>();
        List<Alimento> alimentosCen=new ArrayList<>();

        alimentosDes.add(alimento1);
        alimentosAlm.add(alimento2);
        alimentosMer.add(alimento3);
        alimentosCen.add(alimento4);

        alimentosList.add(alimentosDes);
        alimentosList.add(alimentosAlm);
        alimentosList.add(alimentosMer);
        alimentosList.add(alimentosCen);

        when(servicioListaDeComidasMock.separarLosAlimentosPorTipo(alimentos1)).thenReturn(alimentosList);

        List<List<Alimento>> alimentos= whenRecibeUnaListaDeAlimenTosDevuelvaUnaListaDeListasDeAlimentos(alimentos1);

        thenLosAlimentosEstanSeparadosPorTipo(alimentos);
    }

    private void thenLosAlimentosEstanSeparadosPorTipo(List<List<Alimento>> alimentos) {
        String tipoDesayuno="desayuno";
        String tipoAlmuerzo="almuerzo";
        String tipoMerienda="merienda";
        String tipoCena="cena";

        assertThat(alimentos.get(0).get(0).getTipo().toString(),equalToIgnoringCase(tipoDesayuno));
        assertThat(alimentos.get(1).get(0).getTipo().toString(),equalToIgnoringCase(tipoAlmuerzo));
        assertThat(alimentos.get(2).get(0).getTipo().toString(),equalToIgnoringCase(tipoMerienda));
        assertThat(alimentos.get(3).get(0).getTipo().toString(),equalToIgnoringCase(tipoCena));

    }

    private List<List<Alimento>> whenRecibeUnaListaDeAlimenTosDevuelvaUnaListaDeListasDeAlimentos(List <Alimento> alimentos) {
      return   servicioListaDeComidasMock.separarLosAlimentosPorTipo(alimentos);

    }
@Test
    public void queBusqueAlimentosParaLaSemana(){
    List<List<Alimento>> alimentosList=new ArrayList<>();

    List <Alimento> alimentos=new ArrayList<>();
    List <Alimento> alimentos1=new ArrayList<>();

    Alimento alimento1=new Alimento();
    Alimento alimento2=new Alimento();
    Alimento alimento3=new Alimento();
    Alimento alimento4=new Alimento();

    alimento1.setTipo("desayuno");
    alimento2.setTipo("almuerzo");
    alimento3.setTipo("merienda");
    alimento4.setTipo("cena");

    alimento1.setDieta("vegano");
    alimento2.setDieta("vegano");
    alimento3.setDieta("vegano");
    alimento4.setDieta("vegano");

    alimentos.add(alimento1);
    alimentos.add(alimento2);
    alimentos.add(alimento3);
    alimentos.add(alimento4);


    alimentosList.add(alimentos);
    alimentosList.add(alimentos1);

when(servicioListaDeComidasMock.buscarAlimentosParaLaSemana(usuarioMock)).thenReturn(alimentosList);
    List<List<Alimento>> whenAlimentos=whenBusqueAlimentosPAraLaSemana();


        theLosAlimentosSonLosEsperados(whenAlimentos , usuarioMock);
}

    private void theLosAlimentosSonLosEsperados(List<List<Alimento>> alimentos ,Usuario usuarioMock) {
        String dieta=usuarioMock.getPreferenciaAlimenticia();
        String desayuno="desayuno";
        String almuerzo="almuerzo";
        String merienda="merienda";
        String cena="cena";
        assertThat(alimentos.get(0).get(0).getDieta().toString(),equalToIgnoringCase(dieta));
        assertThat(alimentos.get(0).get(1).getDieta().toString(),equalToIgnoringCase(dieta));
        assertThat(alimentos.get(0).get(2).getDieta().toString(),equalToIgnoringCase(dieta));
        assertThat(alimentos.get(0).get(3).getDieta().toString(),equalToIgnoringCase(dieta));

        assertThat(alimentos.get(0).get(0).getTipo().toString(),equalToIgnoringCase(desayuno));
        assertThat(alimentos.get(0).get(1).getTipo().toString(),equalToIgnoringCase(almuerzo));
        assertThat(alimentos.get(0).get(2).getTipo().toString(),equalToIgnoringCase(merienda));
        assertThat(alimentos.get(0).get(3).getTipo().toString(),equalToIgnoringCase(cena));
    }

    private List<List<Alimento>> whenBusqueAlimentosPAraLaSemana() {
       return servicioListaDeComidasMock.buscarAlimentosParaLaSemana(usuarioMock);
    }
}
