package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ControladorRegistroRutina {


    private final ServicioLogin servicioLogin;
    private ServicioEjercicio servicioEjercicio;

    @Autowired
    public ControladorRegistroRutina(ServicioLogin servicioLogin, ServicioEjercicio ServicioEjercicio) {
        this.servicioLogin = servicioLogin;
        this.servicioEjercicio = ServicioEjercicio;
    }


    @RequestMapping("/ver-Rutina")
    public ModelAndView irALogin(HttpServletRequest request,
                                 @RequestParam(value = "dia", required = false, defaultValue = "Lunes") String dia) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();
        List<RegistroEjercicio> registroEjercicio = servicioEjercicio.traerTodosLosEjerciciosPorDia(dia);

        model.put("registroEjercicios", registroEjercicio);
        model.put("dia", dia);

        return new ModelAndView("registrarRutina", model);
    }

    @RequestMapping("/addEjercicio")
    public ModelAndView registrarRutina(HttpServletRequest request, @ModelAttribute("datosEjercicio") DatosEjercicio datos) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }

        Usuario usuario = servicioLogin.consultarUsuarioPorEmail((String) request.getSession().getAttribute("EMAIL"));


        Ejercicio ejercicio = servicioEjercicio.ontenerEjecicioPorId(datos.getIdEjercicio());

        RegistroEjercicio registroEjercicio = new RegistroEjercicio();

        registroEjercicio.setEjercicio(ejercicio);
        registroEjercicio.setSeries(datos.getSeries());
        registroEjercicio.setDescanso(datos.getDescanso());
        registroEjercicio.setRepeticiones(datos.getRepeticiones());
        registroEjercicio.setUsuario(usuario);
        registroEjercicio.setDia(datos.getDia());


        servicioEjercicio.registrarRutina(registroEjercicio);

        return new ModelAndView("redirect:/ver-Rutina?dia=" + datos.getDia());
    }

    @RequestMapping("/eliminar-RegistroEjercicio")
    public ModelAndView eliminarEjercicio(@RequestParam Long id, @RequestParam String dia) {
        servicioEjercicio.eliminarRegistroEjercicio(id);
        return new ModelAndView("redirect:/ver-Rutina?dia=" + dia);
    }


}


