package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ControladorPerfilUsuario {

    private ServicioPerfilUsuario servicioPerfilUsuario;

    @Autowired

    public void ControladorPerfilUsuario(ServicioPerfilUsuario servicioPerfilUsuario) {
        this.servicioPerfilUsuario = servicioPerfilUsuario;
    }

    @RequestMapping(path = "/perfilusuario", method = RequestMethod.GET)
    public ModelAndView mostrarDatosDelUsuario(HttpServletRequest request) {

        String email = (String) request.getSession().getAttribute("EMAIL");

        if (email == null) {
            return new ModelAndView("redirect:/login");
        }
        Usuario usuarioBuscado = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);

        ModelMap mavUsuario = new ModelMap();




        if (usuarioBuscado != null && usuarioBuscado.getEsPremium() != null && usuarioBuscado.getEsPremium()) {
            mavUsuario.put("esPremium", true);
        } else {
            mavUsuario.put("esPremium", false);
        }



        mavUsuario.put("nombre", usuarioBuscado.getNombre());
        mavUsuario.put("apellido", usuarioBuscado.getApellido());
        mavUsuario.put("objetivoSalud", usuarioBuscado.getObjetivoSalud());
        mavUsuario.put("preferenciaAlimenticia", usuarioBuscado.getPreferenciaAlimenticia());
        mavUsuario.put("restrincionesAlimentarias", usuarioBuscado.getRestrincionesAlimentarias());
        mavUsuario.put("informacionAdicional", usuarioBuscado.getInformacionAdicional());

        return new ModelAndView("perfilusuario", mavUsuario);
    }


    @RequestMapping(path = "/mostrarFormulario", method = RequestMethod.GET)
    public ModelAndView mostrarFormulario() {

        ModelMap map = new ModelMap();
        map.put("usuario", new Usuario());
        return new ModelAndView("formulario", map);
    }

    @RequestMapping(path = "/enviarDatos", method = RequestMethod.POST)
    public ModelAndView enviarDatosDelFormulario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {

        String email = (String) request.getSession().getAttribute("EMAIL");
        Usuario usuarioActivo = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);

        Usuario usuarioActualizado = servicioPerfilUsuario.modificarUsuario(usuarioActivo, usuario);
        ModelMap model = new ModelMap();
        model.put("nombre", usuarioActualizado.getNombre());
        model.put("apellido", usuarioActualizado.getApellido());
        model.put("objetivoSalud", usuarioActualizado.getObjetivoSalud());
        model.put("preferenciaAlimenticia", usuarioActualizado.getPreferenciaAlimenticia());
        model.put("restrincionesAlimentarias", usuarioActualizado.getRestrincionesAlimentarias());
        model.put("informacionAdicional", usuarioActualizado.getInformacionAdicional());

        return new ModelAndView("perfilusuario", model);

    }

    @RequestMapping(path = "/calcularMacro", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCorrecta() {
        ModelAndView model = new ModelAndView("calculadoraIMC");
        return model;
    }
}
