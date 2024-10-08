package com.tallerwebi.presentacion;

public class Macronutrientes {

    private Double carbohidratos;
    private Double proteinas;
    private Double grasas;


    public Double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Double getProteinas() {
        return proteinas;
    }

    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }

    public Double getGrasas() {
        return grasas;
    }

    public void setGrasas(Double grasas) {
        this.grasas = grasas;
    }

    // Método toString() para mostrar los valores fácilmente
    @Override
    public String toString() {
        return "Macronutrientes{" +
                "carbohidratos=" + carbohidratos +
                ", proteinas=" + proteinas +
                ", grasas=" + grasas +
                '}';
    }
}
