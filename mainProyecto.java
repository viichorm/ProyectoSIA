import java.io.*;
import java.util.*;

public class mainProyecto {
    public static void main(String[] args) {
        try {
            // Cargar clubes desde el archivo "Clubes.txt"
            HashMap<Integer, clubesDeportivos> clubes = cargarArchivo.cargarClubes("ArchivosTxt/Clubes.txt");

            // Cargar actividades y asociarlas a los clubes desde el archivo "Actividades.txt"
            cargarArchivo.cargarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);

            //Aqui falta aun implementacion del menu....
            for (Integer id : clubes.keySet()) {
                clubesDeportivos club = clubes.get(id);
                System.out.println("\nClub ID: " + club.getidClub());
                System.out.println("Nombre: " + club.getNombre());
                System.out.println("Dirección: " + club.getDireccion());
                System.out.println("Actividades:");

                if (club.getActividades().isEmpty()) {
                    System.out.println("  - No hay actividades");
                }

                for (actividadesClubes actividad : club.getActividades()) {

                    System.out.println("  - " + actividad.getActividad() + " en " + actividad.getLugar() + " a las " + actividad.getHorario());
                }
                System.out.println("\nSocios: " + club.getSocios());
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar los archivos: " + e.getMessage());
        }
    }

}
