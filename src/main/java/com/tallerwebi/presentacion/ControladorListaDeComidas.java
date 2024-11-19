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
import java.util.Arrays;
import java.util.List;

@Controller
public class ControladorListaDeComidas {

    private ServicioListaDeComidas servicioListaDeComidas;
    private ServicioPerfilUsuario servicioPerfilUsuario;

    @Autowired
    public ControladorListaDeComidas(ServicioListaDeComidas servicioListaDeComidas, ServicioPerfilUsuario servicioPerfilUsuario) {
        this.servicioListaDeComidas = servicioListaDeComidas;
        this.servicioPerfilUsuario = servicioPerfilUsuario;
    }

    @RequestMapping("/listaComidas")
    public ModelAndView irAListaDeComidas(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("redirect:/listaDeComidas");
    }

    @RequestMapping("/listaDeComidas")
    public ModelAndView mostrarAlimentosDisponibles(HttpServletRequest request) {
         if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
        String email = (String) request.getSession().getAttribute("EMAIL");
        Usuario usuarioBuscado = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);
        List<List<Alimento>> alimento = servicioListaDeComidas.buscarAlimentosParaLaSemana(usuarioBuscado);
        ModelMap map = new ModelMap();
        map.put("alimentos", alimento);
        return new ModelAndView("listaDeComidas", map);
    }

    @RequestMapping("/verReceta")
    public ModelAndView mostrarReceta(HttpServletRequest request,@RequestParam("receta") String receta) {
        if (request.getSession().getAttribute("EMAIL") == null) {
            return new ModelAndView("redirect:/login");
        }
       // String email = (String) request.getSession().getAttribute("EMAIL");
       // Usuario usuarioBuscado = servicioPerfilUsuario.buscarUsuarioPoreEmail(email);

        List<Receta> receta1=servicioListaDeComidas.buscarReceta(receta);
        String nombre=receta1.get(0).getNombre();
        String preparacion=receta1.get(0).getPreparacion();
        Long tiempo=receta1.get(0).getTiempo();
        String image=receta1.get(0).getImagen();
        Long calorias=receta1.get(0).getCalorias();
        String ingredientes=receta1.get(0).getIngredientes();
        List listaIngredientes= Arrays.asList(ingredientes.split("\\s*,\\s*"));
        ModelMap map = new ModelMap();
        map.put("image",image);
        map.put("nombre", nombre);
        map.put("preparacion", preparacion);
        map.put("tiempo", tiempo);
        map.put("calorias", calorias);
        map.put("listaIngredientes", listaIngredientes);
        return new ModelAndView("receta", map);
    }

}
