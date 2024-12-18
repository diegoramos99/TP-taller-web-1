package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping("/login")
    public ModelAndView irALogin() {

        ModelMap modelo = new ModelMap();
        modelo.put("usuario", new Usuario());
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
    ModelMap model = new ModelMap();

    Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario.getEmail(), usuario.getPassword());

    if (usuarioBuscado != null) {

        request.getSession().setAttribute("EMAIL", usuarioBuscado.getEmail());
        request.getSession().setAttribute("NOMBRE", usuarioBuscado.getNombre());
        request.getSession().setAttribute("APELLIDO", usuarioBuscado.getApellido());

        if (usuarioBuscado.getRol().equals("USER")) {
            request.getSession().setAttribute("ROL", "USER");
            request.getSession().setAttribute("FOTOPERFIL", usuarioBuscado.getFotoPerfil());
            return new ModelAndView("redirect:/home");
        } else if (usuarioBuscado.getRol().equals("ADMIN")) {
            request.getSession().setAttribute("ROL", "ADMIN");
            request.getSession().setAttribute("FOTOPERFIL", usuarioBuscado.getFotoPerfil());
            return new ModelAndView("redirect:/ver-admin");
        }
    }

    // Si no se encuentra el usuario o el rol es incorrecto
    model.put("error", "Usuario o clave incorrecta");
    return new ModelAndView("login", model);
}


    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
        ModelMap model = new ModelMap();
        try {
            usuario.setRol("USER");
            servicioLogin.registrar(usuario);
        } catch (UsuarioExistente e) {
            model.put("error", "El usuario ya existe");
            return new ModelAndView("nuevo-usuario", model);
        } catch (Exception e) {
            model.put("error", "Error al registrar el nuevo usuario");
            return new ModelAndView("nuevo-usuario", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelMap model = new ModelMap();
        model.put("usuario", new Usuario());
        return new ModelAndView("nuevo-usuario", model);
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") == null) {
        return  new ModelAndView("redirect:/login");
        }
            return new ModelAndView("home");
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/login");
    }


    @RequestMapping(path = "/irAlHomeSinLoguearse", method = RequestMethod.GET)
    public ModelAndView irAlHomeSinLoguearse() {

            return new ModelAndView("redirect:/homeSinLoguearse");

    }
    @RequestMapping(path = "/homeSinLoguearse", method = RequestMethod.GET)
    public ModelAndView HomeSinLoguearse() {

        return new ModelAndView("homeSinLoguearse");

    }
    @RequestMapping(path = "/irAboutUs", method = RequestMethod.GET)
    public ModelAndView irAboutUs() {

        return new ModelAndView("redirect:/AboutUs");

    }
    @RequestMapping(path = "/AboutUs", method = RequestMethod.GET)
    public ModelAndView aboutUs() {

        return new ModelAndView("AboutUs");

    }
    @RequestMapping(path = "/irCatalogo", method = RequestMethod.GET)
    public ModelAndView irCatalogo() {

        return new ModelAndView("redirect:/catalogo");

    }
    @RequestMapping(path = "/catalogo", method = RequestMethod.GET)
    public ModelAndView catalogo() {

        return new ModelAndView("catalogo");

    }
}

