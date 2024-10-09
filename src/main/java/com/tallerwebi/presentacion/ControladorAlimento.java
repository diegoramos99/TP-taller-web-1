package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.Alimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorAlimento {

    private ServicioAlimento servicioAlimento;

    @Autowired
    public ControladorAlimento(ServicioAlimento servicioAlimento) {
        this.servicioAlimento = servicioAlimento;

    }

    @RequestMapping("/ver-Buscar-Alimento")
    public ModelAndView verBuscarAlimento(
            @RequestParam(value = "comida", required = false) String comida,
            @RequestParam(value = "fecha", required = false) String fecha , HttpServletRequest request) {

        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();

        model.addAttribute("comida", comida);
        model.addAttribute("fecha", fecha);

        return new ModelAndView("buscarAlimento", model);
    }


    @RequestMapping("/buscarAlimentos")
    public ModelAndView mostrarAlimentoBuscado(
            @RequestParam("termino") String termino,
            @RequestParam("comida") String comida,
            @RequestParam(value = "fecha", required = false) String fecha) {

        ModelMap model = new ModelMap();

        if (termino != null && !termino.isEmpty()) {
            List<Alimento> resultados = servicioAlimento.BuscarAlimentoPorNombre(termino);
            model.addAttribute("alimentos", resultados);
            model.addAttribute("termino", termino);
            model.addAttribute("comida", comida);
        }

        model.addAttribute("fecha", fecha);

        return new ModelAndView("buscarAlimento", model);
    }


}

















