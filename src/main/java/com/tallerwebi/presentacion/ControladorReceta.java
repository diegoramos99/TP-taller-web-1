package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.tallerwebi.dominio.ServicioReceta;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorReceta {

    private final ServicioReceta servicioReceta;

    @Autowired
    public ControladorReceta(ServicioReceta servicioReceta) {
        this.servicioReceta = servicioReceta;
    }

    @RequestMapping(path = "/recetas", method = RequestMethod.GET)
    public ModelAndView mostrarRecetas() { return new ModelAndView("recetas.html");}


    @RequestMapping(path = "/buscar")
    public ModelAndView buscarRecetas(@RequestParam String nombre) {
        ModelMap model = new ModelMap();
        model.addAttribute("nombre", nombre);
        if (nombre != null && !nombre.isEmpty()) {
            List<Receta> resultados = servicioReceta.BuscarRecetaPorNombre(nombre);
            model.addAttribute("recetas", resultados);

        }

        return new ModelAndView("recetas", model);
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView obtenerRecetaPorId(@PathVariable Long id) {
        Receta receta = servicioReceta.obtenerRecetaPorId(id);
        ModelAndView mav = new ModelAndView("recetas");
        mav.addObject("receta", receta);
        return mav;
    }
}