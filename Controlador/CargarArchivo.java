package Controlador;
import java.io.*;
import java.util.*;

import Modelo.ActividadesClubes;
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
        
        // Datos hardcoded de actividades
        ActividadesClubes actividad1 = new ActividadesClubes();
        actividad1.setActividad("Fútbol");
        actividad1.setDescripcion("Partido de fútbol");
        actividad1.setID(101);
        actividad1.setHorario(" 10:00 AM");
        actividad1.setLugar("Campo 1");

        ActividadesClubes actividad2 = new ActividadesClubes();
        actividad2.setActividad("Yoga");
        actividad2.setDescripcion("Clases de yoga ");
        actividad2.setID(102);
        actividad2.setHorario("8:00 AM");
        actividad2.setLugar("Sala de Yoga");

        // Agregar actividades a clubes
        if (clubesMap.containsKey(1)) {
            clubesMap.get(1).getActividades().add(actividad1);
        }

        if (clubesMap.containsKey(2)) {
            clubesMap.get(2).getActividades().add(actividad2);
        }
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
    
        while ((line = br.readLine()) != null) {
            String[] datos = line.split("\\|");
    
            try {
                // Validar que el primer dato es un número entero
                int idClub = Integer.parseInt(datos[0]);
    
                if (clubesMap.containsKey(idClub)) {
                    ActividadesClubes actividad = new ActividadesClubes();
    
                    // Asignar los datos a los campos de la actividad
                    actividad.setHorario(datos[1]); // Horario en formato 24 horas
                    actividad.setID(Integer.parseInt(datos[2])); // ID de la actividad
                    actividad.setDescripcion(datos[3]); // Nombre o descripción de la actividad
                    actividad.setActividad(datos[4]); // Tipo de actividad (Fútbol, Natación, etc.)
                    actividad.setLugar(datos[5]); // Lugar donde se realiza la actividad
    
                    // Agregar la actividad al club correspondiente
                    clubesMap.get(idClub).getActividades().add(actividad);
                }
    
            } catch (NumberFormatException e) {
                // Mostrar un mensaje de error indicando la línea problemática y la causa
                System.err.println("Error al procesar la línea: " + line);
                System.err.println("Causa: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                // Captura si hay menos elementos de los esperados en la línea
                System.err.println("Error en formato de línea: " + line);
                System.err.println("Causa: " + e.getMessage());
            }
        }
        br.close();
    }
}
