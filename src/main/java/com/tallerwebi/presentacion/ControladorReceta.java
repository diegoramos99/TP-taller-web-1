package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Receta;
import com.tallerwebi.dominio.ServicioImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tallerwebi.dominio.ServicioReceta;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ControladorReceta {

    private final ServicioReceta servicioReceta;
    private final ServicioImagen servicioImagen;

    @Autowired
    public ControladorReceta(ServicioReceta servicioReceta, ServicioImagen servicioImagen) {
        this.servicioImagen = servicioImagen;
        this.servicioReceta = servicioReceta;
    }

    @RequestMapping(path = "/recetas", method = RequestMethod.GET)
    public ModelAndView mostrarRecetas(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("recetas.html");
    }


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

    @RequestMapping(value = "/modificarReceta", method = RequestMethod.POST)
    public ModelAndView modificarEjercicio(@RequestParam("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();

        Receta receta = servicioReceta.obtenerRecetaPorId(id);

        model.addAttribute("recetaSeleccionada", receta);


        return new ModelAndView("modificarReceta", model);
    }

    @RequestMapping("/agregarReceta")
    public ModelAndView agregarReceta(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("agregarReceta");
    }


    @RequestMapping(value = "/guardarReceta", method = RequestMethod.POST)
    public ModelAndView guardarReceta(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("ingredientes") String ingredientes,
            @RequestParam("preparacion") String preparacion,
            @RequestParam("tiempo") Long tiempo,
            @RequestParam("calorias") Long calorias,
            @RequestParam(value = "imagenNueva", required = false) MultipartFile imagenNueva,
            @RequestParam("imagenOriginal") String imagenOriginal,
            HttpServletRequest request
    ) throws IOException {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        Receta receta = servicioReceta.obtenerRecetaPorId(id);
        receta.setNombre(nombre);
        receta.setIngredientes(ingredientes);
        receta.setPreparacion(preparacion);
        receta.setTiempo(tiempo);
        receta.setCalorias(calorias);

        if (imagenNueva != null && !imagenNueva.isEmpty()) {
            String rutaImagen = servicioImagen.guardarImagenReceta(imagenNueva);
            String imagenbien = "images/" + rutaImagen;
            receta.setImagen(imagenbien);
        } else {
            receta.setImagen(imagenOriginal);
        }

        servicioReceta.actualizarReceta(receta);


        return new ModelAndView("redirect:/ver-lista-recetas");
    }

    @RequestMapping(value = "/guardarNuevaReceta", method = RequestMethod.POST)
    public ModelAndView guardarNuevaReceta(
            @RequestParam("nombre") String nombre,
            @RequestParam("ingredientes") String ingredientes,
            @RequestParam("preparacion") String preparacion,
            @RequestParam("tiempo") Long tiempo,
            @RequestParam("calorias") Long calorias,
            @RequestParam(value = "imagen", required = false) MultipartFile imagenNueva,
            HttpServletRequest request

    ) throws IOException {

        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        Receta nuevaReceta = new Receta();

        nuevaReceta.setNombre(nombre);
        nuevaReceta.setIngredientes(ingredientes);
        nuevaReceta.setPreparacion(preparacion);
        nuevaReceta.setTiempo(tiempo);
        nuevaReceta.setCalorias(calorias);

        if (imagenNueva != null && !imagenNueva.isEmpty()) {
            String rutaImagen = servicioImagen.guardarImagenReceta(imagenNueva);
            String imagenbien = "images/" + rutaImagen;
            nuevaReceta.setImagen(imagenbien);
        } else {
            nuevaReceta.setImagen("");
        }

        servicioReceta.gurdarReceta(nuevaReceta);

        return new ModelAndView("redirect:/ver-lista-recetas");
    }

    @RequestMapping(value = "/eliminarReceta", method = RequestMethod.POST)
    public ModelAndView eliminarReceta(@RequestParam("id") Long id) {

        Receta receta = servicioReceta.obtenerRecetaPorId(id);

        if (receta != null) {
            servicioReceta.eliminarReceta(id);
        }

        return new ModelAndView("redirect:/ver-lista-recetas");
    }


}