package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorAdmin {

    private ServicioEjercicio servicioEjercicio;
    private ServicioAlimento servicioAlimento;
    private ServicioReceta servicioReceta;


    @Autowired
    public ControladorAdmin(ServicioEjercicio ServicioEjercicio, ServicioAlimento ServicioAlimento, ServicioReceta ServicioReceta) {
        this.servicioEjercicio = ServicioEjercicio;
        this.servicioAlimento = ServicioAlimento;
        this.servicioReceta = ServicioReceta;

    }


    @RequestMapping("/ver-admin")
    public ModelAndView verBuscarEjercicio(HttpServletRequest request, @RequestParam(value = "search", required = false) String search) {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        List<Ejercicio> ejercicios;

        if (search != null && !search.trim().isEmpty()) {
            ejercicios = servicioEjercicio.buscarEjercicioPorNombreYCategoria(search);
        } else {
            ejercicios = servicioEjercicio.getEjercicios();
        }

        model.addAttribute("ejercicios", ejercicios);

        return new ModelAndView("admin", model);
    }

    @RequestMapping("/ver-lista-recetas")
    public ModelAndView verBuscarReceta(HttpServletRequest request, @RequestParam(value = "search", required = false) String search) {
        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        List<Receta> recetas;

        if (search != null && !search.trim().isEmpty()) {
            recetas = servicioReceta.BuscarRecetaPorNombreYingrediente(search);
        } else {
            recetas = servicioReceta.getRecetas();
        }

        model.addAttribute("recetas", recetas);

        return new ModelAndView("adminListaReseta", model);
    }

    @RequestMapping("/ver-lista-comidas")
    public ModelAndView verListaAlimento(HttpServletRequest request, @RequestParam(value = "search", required = false) String search) {

        if (request.getSession().getAttribute("EMAIL") == null && request.getSession().getAttribute("ROL") != "ADMIN") {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        List<Alimento> alimentos;

        if (search != null && !search.trim().isEmpty()) {
            alimentos = servicioAlimento.BuscarAlimentoPorNombreYCategoria(search);
        } else {
            alimentos = servicioAlimento.getAlimentos();
        }

        model.addAttribute("alimentos", alimentos);

        return new ModelAndView("adminListaAlimento", model);
    }


}
