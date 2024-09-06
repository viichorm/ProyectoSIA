import java.io.*;
import java.util.*;

public class MainProyecto {
    public static void main(String[] args) {
        try {
            // Cargar clubes desde el archivo "Clubes.txt"
            HashMap<Integer, ClubesDeportivos> clubes = CargarArchivo.cargarClubes("ArchivosTxt/Clubes.txt");

            // Cargar actividades y asociarlas a los clubes desde el archivo "Actividades.txt"
            CargarArchivo.cargarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);

            // Mostrar el menú principal
            MenusConsola.mostrarMenuPrincipal(clubes);

        } catch (IOException e) {
            System.out.println("Error al cargar los archivos: " + e.getMessage());
        }
    }
}
