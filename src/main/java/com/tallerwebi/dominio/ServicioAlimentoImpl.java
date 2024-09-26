package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioAlimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioAlimento")
@Transactional
public class ServicioAlimentoImpl implements ServicioAlimento {

    RepositorioAlimento repositorioAlimento;

    @Autowired
    public ServicioAlimentoImpl(RepositorioAlimento repositorioAlimento) {
        this.repositorioAlimento = repositorioAlimento;
    }

    @Override
    public List<Alimento> BuscarAlimentoPorNombre(String nombre) {
        return repositorioAlimento.buscarAlimento(nombre);
    }
}
