package com.tallerwebi.presentacion;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.tallerwebi.dominio.ServicioPerfilUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MercadoPagoController {

    private ServicioPerfilUsuario servicioPerfilUsuario;


    @Autowired
    public MercadoPagoController(ServicioPerfilUsuario servicioPerfilUsuario) {
        com.mercadopago.MercadoPagoConfig.setAccessToken("APP_USR-470297481985646-110219-a9266f4edd8f0f73ccb7005eff521e71-2074774488");
        this.servicioPerfilUsuario = servicioPerfilUsuario;
    }

    public Preference createPreference() throws MPException, MPApiException {
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .title("Suscripción Premium FitCal")
                .description("Acceso premium a funciones avanzadas de salud y bienestar en la app FitCal")
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(new BigDecimal("1000"))
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:8080/spring/pago-exitoso")
                        .failure("http://localhost:8080/spring/pago-fallido")
                        .pending("http://localhost:8080/pago-pendiente")
                        .build())
                .autoReturn("approved")
                .build();

        PreferenceClient client = new PreferenceClient();
        return client.create(preferenceRequest);
    }

    @RequestMapping("/crear-preferencia")
    public ModelAndView crearPreferencia() throws MPException, MPApiException {
        Preference preference = createPreference();
        String preferenceId = preference.getId();

        ModelMap model = new ModelMap();
        model.addAttribute("preferenceId", preferenceId);

        return new ModelAndView("perfilusuario", model);
    }

    @GetMapping("/pago-exitoso")
    public ModelAndView pagoExitoso(@RequestParam("collection_id") String collectionId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();


        Usuario usuarioBuscado = servicioPerfilUsuario.buscarUsuarioPoreEmail((String) request.getSession().getAttribute("EMAIL"));
        servicioPerfilUsuario.actualizarEstadoPremium(true, usuarioBuscado.getId());

        modelAndView.addObject("mensaje", "Pago realizado con éxito.");
        modelAndView.setViewName("home");

        return modelAndView;
    }

    @GetMapping("/pago-fallido")
    public ModelAndView pagoFallido() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mensaje", "Hubo un problema con el pago. Por favor, intenta de nuevo.");
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @GetMapping("/pago-pendiente")
    public ModelAndView pagoPendiente() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


}
