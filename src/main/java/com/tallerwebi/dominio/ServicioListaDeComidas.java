package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioListaDeComidas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioListaDeComidas {
    private RepositorioListaDeComidas repositorioListaDeComidas;
    @Autowired
    public void ServicioListaDeComidas(RepositorioListaDeComidas listaDeComidas){
        this.repositorioListaDeComidas=listaDeComidas;
    }

    public List<Alimento> buscarAlimentos(Usuario usuarioMock) {
        List <Alimento> alimentos= repositorioListaDeComidas.buscarAlimentos();

        return alimentos;
    }
}
