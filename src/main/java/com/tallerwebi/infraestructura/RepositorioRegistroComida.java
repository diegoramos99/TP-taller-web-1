package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.RegistroComida;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioRegistroComida {
    void guardar(RegistroComida registroComida);
    List<RegistroComida> buscarRegistroComidaPorFecha(String fecha);
}
