package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.Macronutrientes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicioCalculoNutricionalTest {

    private final ServicioCalculoNutricionalimpl servicio = new ServicioCalculoNutricionalimpl();

    @Test
    void calcularCaloriasDiariasParaHombreActivo() {
        // Dado
        Usuario usuario = new Usuario();
        usuario.setSexo("masculino");
        usuario.setPeso(75.0f);
        usuario.setAltura(1.8f);
        usuario.setEdad(30);
        usuario.setActividad("activo");
        usuario.setObjetivoSalud("mantener");

        Double calorias = servicio.calcularCaloriasDiarias(usuario);

        assertNotNull(calorias);
        assertEquals(3081.471, calorias, 0.1, "Las calorías diarias calculadas deben coincidir");
    }

    @Test
    void calcularCaloriasDiariasParaMujerSedentariaConObjetivoDePerderPeso() {
        Usuario usuario = new Usuario();
        usuario.setSexo("femenino");
        usuario.setPeso(60.0f);
        usuario.setAltura(1.65f);
        usuario.setEdad(25);
        usuario.setActividad("sedentario");
        usuario.setObjetivoSalud("perder");

        Double calorias = servicio.calcularCaloriasDiarias(usuario);

        assertNotNull(calorias);
        assertEquals(1431.7, calorias, 0.1, "Las calorías diarias calculadas deben coincidir");
    }


    @Test
    void calcularMacronutrientesDebeDividirCorrectamenteLasCalorías() {
        Double calorias = 2000.0;

        Macronutrientes macros = servicio.calcularMacronutrientes(calorias);

        assertNotNull(macros);
        assertEquals(250.0, macros.getCarbohidratos(), 0.1, "Los carbohidratos calculados deben coincidir");
        assertEquals(150.0, macros.getProteinas(), 0.1, "Las proteínas calculadas deben coincidir");
        assertEquals(44.44, macros.getGrasas(), 0.1, "Las grasas calculadas deben coincidir");
    }

    @Test
    void calcularIMCHombreDebeClasificarCorrectamente() {

        String genero = "hombre";
        double altura = 1.80;
        double peso = 75.0;
        Integer edad = 30;

        DatosIMC datosIMC = servicio.calcularIMC(genero, altura, peso, edad);

        assertNotNull(datosIMC);
        assertEquals(23.15, datosIMC.getImc(), 0.01, "El IMC calculado debe coincidir");
        assertEquals("Normal", datosIMC.getClasificacion(), "La clasificación del IMC debe ser correcta");
    }

    @Test
    void calcularIMCMujerDebeClasificarCorrectamente() {
        // Dado
        String genero = "mujer";
        double altura = 1.65;
        double peso = 60.0;
        Integer edad = 25;

        DatosIMC datosIMC = servicio.calcularIMC(genero, altura, peso, edad);

        assertNotNull(datosIMC);
        assertEquals(22.04, datosIMC.getImc(), 0.01, "El IMC calculado debe coincidir");
        assertEquals("Normal", datosIMC.getClasificacion(), "La clasificación del IMC debe ser correcta");
    }

    @Test
    void obtenerMultiplicadorActividadInvalidoDebeRetornarPorDefecto() {
        // Dado
        Usuario usuario = new Usuario();
        usuario.setActividad("actividad-invalida");

        Double resultado = servicio.obtenerMultiplicadorActividad("actividad-invalida");

        assertEquals(1.0, resultado, "El multiplicador para actividades no válidas debe ser 1.0");
    }
}
