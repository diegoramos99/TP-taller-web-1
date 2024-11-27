package com.tallerwebi.dominio;


import com.tallerwebi.presentacion.Macronutrientes;

public interface ServicioCalculoNutricional {

    Double calcularCaloriasDiarias(Usuario usuario);
     public Macronutrientes calcularMacronutrientes(Double calorias);

    DatosIMC calcularIMC(String genero,double altura, double peso, Integer edad);
}
