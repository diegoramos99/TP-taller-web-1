package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioListaDeComidas {
  List<Alimento> buscarAlimentos();

  List<List<Alimento>> separarLosAlimentosPorTipo(List<Alimento> alimentosList);

  List<List<Alimento>> buscarAlimentosParaLaSemana(Usuario usuarioMock);

  List<List<Alimento>> comidasParaElDiaRespetandoLaDieta(Usuario usuarioMock, List<List<Alimento>> alimentosSeparadosPorTipo);

  List<Alimento> comidasParaEldia(Usuario usuarioMock, List<List<Alimento>> alimentosSeparadosPorTipoYDieta);
}
