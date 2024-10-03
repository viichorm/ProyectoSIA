package Modelo;


public class ClubesPremium extends ClubesDeportivos {

    private String beneficiosAdicionales;

    // Constructor
    public ClubesPremium(int idClub, String nombre, String direccion, String beneficiosAdicionales) {
        super(idClub, nombre, direccion); // Llamada al constructor de la clase padre
        this.beneficiosAdicionales = beneficiosAdicionales;
    }

    // Getter para los beneficios adicionales
    public String getBeneficiosAdicionales() {
        return beneficiosAdicionales;
    }

    // Setter para los beneficios adicionales
    public void setBeneficiosAdicionales(String beneficiosAdicionales) {
        this.beneficiosAdicionales = beneficiosAdicionales;
    }

    // Sobrescribir el método toString para mostrar la información del club premium
    @Override
    public String toString() {
        return super.toString() + "\nBeneficios adicionales: " + beneficiosAdicionales;
    }
}

