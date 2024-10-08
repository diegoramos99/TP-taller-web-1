package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Receta;
import com.tallerwebi.dominio.ServicioAlimento;
import com.tallerwebi.dominio.ServicioReceta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorReceta {

    private ServicioReceta servicioReceta;

    @Autowired

    public ControladorReceta(ServicioReceta servicioReceta) {
        this.servicioReceta = servicioReceta;

    }

    @GetMapping("/buscar")
    public String buscarForm(Model model) {
        model.addAttribute("nombre", "");
        model.addAttribute("recetas", new ArrayList<Receta>());
        return "buscar";
    }

    @PostMapping("/buscar")
    public String buscarRecetas(@RequestParam String nombre, Model model) {
        List<Receta> recetas = servicioReceta.BuscarRecetaPorNombre(nombre);
        model.addAttribute("nombre", nombre);
        model.addAttribute("recetas", recetas);
        return "buscar";
    }
}