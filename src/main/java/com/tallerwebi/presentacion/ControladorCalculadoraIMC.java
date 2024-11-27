package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorCalculadoraIMC {

    ServicioCalculoNutricional ServicioCalculoNutricional;
    ServicioPerfilUsuario servicioPerfilUsuario;
    @Autowired
    public ControladorCalculadoraIMC(ServicioCalculoNutricional servicioCalculoNutricional,ServicioPerfilUsuario servicioPerfilUsuario) {
        this.ServicioCalculoNutricional = servicioCalculoNutricional;
        this.servicioPerfilUsuario=servicioPerfilUsuario;

    }

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
         float alturas =(float)altura;
         Float valorAltura=alturas;
         float pesos=(float)peso;
         Float valorpeso=pesos;
         DatosIMC datosIMC=ServicioCalculoNutricional.calcularIMC(genero,altura, peso, edad);
        double redondeado = Math.round(datosIMC.getImc() * 100.0) / 100.0;

        String email =(String) request.getSession().getAttribute("EMAIL");
        Usuario usuarioBuscado=servicioPerfilUsuario.buscarUsuarioPoreEmail(email);
        usuarioBuscado.setSexo(genero);
        usuarioBuscado.setAltura(valorAltura);
        usuarioBuscado.setPeso(valorpeso);
        usuarioBuscado.setEdad(edad);

        servicioPerfilUsuario.actualizarUsuario(usuarioBuscado);
    //agrego resultados al modelo
        ModelMap modelo = new ModelMap();

        modelo.put("clasificacion", datosIMC.getClasificacion());
        modelo.put("genero", datosIMC.getGenero());
        modelo.put("altura", datosIMC.getAltura());
        modelo.put("peso", datosIMC.getPeso());
        modelo.put("edad", datosIMC.getEdad());
        modelo.put("imc", redondeado);


        //redirigo a la pagina
        return new ModelAndView("resultadoIMC", modelo);
    }

}

