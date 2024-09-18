package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.ServicioPerfilUsuarioImpl;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import com.tallerwebi.infraestructura.RepositorioUsuarioImpl;
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
    
    public void ControladorPerfilUsuario(ServicioPerfilUsuario servicioPerfilUsuario){
    this.servicioPerfilUsuario=servicioPerfilUsuario;
    }

    @RequestMapping(path = "/perfilusuario", method = RequestMethod.GET)
    public ModelAndView mostrarDatosDelUsuario() {
        ModelMap mavUsuario=new ModelMap();
        Usuario usuario=new Usuario();
        mavUsuario.put("nombre",usuario.getNombre());
        mavUsuario.put("apellido",usuario.getApellido());
        mavUsuario.put("objetivoSalud",usuario.getObjetivoSalud());
        mavUsuario.put("preferenciaAlimenticia",usuario.getPreferenciaAlimenticia());
        mavUsuario.put("restrincionesAlimentarias",usuario.getRestrincionesAlimentarias());
        mavUsuario.put("informacionAdicional",usuario.getInformacionAdicional());

        return new ModelAndView( "perfilusuario",mavUsuario);
    }


    @RequestMapping(path = "/mostrarFormulario", method = RequestMethod.GET)
    public ModelAndView mostrarFormulario() {

        ModelMap map=new ModelMap();
        map.put("usuario",new Usuario());
        return new ModelAndView("formulario",map);
    }








    @RequestMapping(path = "/enviarDatos", method = RequestMethod.POST)
    public ModelAndView enviarDatosDelFormulario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {

        ModelMap model = new ModelMap();
        model.put("nombre",usuario.getNombre());
        model.put("apellido",usuario.getApellido());
        model.put("objetivoSalud",usuario.getObjetivoSalud());
        model.put("preferenciaAlimenticia",usuario.getPreferenciaAlimenticia());
        model.put("restrincionesAlimentarias",usuario.getRestrincionesAlimentarias());
        model.put("informacionAdicional",usuario.getInformacionAdicional());

        return new ModelAndView("perfilusuario",model);

    }

   // @RequestMapping(path = "/calcularMacro", method = RequestMethod.GET)
    // public ModelAndView mostrarVistaCorrecta() {
     //  ModelAndView model=new ModelAndView("formulario");
      //   return model;
   // }
}
