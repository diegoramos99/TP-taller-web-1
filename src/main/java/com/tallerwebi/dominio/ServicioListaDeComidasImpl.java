package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioAlimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("servicioListaDeComidas")
@Transactional
public class ServicioListaDeComidasImpl implements  ServicioListaDeComidas{
    private RepositorioAlimento repositorioAlimento;
    @Autowired
    public void ServicioListaDeComidas(RepositorioAlimento listaDeComidas){
        this.repositorioAlimento=listaDeComidas;
    }
@Override
    public List<Alimento> buscarAlimentos(Usuario usuarioMock) {
        List <Alimento> alimentos= repositorioAlimento.traerTodosLosAlimentos();

        return alimentos;
    }
@Override
    public List<List<Alimento>> separarLosAlimentosPorTipo(List<Alimento> alimentosList){
        List<List<Alimento>> alimentosSeparadosPorTipo = new ArrayList<>();
        List<Alimento> desayunos = new ArrayList<>();
        List<Alimento> almuerzos = new ArrayList<>();
        List<Alimento> meriendas = new ArrayList<>();
        List<Alimento> cenas = new ArrayList<>();

        for (Alimento alimento : alimentosList){
            switch (alimento.getTipo().toLowerCase()){
                case "desayuno":
                    desayunos.add(alimento);
                    break;
                case "almuerzo":
                    almuerzos.add(alimento);
                    break;
                case "merienda":
                    meriendas.add(alimento);
                    break;
                case "cena":
                    cenas.add(alimento);
                    break;
                default:
                    // Opcional: manejar tipos desconocidos
                    break;
            }
        }

        alimentosSeparadosPorTipo.add(desayunos);
        alimentosSeparadosPorTipo.add(almuerzos);
        alimentosSeparadosPorTipo.add(meriendas);
        alimentosSeparadosPorTipo.add(cenas);

        return alimentosSeparadosPorTipo;
    }


@Override
    public List<List<Alimento>> comidasParaElDiaRespetandoLaDieta(Usuario usuarioMock, List<List<Alimento>> alimentosSeparadosPorTipo ){
        List<Alimento> desayunos = alimentosSeparadosPorTipo.get(0);
        List<Alimento> almuerzos = alimentosSeparadosPorTipo.get(1);
        List<Alimento> meriendas = alimentosSeparadosPorTipo.get(2);
        List<Alimento> cenas = alimentosSeparadosPorTipo.get(3);

        String dieta = usuarioMock.getPreferenciaAlimenticia();
        List<Alimento> desayunosDeDieta = new ArrayList<>();
        List<Alimento> almuerzosDeDieta = new ArrayList<>();
        List<Alimento> meriendasDeDieta = new ArrayList<>();
        List<Alimento> cenasDeDieta = new ArrayList<>();

        for (Alimento alimento : desayunos){
            if (dieta.equalsIgnoreCase(alimento.getDieta())){
                desayunosDeDieta.add(alimento);
            }
        }
        for (Alimento alimento : almuerzos){
            if (dieta.equalsIgnoreCase(alimento.getDieta())){
                almuerzosDeDieta.add(alimento);
            }
        }
        for (Alimento alimento : meriendas){
            if (dieta.equalsIgnoreCase(alimento.getDieta())){
                meriendasDeDieta.add(alimento);
            }
        }
        for (Alimento alimento : cenas){
            if (dieta.equalsIgnoreCase(alimento.getDieta())){
                cenasDeDieta.add(alimento);
            }
        }

        List<List<Alimento>> comidasParaElDia = new ArrayList<>();
        comidasParaElDia.add(desayunosDeDieta);
        comidasParaElDia.add(almuerzosDeDieta);
        comidasParaElDia.add(meriendasDeDieta);
        comidasParaElDia.add(cenasDeDieta);
        return comidasParaElDia;
    }

    @Override
    public List<Alimento> comidasParaEldia(Usuario usuarioMock, List<List<Alimento>> alimentosSeparadosPorTipoYDieta) {
        List<Alimento> comidaParaUnDia = new ArrayList<>();
        Random random = new Random();

        if (alimentosSeparadosPorTipoYDieta.size() < 4) {
            throw new IllegalArgumentException("La lista de comidas separadas por tipo y dieta no contiene todas las categorías.");
        }

        List<Alimento> desayunos = alimentosSeparadosPorTipoYDieta.get(0);
        List<Alimento> almuerzos = alimentosSeparadosPorTipoYDieta.get(1);
        List<Alimento> meriendas = alimentosSeparadosPorTipoYDieta.get(2);
        List<Alimento> cenas = alimentosSeparadosPorTipoYDieta.get(3);

        if (desayunos.isEmpty() || almuerzos.isEmpty() || meriendas.isEmpty() || cenas.isEmpty()) {
            throw new IllegalArgumentException("No hay suficientes alimentos para cada tipo de comida según la dieta.");
        }

        int numeroRandomDes = random.nextInt(desayunos.size());
        int numeroRandomAlm = random.nextInt(almuerzos.size());
        int numeroRandomMer = random.nextInt(meriendas.size());
        int numeroRandomCen = random.nextInt(cenas.size());

        comidaParaUnDia.add(desayunos.get(numeroRandomDes));
        comidaParaUnDia.add(almuerzos.get(numeroRandomAlm));
        comidaParaUnDia.add(meriendas.get(numeroRandomMer));
        comidaParaUnDia.add(cenas.get(numeroRandomCen));

        return comidaParaUnDia;
    }

    @Override
    public List<List<Alimento>> buscarAlimentosParaLaSemana(Usuario usuario) {
        List<Alimento> alimentosList = buscarAlimentos(usuario);
        List<List<Alimento>> alimentosSeparadosPorTipo = separarLosAlimentosPorTipo(alimentosList);
        List<List<Alimento>> alimentosSeparadosPorTipoYDieta = comidasParaElDiaRespetandoLaDieta(usuario, alimentosSeparadosPorTipo);
        int cantidadDeDias = 7;
        List<List<Alimento>> alimentosParaLaSemana = new ArrayList<>();

        for (int i = 0; i < cantidadDeDias; i++) {
            List<Alimento> alimentosParaELdia = comidasParaEldia(usuario, alimentosSeparadosPorTipoYDieta);
            alimentosParaLaSemana.add(alimentosParaELdia);
        }

        return alimentosParaLaSemana;
    }



}
