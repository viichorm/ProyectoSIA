package Controlador;
import java.io.*;
import java.util.*;

import Modelo.ActividadesClubes;
import Modelo.ClubesDeportivos;



public class CargarArchivo {

    public static HashMap<Integer, ClubesDeportivos> cargarClubes(String fileName) throws IOException {
        HashMap<Integer, ClubesDeportivos> clubesMap = new HashMap<>();

                // Datos hardcoded
                ClubesDeportivos club1 = new ClubesDeportivos();
                club1.setId(12445);
                club1.setNombre("Club Deportivo Real Santiago FC");
                club1.setDireccion("Calle Maipu 2237");
                club1.setSocios(new ArrayList<>(Arrays.asList("Juan", "Pedro", "Ana")));
                club1.setActividades(new ArrayList<>());
        
                ClubesDeportivos club2 = new ClubesDeportivos();
                club2.setId(13567);
                club2.setNombre("FC Universidad Sausalito");
                club2.setDireccion("Sausalito 123");
                club2.setSocios(new ArrayList<>(Arrays.asList("Luis", "Maria")));
                club2.setActividades(new ArrayList<>());
        
                // Agregar clubes al mapa
                clubesMap.put(club1.getidClub(), club1);
                clubesMap.put(club2.getidClub(), club2);
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] datos = line.split("\\|");
            int idClub = Integer.parseInt(datos[0]);
            String nombre = datos[1];
            String direccion = datos[2];
            String miembrosString = datos[3];
            String[] miembrosArray = miembrosString.split(",");

            ClubesDeportivos club = new ClubesDeportivos();
            club.setId(idClub);
            club.setNombre(nombre);
            club.setDireccion(direccion);
            club.setActividades(new ArrayList<ActividadesClubes>());
            club.setSocios(new ArrayList<String>());

            for (String miembro : miembrosArray) {
                club.getSocios().add(miembro);
            }

            clubesMap.put(idClub, club);
        }
        br.close();
        return clubesMap;
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
            int idClub = Integer.parseInt(datos[0]);

            if (clubesMap.containsKey(idClub)) {
                ActividadesClubes actividad = new ActividadesClubes();
                actividad.setActividad(datos[1]);
                actividad.setDescripcion(datos[2]);
                actividad.setID(Integer.parseInt(datos[3]));
                actividad.setHorario(datos[4]);
                actividad.setLugar(datos[5]);

                clubesMap.get(idClub).getActividades().add(actividad);
            }
        }
        br.close();
    }
}
