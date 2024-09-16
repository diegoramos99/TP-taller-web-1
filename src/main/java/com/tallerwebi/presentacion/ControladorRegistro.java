package com.tallerwebi.presentacion;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControladorRegistro {


    public ModelAndView registrar(String email,String contracena,String validarContracena) {
        if (email.isEmpty() || contracena!=validarContracena){

        ModelMap mav=new ModelMap();
        mav.put("error","el email es obligatorio");
        mav.put("contraceñaDistinta","las contraceñas deben ser iguales");
        return new ModelAndView("redirect:/registro",mav);
        }
        return new ModelAndView("redirect:/login");
    }
}
