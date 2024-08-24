import java.io.*;
import java.util.*;

public class mainProyecto {
    public static void main(String[] args) {
        try {
            // Cargar clubes desde el archivo "Clubes.txt"
            HashMap<Integer, clubesDeportivos> clubes = cargarArchivo.cargarClubes("ArchivosTxt/Clubes.txt");

            // Cargar actividades y asociarlas a los clubes desde el archivo "Actividades.txt"
            cargarArchivo.cargarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);

            // Mostrar el menú principal
            menusConsola.mostrarMenuPrincipal(clubes);

        } catch (IOException e) {
            System.out.println("Error al cargar los archivos: " + e.getMessage());
        }
    }

}
