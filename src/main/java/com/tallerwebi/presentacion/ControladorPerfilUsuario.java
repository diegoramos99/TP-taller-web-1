package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioPerfilUsuarioImpl;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import com.tallerwebi.infraestructura.RepositorioUsuarioImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ControladorPerfilUsuario {

    private ServicioPerfilUsuarioImpl servicioPerfilUsuario;
    public void ControladorPerfilUsuario(){

    }

    @RequestMapping(path = "/perfilusuario", method = RequestMethod.GET)
    public ModelAndView irAlPerfil() {
        return new ModelAndView("perfilusuario");
    }



    @RequestMapping(path = "/perfilusuario", method = RequestMethod.GET)
    public ModelMap mostrarDatosDelUsuario() {
        ModelMap mavUsuario=new ModelMap();
        Usuario usuario=new Usuario();
        mavUsuario.put("nombre",usuario.getNombre());
        mavUsuario.put("apellido",usuario.getApellido());
        mavUsuario.put("objetivoSalud",usuario.getObjetivoSalud());
        mavUsuario.put("preferenciaAlimenticia",usuario.getPreferenciaAlimenticia());
        mavUsuario.put("restrincionesAlimentarias",usuario.getRestrincionesAlimentarias());
        return mavUsuario;
    }


    @RequestMapping(path = "/formulario", method = RequestMethod.GET)
    public ModelAndView irAlFormulario() {
        return new ModelAndView("formulario");
    }



    @RequestMapping(path = "/formulario", method = RequestMethod.GET)
    public ModelAndView mostrarVista() {
        ModelAndView mav=new ModelAndView("formulario");
        return mav;
    }


    @RequestMapping(path = "/formulario", method = RequestMethod.GET)
    public ModelAndView IrAlPerfil() {
        ModelAndView model=new ModelAndView("perfilusuario");
        return model;
    }


    @RequestMapping(path = "/enviarDatos", method = RequestMethod.POST)
    public ModelAndView enviarDatosDelFormulario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {

        ModelMap model = new ModelMap();
        model.put("nombre",usuario.getNombre());
        model.put("apellido",usuario.getApellido());
        model.put("objetivoSalud",usuario.getObjetivoSalud());
        model.put("preferenciaAlimenticia",usuario.getInformacionAdicional());
        model.put("restrincionesAlimentarias",usuario.getRestrincionesAlimentarias());
        model.put("informacionAdicional",usuario.getInformacionAdicional());

        return new ModelAndView("perfilusuario",model);

    }
}
