package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Ejercicio;
import com.tallerwebi.dominio.ServicioEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class ControladorEjercicio {

    private ServicioEjercicio ServicioEjercicio;

    @Autowired
    public ControladorEjercicio(ServicioEjercicio ServicioEjercicio) {
        this.ServicioEjercicio = ServicioEjercicio;
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
            List<Ejercicio> resultados = ServicioEjercicio.buscarEjercicioPorNombreYCategoria(termino);
            model.addAttribute("ejercicios", resultados);
        } else {
            model.addAttribute("ejercicios", Collections.emptyList());
        }

        model.addAttribute("datosEjercicio", new DatosEjercicio());
        return new ModelAndView("buscarEjercicio", model);
    }


}
