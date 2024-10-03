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
}

