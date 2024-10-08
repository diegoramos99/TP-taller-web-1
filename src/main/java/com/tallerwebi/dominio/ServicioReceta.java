package com.tallerwebi.dominio;
import java.util.List;

public interface ServicioReceta {
    List<Receta> BuscarRecetaPorNombre(String nombre);
    Receta obtenerRecetaPorId(Long id);
}
