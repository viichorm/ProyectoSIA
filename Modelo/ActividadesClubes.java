package Modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import Excepciones.ActividadNoEncontradaException;

public class ActividadesClubes{
    private int idActividad; //Identificador de la actividad.
    private String actividad; // Nombre de la actividad.
    private String descripcion; // Descripción de la actividad.
    private String horario; 
    private String lugar;

    //Constructor default.
    public ActividadesClubes()
    {
        this.idActividad = 0;
        this.actividad = "";
        this.descripcion = "";
        this.horario = "";
        this.lugar = "";
    }

    // Constructor único con los parámetros obligatorios.

    public ActividadesClubes(int idActividad, String actividad, String horario) {
        this.idActividad = idActividad;
        this.actividad = actividad;
        this.horario = horario;
    }

    //Getters
    public String getActividad() {return actividad;}
    public String getDescripcion() { return descripcion;}
    public int getidActividad() {return idActividad;}
    public String getHorario() {return horario;}
    public String getLugar() {return lugar;}   

    //Setters
    public void setActividad(String actividad) {this.actividad = actividad;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setID(int id) {this.idActividad = id;}
    public void setHorario(String horario) {this.horario = horario;}
    public void setLugar(String lugar) {this.lugar = lugar;}

    //Metodos adicionales.

    public void agregarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void agregarLugar(String lugar) {
        this.lugar = lugar;
    }

    // Método adicional para convertir la actividad a mayúsculas.
public void convertirActividadAMayusculas() {
    this.actividad = this.actividad.toUpperCase();
}

public static void agregarActividad(ArrayList<ActividadesClubes> actividades, BufferedReader buffer) throws IOException {
    System.out.print("¿Es una actividad extra deportiva? (si/no): ");
    String esExtra = buffer.readLine().toLowerCase();

    ActividadesClubes nuevaActividad = null;

    if (esExtra.equals("si")) {
        nuevaActividad = new ActividadesExtraDeportivas();
        System.out.print("Ingrese el tipo de actividad extra deportiva: ");
        ((ActividadesExtraDeportivas) nuevaActividad).setTipoExtraDeportivo(buffer.readLine());
    }

    System.out.print("Ingrese el ID de la actividad: ");
    nuevaActividad.setID(Integer.parseInt(buffer.readLine()));
    System.out.print("Ingrese el nombre de la actividad: ");
    nuevaActividad.setActividad(buffer.readLine());
    System.out.print("Ingrese el horario de la actividad: ");
    nuevaActividad.setHorario(buffer.readLine());
    System.out.print("Ingrese la descripción de la actividad: ");
    nuevaActividad.setDescripcion(buffer.readLine());
    System.out.print("Ingrese el lugar de la actividad: ");
    nuevaActividad.setLugar(buffer.readLine());

    actividades.add(nuevaActividad);
    System.out.println("Actividad agregada con éxito.");
}


    public static void editarActividad(ArrayList<ActividadesClubes> actividades, BufferedReader buffer) throws IOException, ActividadNoEncontradaException {
        System.out.print("Ingrese el ID de la actividad a editar: ");
        int idActividad = Integer.parseInt(buffer.readLine());
    
        for (ActividadesClubes actividad : actividades) {
            if (actividad.getidActividad() == idActividad) {
                System.out.print("Ingrese el nuevo nombre de la actividad: ");
                actividad.setActividad(buffer.readLine());
                System.out.print("Ingrese la nueva descripción de la actividad: ");
                actividad.setDescripcion(buffer.readLine());
                System.out.print("Ingrese el nuevo horario de la actividad: ");
                actividad.setHorario(buffer.readLine());
                System.out.print("Ingrese el nuevo lugar de la actividad: ");
                actividad.setLugar(buffer.readLine());
                System.out.println("Actividad editada con éxito.");
                return;
            }
        }
        throw new ActividadNoEncontradaException("La actividad con ID " + idActividad + " no fue encontrada.");
    }

    public static void eliminarActividad(ArrayList<ActividadesClubes> actividades, BufferedReader buffer) throws IOException {
        System.out.print("Ingrese el ID de la actividad a eliminar: ");
        int idActividadEliminar = Integer.parseInt(buffer.readLine());
        
        Iterator<ActividadesClubes> iterator = actividades.iterator();
        while (iterator.hasNext()) {
            ActividadesClubes actividad = iterator.next();
            if (actividad.getidActividad() == idActividadEliminar) {
                iterator.remove();
                System.out.println("Actividad eliminada con éxito.");
                return;
            }
        }
        System.out.println("Actividad no encontrada.");
    }

    public static void eliminarActividad(ArrayList<ActividadesClubes> actividades) {
        if (actividades == null || actividades.isEmpty()) {
            System.out.println("No hay actividades para eliminar.");
            return;
        }
    
        // Elimina todas las actividades
        actividades.clear();
        System.out.println("Todas las actividades del club han sido eliminadas.");
    }
    


    public static void mostrarActividades(ArrayList<ActividadesClubes> actividades) {
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades registradas.");
            return; 
        }

        System.out.println("\n--- Lista de Actividades ---");
        for (ActividadesClubes actividad : actividades) {
            System.out.println("ID: " + actividad.getidActividad());
            System.out.println("Nombre: " + actividad.getActividad());
            System.out.println("Descripción: " + actividad.getDescripcion());
            System.out.println("Horario: " + actividad.getHorario());
            System.out.println("Lugar: " + actividad.getLugar());
            System.out.println("---------------------------");
        }
    }


}
