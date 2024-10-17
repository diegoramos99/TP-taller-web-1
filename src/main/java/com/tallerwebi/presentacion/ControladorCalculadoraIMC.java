package com.tallerwebi.presentacion;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorCalculadoraIMC {
    @RequestMapping(path = "/calculadoraIMC", method = RequestMethod.GET)
    public ModelAndView mostrarCalculadora(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("calculadoraIMC.html");
    }

    @RequestMapping(path = "/calcularIMC")
    public ModelAndView calcularIMC(@RequestParam String genero, @RequestParam double altura, @RequestParam double peso, @RequestParam Integer edad,HttpServletRequest request) {


         if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
        //paso altura a metros
        altura = altura / 100;

        //calculo el IMC
        double imc = peso / (altura * altura);

        //Clasifico en hombre o mujer
        String clasificacion;

        if (genero.equalsIgnoreCase("hombre")) {
            // clasificacion especifica para hombres
            if (imc < 20) {
                clasificacion = "Bajo peso";
            } else if (imc < 25) {
                clasificacion = "Normal";
            } else if (imc < 30) {
                clasificacion = "Sobrepeso";
            } else {
                clasificacion = "Obesidad";
            }
        } else {
            // clasificacion especifica para mujeres
            if (imc < 19) {
                clasificacion = "Bajo peso";
            } else if (imc < 24) {
                clasificacion = "Normal";
            } else if (imc < 29) {
                clasificacion = "Sobrepeso";
            } else {
                clasificacion = "Obesidad";
            }
        }


        //agrego resultados al modelo
        ModelMap modelo = new ModelMap();

        modelo.put("clasificacion", clasificacion);
        modelo.put("genero", genero);
        modelo.put("altura", altura);
        modelo.put("peso", peso);
        modelo.put("edad", edad);
        modelo.put("imc", imc);


        //redirigo a la pagina
        return new ModelAndView("resultadoIMC", modelo);
    }
}

