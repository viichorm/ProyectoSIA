import java.io.*;
import java.util.*;

public class mainProyecto {
    public static void main(String[] args) {
        try {
            // Cargar clubes desde el archivo "Clubes.txt"
            HashMap<Integer, clubesDeportivos> clubes = cargarArchivo.cargarClubes("Clubes.txt");

            // Cargar actividades y asociarlas a los clubes desde el archivo "Actividades.txt"
            cargarArchivo.cargarActividades("Actividades.txt", clubes);

            // Aquí puedes agregar más lógica para interactuar con los clubes y actividades cargados
            // Por ejemplo, podrías imprimir la información de los clubes
            for (Integer id : clubes.keySet()) {
                clubesDeportivos club = clubes.get(id);
                System.out.println("Club ID: " + club.getidClub());
                System.out.println("Nombre: " + club.getNombre());
                System.out.println("Dirección: " + club.getDireccion());
                System.out.println("Actividades:");
                for (actividadesClubes actividad : club.getActividades()) {
                    System.out.println("  - " + actividad.getActividad() + " en " + actividad.getLugar() + " a las " + actividad.getHorario());
                }
                System.out.println("Miembros: " + club.getMiembros());
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar los archivos: " + e.getMessage());
        }
    }

}
