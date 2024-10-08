package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.Alimento;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("controladorResgistroAlimento")
public class ControladorRegistrarAlimento {


    private final ServicioAlimento servicioAlimento;
    private final ServicioLogin servicioLogin;
    private final ServicioCalculoNutricional servicioCalculoNutricional;

    @Autowired
    public ControladorRegistrarAlimento(ServicioAlimento servicioAlimento, ServicioLogin servicioLogin, ServicioCalculoNutricional servicioCalculoNutricional) {
        this.servicioAlimento = servicioAlimento;
        this.servicioLogin = servicioLogin;
        this.servicioCalculoNutricional = servicioCalculoNutricional;
    }


    @RequestMapping("/ver-Registrar-alimentos")
    public ModelAndView verRegistrarAlimentos(@RequestParam(value = "fecha", required = false) String fechaStr, HttpServletRequest request) {

        ModelMap model = new ModelMap();
        List<RegistroComida> alimentos = servicioAlimento.obtenerRegistrosPorFecha(fechaStr);

        Double totalCalorias = 0.0;
        for (RegistroComida reg : alimentos) {
            totalCalorias += reg.getAlimento().getCalorias();
        }

        Double totalCarbohidratos = 0.0;
        for (RegistroComida reg : alimentos) {
            totalCarbohidratos += reg.getAlimento().getCarbohidratos();
        }

        Double totalGrasas = 0.0;
        for (RegistroComida reg : alimentos) {
            totalGrasas += reg.getAlimento().getGrasas();
        }

        Double totalProteinas = 0.0;
        for (RegistroComida reg : alimentos) {
            totalProteinas += reg.getAlimento().getProteinas();
        }


        String nombreUsuario = (String) request.getSession().getAttribute("EMAIL");
        Usuario usuario = servicioLogin.consultarUsuarioPorEmail(nombreUsuario);
        if (usuario == null) {
            model.addAttribute("mensaje", "Usuario no encontrado.");
            return new ModelAndView("registrarAlimento", model);
        }

        Double totalCaloriasDiarias = servicioCalculoNutricional.calcularCaloriasDiarias(usuario);
        Macronutrientes macros = servicioCalculoNutricional.calcularMacronutrientes(totalCaloriasDiarias);

        model.addAttribute("totalCaloriasDiarias", Math.round(totalCaloriasDiarias));
        model.addAttribute("totalGrasasDiarias", Math.round(macros.getGrasas()));
        model.addAttribute("totalProteinasDiarias", Math.round(macros.getProteinas()));
        model.addAttribute("totalCarbohidratosDiarios", Math.round(macros.getCarbohidratos()));

        model.addAttribute("totalCaloriasRestantes", Math.round(totalCaloriasDiarias - totalCalorias));
        model.addAttribute("totalCarbohidratosRestantes", Math.round(macros.getCarbohidratos() - totalCarbohidratos));
        model.addAttribute("totalGrasasRestantes", Math.round(macros.getGrasas() - totalGrasas));
        model.addAttribute("totalProteinasRestantes", Math.round(macros.getProteinas() - totalProteinas));

        model.addAttribute("totalCalorias", Math.round(totalCalorias));
        model.addAttribute("totalProteinas", Math.round(totalProteinas));
        model.addAttribute("totalGrasas", Math.round(totalGrasas));
        model.addAttribute("totalCarbohidratos", Math.round(totalCarbohidratos));


        model.addAttribute("alimentos", alimentos);
        return new ModelAndView("registrarAlimento", model);
    }


    @RequestMapping("/registrarAlimentoSelecionado/{id}")
    public ModelAndView alimentoSeleccionado(
            @PathVariable("id") Long id,
            @RequestParam("comida") String tipoDeComida,
            @RequestParam("fecha") String fechaStr,
            HttpServletRequest request) {

        ModelMap model = new ModelMap();

        if (tipoDeComida == null || tipoDeComida.isEmpty()) {
            model.addAttribute("mensaje", "El tipo de comida es requerido.");
            return new ModelAndView("registrarAlimento", model);
        }

        if (fechaStr == null || fechaStr.isEmpty()) {
            model.addAttribute("mensaje", "La fecha es requerida.");
            return new ModelAndView("registrarAlimento", model);
        }

        Alimento alimentoSeleccionado = servicioAlimento.obtenerAlimentoPorId(id);

        String nombreUsuario = (String) request.getSession().getAttribute("EMAIL");
        Usuario usuario = servicioLogin.consultarUsuarioPorEmail(nombreUsuario);
        if (usuario == null) {
            model.addAttribute("mensaje", "Usuario no encontrado.");
            return new ModelAndView("registrarAlimento", model);
        }


        RegistroComida registroComida = new RegistroComida();
        registroComida.setFecha(fechaStr);
        registroComida.setTipoComida(tipoDeComida);
        registroComida.setAlimento(alimentoSeleccionado);
        registroComida.setUsuario(usuario);


        servicioAlimento.guardarRegistroAlimento(registroComida);

        return new ModelAndView("redirect:/ver-Registrar-alimentos?fecha=" + fechaStr);
    }


}
