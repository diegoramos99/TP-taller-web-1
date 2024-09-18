package com.tallerwebi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCalculadoraIMC {
    @RequestMapping(path = "/calculadoraIMC", method = RequestMethod.GET)

    public ModelAndView mostrarCalculadora() { return new ModelAndView("calculadoraIMC.html");}

}