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

    @RequestMapping(value = "/modificarAlimento", method = RequestMethod.POST)
    public ModelAndView modificarAlimento(@RequestParam("id") Long id, HttpServletRequest request) {

        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        Alimento alimento = servicioAlimento.obtenerAlimentoPorId(id);
        model.addAttribute("alimentoSeleccionado", alimento);
        return new ModelAndView("modificarAlimento", model);
    }


     @PostMapping("/guardarAlimento")
    public ModelAndView guardarAlimento(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("calorias") Double calorias,
            @RequestParam("proteinas") Double proteinas,
            @RequestParam("grasas") Double grasas,
            @RequestParam("carbohidratos") Double carbohidratos,
            @RequestParam("categoria") String categoria,
            @RequestParam("cantidad") String cantidad,
            @RequestParam("dieta") String dieta,
            HttpServletRequest request
    ) {


        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        Alimento alimento = servicioAlimento.obtenerAlimentoPorId(id);

        if (alimento != null) {

            alimento.setNombre(nombre);
            alimento.setCalorias(calorias);
            alimento.setProteinas(proteinas);
            alimento.setGrasas(grasas);
            alimento.setCarbohidratos(carbohidratos);
            alimento.setCategoria(categoria);
            alimento.setCantidad(cantidad);
            alimento.setDieta(dieta);


            servicioAlimento.actualizarAlimento(alimento);


            return new ModelAndView("redirect:/ver-lista-comidas");
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("mensaje", "El alimento no existe.");
            return modelAndView;
        }
    }

    @GetMapping("/agregarAlimento")
    public ModelAndView mostrarFormularioAgregarAlimento(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        model.addAttribute("alimento", new Alimento());
        return new ModelAndView("agregarAlimento", model);
    }

     @PostMapping("/guardarNuevoAlimento")
    public ModelAndView guardarAlimento(@ModelAttribute Alimento alimento) {
        ModelMap model = new ModelMap();
        try {
            servicioAlimento.guardar(alimento);
            model.addAttribute("mensaje", "Alimento guardado con éxito.");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar el alimento: " + e.getMessage());
        }
        return new ModelAndView("redirect:/ver-lista-comidas", model);
    }

     @RequestMapping(value = "/eliminarAlimento", method = RequestMethod.POST)
    public ModelAndView eliminarEjercicio(@RequestParam("id") Long id) {

        Alimento alimento = servicioAlimento.obtenerAlimentoPorId(id);

        if (alimento != null) {
            servicioAlimento.eliminarAlimento(id);
        }
        return new ModelAndView("redirect:/ver-lista-comidas");
    }

}

















