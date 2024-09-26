package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.ServicioAlimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorAlimento {

    private ServicioAlimento servicioAlimento;

    @Autowired
    public ControladorAlimento(ServicioAlimento servicioAlimento) {
        this.servicioAlimento = servicioAlimento;
    }

    @RequestMapping("/ver-Buscar-Alimento")
    public ModelAndView mostrarBuscador(){
        return new ModelAndView("buscarAlimento");
    }

    @RequestMapping("/buscarAlimentos")
    public ModelAndView mostrarAlimentoBuscado(@RequestParam(required = false) String termino) {
        ModelMap model = new ModelMap();
        List<Alimento> resultados = null;

        if (termino != null && !termino.isEmpty()) {
            resultados = servicioAlimento.BuscarAlimentoPorNombre(termino);
            model.addAttribute("alimentos", resultados);
            model.addAttribute("termino", termino);
        }

        return new ModelAndView("buscarAlimento", model);
    }


















}
