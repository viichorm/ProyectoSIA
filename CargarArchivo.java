import java.io.*;
import java.util.*;



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