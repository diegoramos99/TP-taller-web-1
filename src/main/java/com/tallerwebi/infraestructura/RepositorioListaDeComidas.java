package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.Usuario;

import java.util.List;

public interface RepositorioListaDeComidas {
    List<Alimento> buscarAlimentos();
}
