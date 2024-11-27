package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioCalculoNutricional;
import com.tallerwebi.dominio.ServicioImagen;
import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
public class ControladorPerfilUsuario {

    private ServicioPerfilUsuario servicioPerfilUsuario;
    private ServicioImagen servicioImagen;
    private ServicioCalculoNutricional servicioCalculoNutricional;

    @Autowired

    public void ControladorPerfilUsuario(ServicioPerfilUsuario servicioPerfilUsuario, ServicioImagen servicioImagen,ServicioCalculoNutricional servicioCalculoNutricional) {
        this.servicioPerfilUsuario = servicioPerfilUsuario;
        this.servicioImagen = servicioImagen;
        this.servicioCalculoNutricional=servicioCalculoNutricional;
    }

    @RequestMapping(path = "/perfilusuario", method = RequestMethod.GET)
    public ModelAndView mostrarDatosDelUsuario(HttpServletRequest request) {

        String email = (String) request.getSession().getAttribute("EMAIL");

        if (email == null) {
            return new ModelAndView("redirect:/login");
        }
        Usuario usuarioBuscado = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);
        double imcLargo=servicioCalculoNutricional.calcularIMC(usuarioBuscado.getSexo(), usuarioBuscado.getAltura(), usuarioBuscado.getPeso(),usuarioBuscado.getEdad()).getImc();
        double redondeado = Math.round(imcLargo * 100.0) / 100.0;

        String clasificacion=servicioCalculoNutricional.calcularIMC(usuarioBuscado.getSexo(), usuarioBuscado.getAltura(), usuarioBuscado.getPeso(),usuarioBuscado.getEdad()).getClasificacion();

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
        mavUsuario.put("fotoPerfil", usuarioBuscado.getFotoPerfil());
        mavUsuario.put("IMC", redondeado);
        mavUsuario.put("clasificacion", clasificacion);


        return new ModelAndView("perfilusuario", mavUsuario);
    }


    @RequestMapping(path = "/mostrarFormulario", method = RequestMethod.GET)
    public ModelAndView mostrarFormulario(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("EMAIL");

        if (email == null) {
            return new ModelAndView("redirect:/login");
        }
        Usuario usuarioBuscado = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);

        ModelMap map = new ModelMap();

        map.put("usuario", usuarioBuscado);

        return new ModelAndView("formulario", map);
    }

    @RequestMapping(path = "/enviarDatos", method = RequestMethod.POST)
    public ModelAndView enviarDatosDelFormulario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {

        String email = (String) request.getSession().getAttribute("EMAIL");
        Usuario usuarioActivo = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);

        Usuario usuarioActualizado = servicioPerfilUsuario.modificarUsuario(usuarioActivo, usuario);

        double imcLargo=servicioCalculoNutricional.calcularIMC(usuarioActivo.getSexo(), usuarioActivo.getAltura(), usuarioActivo.getPeso(),usuarioActivo.getEdad()).getImc();
        double redondeado = Math.round(imcLargo * 100.0) / 100.0;
        String clasificacion=servicioCalculoNutricional.calcularIMC(usuarioActivo.getSexo(), usuarioActivo.getAltura(), usuarioActivo.getPeso(),usuarioActivo.getEdad()).getClasificacion();
        ModelMap model = new ModelMap();
        model.put("nombre", usuarioActualizado.getNombre());
        model.put("apellido", usuarioActualizado.getApellido());
        model.put("objetivoSalud", usuarioActualizado.getObjetivoSalud());
        model.put("preferenciaAlimenticia", usuarioActualizado.getPreferenciaAlimenticia());
        model.put("restrincionesAlimentarias", usuarioActualizado.getRestrincionesAlimentarias());
        model.put("informacionAdicional", usuarioActualizado.getInformacionAdicional());
        model.put("fotoPerfil",usuarioActualizado.getFotoPerfil());
        model.put("IMC",redondeado);
        model.put("clasificacion",clasificacion);


        return new ModelAndView("perfilusuario", model);

    }

    @RequestMapping(path = "/calcularMacro", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCorrecta(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("EMAIL");

        if (email == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView model = new ModelAndView("calculadoraIMC");

        return model;
    }

    @PostMapping("/cambiarFotoPerfil")
    public ModelAndView cambiarFotoPerfil(@RequestParam("foto") MultipartFile foto, HttpServletRequest request, ModelMap modelMap) throws IOException {

          ModelMap model = new ModelMap();
          String email = (String) request.getSession().getAttribute("EMAIL");
          Usuario usuarioActivo = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);

         if (foto != null && !foto.isEmpty()) {
            String rutaImagen = servicioImagen.guardarImagenReceta(foto);
            String imagenbien = "images/" + rutaImagen;
            usuarioActivo.setFotoPerfil(imagenbien);
            servicioPerfilUsuario.actualizarUsuario(usuarioActivo);
            model.put("success", "foto subida exitosamente");
            return new ModelAndView("redirect:/perfilusuario",model);
        } else {
             model.put("error", "No se pudo subir la imagen");
            return new ModelAndView("redirect:/perfilusuario",model);
        }



    }


}
