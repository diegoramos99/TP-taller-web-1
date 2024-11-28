package com.tallerwebi.dominio;
import java.util.List;

public interface ServicioReceta {
    List<Receta> BuscarRecetaPorNombre(String nombre);
    Receta obtenerRecetaPorId(Long id);

    List<Receta> getRecetas();

    void actualizarReceta(Receta receta);

    void gurdarReceta(Receta nuevaReceta);

    void eliminarReceta(Long id);

    List<Receta> BuscarRecetaPorNombreYingrediente(String search);
}
