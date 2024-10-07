package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.ServicioListaDeComidas;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorListaDeComidas {

    private ServicioListaDeComidas servicioListaDeComidas;
    @Autowired
    public void ControladorListaDeComidas(ServicioListaDeComidas servicioListaDeComidas){
        this.servicioListaDeComidas=servicioListaDeComidas;
    }
    @RequestMapping(path = "/listaComidas", method = RequestMethod.GET)
    public ModelAndView irAListaDeComidas() {

         return new ModelAndView("redirect:/listaDeComidas");
    }

    @RequestMapping("/listaDeComidas")
    public ModelAndView mostrarAlimentosDisponibles(Usuario usuarioMock) {
        List <Alimento> alimento=servicioListaDeComidas.buscarAlimentos(usuarioMock);
        ModelMap map=new ModelMap();
        map.put("alimentos",alimento);
        ModelAndView mav=new ModelAndView("listaDeComidas",map);
        return  mav;
    }
}
