package Modelo;

import java.util.ArrayList;

public class ActividadesExtraDeportivas extends ActividadesClubes {

    private String tipoExtraDeportivo; // Nuevo atributo específico para actividades extra deportivas.

    // Constructor por defecto
    public ActividadesExtraDeportivas() {
        super(); // Llama al constructor por defecto de ActividadesClubes
        this.tipoExtraDeportivo = "";
    }

    // Constructor con parámetros
    public ActividadesExtraDeportivas(int idActividad, String actividad, String horario, String tipoExtraDeportivo) {
        super(idActividad, actividad, horario); // Llamada al constructor de la clase padre.
        this.tipoExtraDeportivo = tipoExtraDeportivo;
    }

    // Getter y Setter para el nuevo atributo
    public String getTipoExtraDeportivo() {
        return tipoExtraDeportivo;
    }

    public void setTipoExtraDeportivo(String tipoExtraDeportivo) {
        this.tipoExtraDeportivo = tipoExtraDeportivo;
    }

    // Método para mostrar actividades incluyendo detalles extra deportivos.
    public static void mostrarActividades(ArrayList<ActividadesClubes> actividades) {
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades registradas.");
            return;
        }

        System.out.println("\n--- Lista de Actividades Extra Deportivas ---");
        for (ActividadesClubes actividad : actividades) {
            if (actividad instanceof ActividadesExtraDeportivas) {
                ActividadesExtraDeportivas extra = (ActividadesExtraDeportivas) actividad;
                System.out.println("ID: " + extra.getidActividad());
                System.out.println("Nombre: " + extra.getActividad());
                System.out.println("Descripción: " + extra.getDescripcion());
                System.out.println("Horario: " + extra.getHorario());
                System.out.println("Lugar: " + extra.getLugar());
                System.out.println("Tipo Extra Deportivo: " + extra.getTipoExtraDeportivo());
                System.out.println("---------------------------");
            }
        }
    }
}
