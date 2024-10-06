package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorFiltros {


    public static ArrayList<ActividadesClubes> filtrarActividadesPorHorario(String horario, HashMap<Integer, ClubesDeportivos> clubes) {
        ArrayList<ActividadesClubes> actividadesFiltradas = new ArrayList<>();
    
        for (ClubesDeportivos club : clubes.values()) {
            for (ActividadesClubes actividad : club.getActividades()) {
                if (actividad.getHorario().equals(horario)) {
                    actividadesFiltradas.add(actividad);
                }
            }
        }
        return actividadesFiltradas;
    }
    
    public static void mostrarActividadesFiltradas(ArrayList<ActividadesClubes> actividadesFiltradas) {
        if (actividadesFiltradas.isEmpty()) {
            System.out.println("No se encontraron actividades que cumplan con los criterios especificados.");
        } else {
            System.out.println("\n--- Actividades Filtradas ---");
            for (ActividadesClubes actividad : actividadesFiltradas) {
                System.out.println(actividad.toString()); 
            }
        }
    }
}
