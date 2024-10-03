package Modelo;
import java.io.*;
import java.util.*;

public class GestorPersistencia {

    // Método para guardar los clubes en un archivo
    public static void guardarClubes(String archivoClubes, HashMap<Integer, ClubesDeportivos> clubes) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoClubes));
        
        for (ClubesDeportivos club : clubes.values()) {
            // Formato: idClub,nombre,direccion,socios (separados por ';')
            String lineaClub = club.getidClub() + "|" + club.getNombre() + "|" + club.getDireccion() + "|" + String.join("|", club.getSocios());
            escritor.write(lineaClub);
            escritor.newLine();
        }
        escritor.close();
    }

    // Método para guardar las actividades de cada club en un archivo
    public static void guardarActividades(String archivoActividades, HashMap<Integer, ClubesDeportivos> clubes) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoActividades));

        for (ClubesDeportivos club : clubes.values()) {
            for (ActividadesClubes actividad : club.getActividades()) {
                // Formato: idClub,idActividad,nombre,horario,descripcion,lugar
                String lineaActividad = club.getidClub() + "|" + actividad.getidActividad() + "|" + actividad.getActividad() + "|" +
                        actividad.getHorario() + "|" + actividad.getDescripcion() + "|" + actividad.getLugar();
                escritor.write(lineaActividad);
                escritor.newLine();
            }
        }
        escritor.close();
    }
}

