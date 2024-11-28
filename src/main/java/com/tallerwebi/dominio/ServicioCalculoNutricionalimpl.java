package com.tallerwebi.dominio;


import com.tallerwebi.presentacion.Macronutrientes;
import org.springframework.stereotype.Service;


@Service("servicioCalculoNutricional")
public class ServicioCalculoNutricionalimpl implements ServicioCalculoNutricional {
    private Double calcularTasaMetabolicaBasal(Usuario usuario) {
        double alturaEnCm = usuario.getAltura() > 3 ? usuario.getAltura() : usuario.getAltura() * 100;
        if (usuario.getSexo().equalsIgnoreCase("masculino")) {
            return 88.36 + (13.4 * usuario.getPeso()) + (4.8 * alturaEnCm) - (5.7 * usuario.getEdad());
        } else {
            return 447.6 + (9.2 * usuario.getPeso()) + (3.1 * alturaEnCm) - (4.3 * usuario.getEdad());
        }
    }


    public Double obtenerMultiplicadorActividad(String actividad) {
        switch (actividad.toLowerCase()) {
            case "sedentario":
                return 1.2;
            case "ligero":
                return 1.375;
            case "moderado":
                return 1.55;
            case "activo":
                return 1.725;
            case "muy-activo":
                return 1.9;
            case "extremadamente-activo":
                return 2.0;
            default:
                return 1.0;
        }
    }

    private Double ajustarPorObjetivoSalud(Double calorias, String objetivoSalud) {
        switch (objetivoSalud.toLowerCase()) {
            case "mantener":
                return calorias;
            case "perder":
                return calorias * 0.85;
            case "agresivo":
                return calorias * 0.7;
            case "ganar":
                return calorias * 1.15;
            case "ganar-agresivo":
                return calorias * 1.3;
            default:
                return calorias;
        }
    }

    @Override
    public Double calcularCaloriasDiarias(Usuario usuario) {
        if (usuario.getPeso() <= 0 || usuario.getAltura() <= 0 || usuario.getEdad() <= 0) {
            throw new IllegalArgumentException("Datos del usuario inválidos para el cálculo.");
        }

        Double caloriasBase = calcularTasaMetabolicaBasal(usuario);
        Double multiplicadorActividad = obtenerMultiplicadorActividad(usuario.getActividad());
        Double caloriasTotales = caloriasBase * multiplicadorActividad;
        caloriasTotales = ajustarPorObjetivoSalud(caloriasTotales, usuario.getObjetivoSalud());

        if (caloriasTotales < 1000 || caloriasTotales > 5000) {
            throw new IllegalArgumentException("El cálculo resultó en calorías fuera del rango lógico.");
        }

        return caloriasTotales;
    }


    @Override
    public Macronutrientes calcularMacronutrientes(Double calorias) {
        if (calorias <= 0) {
            throw new IllegalArgumentException("Calorías inválidas para calcular macronutrientes.");
        }

        Macronutrientes macros = new Macronutrientes();
        macros.setCarbohidratos(calorias * 0.50 / 4);
        macros.setProteinas(calorias * 0.30 / 4);
        macros.setGrasas(calorias * 0.20 / 9);
        return macros;
    }

    @Override
    public DatosIMC calcularIMC(String genero, double altura, double peso, Integer edad) {
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso o altura inválidos para calcular el IMC.");
        }

        double imc = peso / (altura * altura);
        String clasificacion;

        if (genero.equalsIgnoreCase("hombre")) {
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

        return new DatosIMC(clasificacion, genero, altura, peso, edad, imc);
    }


}
