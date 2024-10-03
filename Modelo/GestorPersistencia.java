package Modelo;
import java.io.*;
import java.util.*;

public class GestorPersistencia {

    // Método para guardar los clubes en un archivo
    public static void guardarClubes(String archivoClubes, HashMap<Integer, ClubesDeportivos> clubes) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoClubes));
        
        for (ClubesDeportivos club : clubes.values()) {
            // Formato: idClub|nombre|direccion|socios separados por ';'|beneficios (si aplica)
            String socios = String.join(";", club.getSocios());  // Socios separados por ';'
            String lineaClub;

            // Si es un club premium, agregamos beneficios al final
            if (club instanceof ClubesPremium) {
                ClubesPremium clubPremium = (ClubesPremium) club;
                lineaClub = club.getidClub() + "|" + club.getNombre() + "|" + club.getDireccion() + "|" + socios + "|" + clubPremium.getBeneficiosAdicionales();
            } else {
                // Para un club regular, solo hasta los socios
                lineaClub = club.getidClub() + "|" + club.getNombre() + "|" + club.getDireccion() + "|" + socios;
            }

            escritor.write(lineaClub);
            escritor.newLine();
        }

        escritor.close();
    }

    // Método para guardar las actividades de cada club en un archivo
    public static void guardarActividades(String archivoActividades, HashMap<Integer, ClubesDeportivos> clubes) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoActividades, true)); // Modo append (true)
    
        for (ClubesDeportivos club : clubes.values()) {
            for (ActividadesClubes actividad : club.getActividades()) {
                // Formato básico: idClub|idActividad|nombre|horario|descripcion|lugar
                String lineaActividad = club.getidClub() + "|" + actividad.getidActividad() + "|" + actividad.getActividad() + "|" +
                        actividad.getHorario() + "|" + actividad.getDescripcion() + "|" + actividad.getLugar();
                
                // Si es una actividad extra deportiva, agregar el separador adicional y la etiqueta
                if (actividad instanceof ActividadesExtraDeportivas) {
                    lineaActividad += "|ExtraDeportiva";
                }
    
                escritor.write(lineaActividad);
                escritor.newLine();
            }
        }
        escritor.close();
    }

    public static void generarReporte(String nombreArchivo, HashMap<Integer, ClubesDeportivos> clubes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Reporte de Clubes y Actividades\n");
            writer.write("--------------------------------\n");
            
            // Cambiamos el tipo de clubId a Integer
            for (Integer clubId : clubes.keySet()) {
                ClubesDeportivos club = clubes.get(clubId);
                writer.write("Club: " + club.getNombre() + "\n"); 

                List<ActividadesClubes> actividades = club.getActividades(); 
                if (actividades != null && !actividades.isEmpty()) {
                    for (ActividadesClubes actividad : actividades) {
                        writer.write(" - Actividad: " + actividad.getActividad() + "\n"); 
                    }
                } else {
                    writer.write(" - No hay actividades registradas.\n");
                }
                writer.write("\n"); // Espacio entre clubes
            }
            writer.write("--------------------------------\n");
            writer.write("Fin del reporte.\n");
            System.out.println("Reporte generado exitosamente en " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}

