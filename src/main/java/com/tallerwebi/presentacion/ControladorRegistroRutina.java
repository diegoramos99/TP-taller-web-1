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
import java.util.ArrayList;
import java.util.List;


@Controller
public class ControladorRegistroRutina {


    private final ServicioLogin servicioLogin;
    private ServicioEjercicio servicioEjercicio;
    private ServicioPerfilUsuario servicioPerfilUsuario;

    @Autowired
    public ControladorRegistroRutina(ServicioLogin servicioLogin, ServicioEjercicio ServicioEjercicio, ServicioPerfilUsuario servicioPerfilUsuario) {
        this.servicioPerfilUsuario = servicioPerfilUsuario;
        this.servicioLogin = servicioLogin;
        this.servicioEjercicio = ServicioEjercicio;
    }

    @RequestMapping("/ver-Rutina")
    public ModelAndView irALogin(HttpServletRequest request,
                                 @RequestParam(value = "dia", required = false, defaultValue = "Lunes") String dia,
                                 @RequestParam(value = "rutinaSelecionada", required = false, defaultValue = "Rutina Personalizada") String rutinaSelecionada) {

        Usuario usuarioBuscado = servicioLogin.consultarUsuarioPorEmail((String) request.getSession().getAttribute("EMAIL"));

        if (usuarioBuscado == null) {
            return new ModelAndView("redirect:/login");
        }

        Usuario usuario = servicioLogin.consultarUsuarioPorEmail((String) request.getSession().getAttribute("EMAIL"));
        ModelMap model = new ModelMap();

        if (rutinaSelecionada == null || rutinaSelecionada.isEmpty()) {
            rutinaSelecionada = "Rutina Personalizada";
        }

        List<RegistroEjercicio> registroEjercicio = servicioEjercicio.traerRutina(rutinaSelecionada, usuario);
        List<RegistroEjercicio> registroEjercicio2 = new ArrayList<>();

        List<String> diasOcupados = new ArrayList<>();


        for (RegistroEjercicio registroEjercicioBuscado : registroEjercicio) {
            String diaocupado = registroEjercicioBuscado.getDia();

            if (!diasOcupados.contains(diaocupado)) {
                diasOcupados.add(diaocupado);
            }
        }


        for (RegistroEjercicio registroEjercicioBuscado : registroEjercicio) {
            if (registroEjercicioBuscado.getDia().equals(dia)) {
                registroEjercicio2.add(registroEjercicioBuscado);
            }
        }

        if (usuarioBuscado != null && usuarioBuscado.getEsPremium() != null && usuarioBuscado.getEsPremium()) {
            model.put("esPremium", true);
        } else {
            model.put("esPremium", false);
        }


        model.put("registroEjercicios", registroEjercicio2);
        model.put("dia", dia);
        model.put("rutinaSelecionada", rutinaSelecionada);
        model.put("diasOcupados", diasOcupados);

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
        registroEjercicio.setNombreRutina("Rutina Personalizada");


        servicioEjercicio.registrarRutina(registroEjercicio);

        return new ModelAndView("redirect:/ver-Rutina?dia=" + datos.getDia());
    }


    @RequestMapping("/selecionar-rutina")
    public ModelAndView mostrarRutinas(@RequestParam String rutinaSelecionada, @RequestParam String dia, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("EMAIL");

        if (email == null) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("redirect:/ver-Rutina?rutinaSelecionada=" + rutinaSelecionada + "&dia=" + dia);
    }

    @RequestMapping("/eliminar-RegistroEjercicio")
    public ModelAndView eliminarEjercicio(@RequestParam Long id, @RequestParam String dia) {
        servicioEjercicio.eliminarRegistroEjercicio(id);
        return new ModelAndView("redirect:/ver-Rutina?dia=" + dia);
    }


}


