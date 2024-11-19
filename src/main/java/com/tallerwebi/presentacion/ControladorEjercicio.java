package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Ejercicio;
import com.tallerwebi.dominio.ServicioEjercicio;
import com.tallerwebi.dominio.ServicioImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class ControladorEjercicio {

    private ServicioEjercicio servicioEjercicio;
    private ServicioImagen servicioImagen;

    @Autowired
    public ControladorEjercicio(ServicioEjercicio ServicioEjercicio, ServicioImagen ServicioImagen) {
        this.servicioImagen = ServicioImagen;
        this.servicioEjercicio = ServicioEjercicio;
    }


    @RequestMapping("/buscarEjercicio")
    public ModelAndView buscarEjercicio(HttpServletRequest request,
                                        @RequestParam(value = "termino", required = false) String termino,
                                        @RequestParam(value = "dia", required = false) String dia) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");

        }

        ModelMap model = new ModelMap();
        model.addAttribute("dia", dia != null ? dia : "");
        model.addAttribute("termino", termino != null ? termino : "");


        if (termino != null && !termino.isEmpty()) {
            List<Ejercicio> resultados = servicioEjercicio.buscarEjercicioPorNombreYCategoria(termino);
            model.addAttribute("ejercicios", resultados);
        } else {
            model.addAttribute("ejercicios", Collections.emptyList());
        }

        model.addAttribute("datosEjercicio", new DatosEjercicio());
        return new ModelAndView("buscarEjercicio", model);
    }

    @RequestMapping("/traerRutina")
    public ModelAndView traerRutina(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        return new ModelAndView("redirect:/buscarEjercicio", model);
    }

    @RequestMapping(value = "/modificarEjercicio", method = RequestMethod.POST)
    public ModelAndView modificarEjercicio(@RequestParam("id") Long id, HttpServletRequest request) {

        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        Ejercicio ejercicios = servicioEjercicio.ontenerEjecicioPorId(id);

        model.addAttribute("ejerciciosSeleccionado", ejercicios);


        return new ModelAndView("modificarEjercicio", model);
    }

    @RequestMapping("/agregarEjercicio")
    public ModelAndView agregarEjercicio(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("agregarEjercicio");
    }


    @RequestMapping(value = "/guardarEjercicio", method = RequestMethod.POST)
    public ModelAndView guardarEjercicio(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("categoria") String categoria,
            @RequestParam(value = "imagen", required = false) MultipartFile imagenNueva,
            @RequestParam("imagenOriginal") String imagenOriginal,
            HttpServletRequest request
    ) throws IOException {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        Ejercicio ejercicio = servicioEjercicio.ontenerEjecicioPorId(id);

        ejercicio.setNombre(nombre);
        ejercicio.setCategoria(categoria);


        if (imagenNueva != null && !imagenNueva.isEmpty()) {

            String rutaImagen = servicioImagen.guardarImagen(imagenNueva);
            String imagenbien = "ejercicios/" + rutaImagen;
            ejercicio.setUbicacion(imagenbien);
        } else {
            ejercicio.setUbicacion(imagenOriginal);
        }

        servicioEjercicio.actualizarEjercicio(ejercicio);

        return new ModelAndView("redirect:/ver-admin");
    }


    @RequestMapping(value = "/guardarNuevoEjercicio", method = RequestMethod.POST)
    public ModelAndView guardarNuevoEjercicio(
            @RequestParam("nombre") String nombre,
            @RequestParam("categoria") String categoria,
            @RequestParam(value = "imagen", required = false) MultipartFile imagenNueva,
            HttpServletRequest request
    ) throws IOException {

        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        Ejercicio ejercicio = new Ejercicio();

        ejercicio.setNombre(nombre);
        ejercicio.setCategoria(categoria);


        if (imagenNueva != null && !imagenNueva.isEmpty()) {

            String rutaImagen = servicioImagen.guardarImagen(imagenNueva);
            String imagenbien = "ejercicios/" + rutaImagen;
            ejercicio.setUbicacion(imagenbien);
        } else {
            ejercicio.setUbicacion("");
        }

        servicioEjercicio.guardarEjercicio(ejercicio);

        return new ModelAndView("redirect:/ver-admin");
    }

    @RequestMapping(value = "/eliminarEjercicio", method = RequestMethod.POST)
    public ModelAndView eliminarEjercicio(@RequestParam("id") Long id) {

        Ejercicio ejercicio = servicioEjercicio.ontenerEjecicioPorId(id);

        if (ejercicio != null) {
            servicioEjercicio.eliminarEjercicio(id);
        }

        return new ModelAndView("redirect:/ver-admin");
    }


}
