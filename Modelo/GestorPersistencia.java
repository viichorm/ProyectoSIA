package Modelo;
import java.io.*;
import java.util.*;

public class GestorPersistencia {

    // Método para guardar los clubes en un archivo
    public static void guardarClubes(String archivoClubes, HashMap<Integer, ClubesDeportivos> clubes) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoClubes));
        
        for (ClubesDeportivos club : clubes.values()) {
            // Formato: idClub|nombre|direccion|socios separados por ';'|beneficios (si aplica)
            String socios = String.join(" ", club.getSocios());  // Socios separados por ';'
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
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoActividades)); // Modo append (true)
    
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
                writer.write("ID del Club: " + clubId + "\n");
                writer.write("Nombre: " + club.getNombre() + "\n");
                writer.write("Dirección: " + club.getDireccion() + "\n"); // Suponiendo que hay un método getDireccion()
                writer.write("Número de Socios: " + club.getSocios().size() + "\n"); // Suponiendo que hay un método getSocios()
                writer.write("Tipo de Club: " + (club instanceof ClubesPremium ? "Premium" : "Regular") + "\n");
                writer.write("--------------------------------\n");
                
                List<ActividadesClubes> actividades = club.getActividades(); 
                if (actividades != null && !actividades.isEmpty()) {
                    for (ActividadesClubes actividad : actividades) {
                        // Diferenciar entre actividades deportivas y extra deportivas
                        if (actividad instanceof ActividadesExtraDeportivas) {
                            writer.write(" - Actividad Extra Deportiva: " + actividad.getActividad() + "\n");
                        } else {
                            writer.write(" - Actividad Deportiva: " + actividad.getActividad() + "\n");
                        }
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

    public static void guardarActividad(String archivoActividades, ClubesDeportivos club) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoActividades)); // Elimina modo append
        
        // Guardar solo las actividades del club especificado
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
        escritor.close();
    }   
}