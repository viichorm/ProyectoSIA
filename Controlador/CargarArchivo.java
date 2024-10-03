package Controlador;
import java.io.*;
import java.util.*;

import Modelo.ActividadesClubes;
import Modelo.ActividadesExtraDeportivas;
import Modelo.ClubesDeportivos;
import Modelo.ClubesPremium;



public class CargarArchivo {

    public static HashMap<Integer, ClubesDeportivos> cargarClubes(String fileName) throws IOException {
        HashMap<Integer, ClubesDeportivos> clubesMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] datos = line.split("\\|");
            int idClub = Integer.parseInt(datos[0]);
            String nombre = datos[1];
            String direccion = datos[2];
            ArrayList<String> socios = new ArrayList<>(Arrays.asList(datos[3].split(",")));  // Socios separados por comas
            
            ClubesDeportivos club;

            // Verifica si es un club premium (con beneficios adicionales en la columna 4)
            if (datos.length == 5) {
                String beneficios = datos[4];  // Club premium
                club = new ClubesPremium(idClub, nombre, direccion, beneficios);
            } else {
                club = new ClubesDeportivos(idClub, nombre, direccion);
            }

            // Asignar socios al club
            club.setSocios(socios);

            // Añadir club al mapa
            clubesMap.put(idClub, club);
        }
        br.close();
        return clubesMap;
    }

    public static void guardarClubes(String fileName, HashMap<Integer, ClubesDeportivos> clubesMap) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        
        for (ClubesDeportivos club : clubesMap.values()) {
            String socios = String.join(",", club.getSocios());
            if (club instanceof ClubesPremium) {
                ClubesPremium clubPremium = (ClubesPremium) club;
                bw.write(club.getidClub() + "|" + club.getNombre() + "|" + club.getDireccion() + "|" + socios + "|" + clubPremium.getBeneficiosAdicionales() + "\n");
            } else {
                bw.write(club.getidClub() + "|" + club.getNombre() + "|" + club.getDireccion() + "|" + socios + "\n");
            }
        }
        bw.close();
    }

    public static void cargarActividades(String fileName, HashMap<Integer, ClubesDeportivos> clubesMap) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
    
        while ((line = br.readLine()) != null) {
            String[] datos = line.split("\\|");
    
            try {
                int idClub = Integer.parseInt(datos[0]);
    
                if (clubesMap.containsKey(idClub)) {
                    ActividadesClubes actividad;
    
                    // Verifica si es una actividad extra deportiva según la etiqueta
                    if (datos.length == 7 && "ExtraDeportiva".equals(datos[6])) {
                        actividad = new ActividadesExtraDeportivas();
                    } else {
                        actividad = new ActividadesClubes();
                    }
    
                    // Asignar los datos del archivo a los campos de la actividad
                    actividad.setID(Integer.parseInt(datos[1])); // ID de la actividad
                    actividad.setActividad(datos[2]); // Tipo de actividad
                    actividad.setHorario(datos[3]); // Horario
                    actividad.setDescripcion(datos[4]); // Descripción
                    actividad.setLugar(datos[5]); // Lugar
    
                    // Agregar la actividad al club correspondiente
                    clubesMap.get(idClub).getActividades().add(actividad);
                }
    
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Error al procesar la línea: " + line);
                System.err.println("Causa: " + e.getMessage());
            }
        }
        br.close();
    }
}
