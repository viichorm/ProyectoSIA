import java.util.*;

public class actividadesClubes{
    private int idActividad; //Identificador de la actividad.
    private String actividad; // Nombre de la actividad.
    private String descripcion; // Descripción de la actividad.
    private String horario; 
    private String lugar;

    //Constructor default.
    public actividadesClubes()
    {
        this.idActividad = 0;
        this.actividad = "";
        this.descripcion = "";
        this.horario = "";
        this.lugar = "";
    }

    //Constructor con tres parametros basicos. (Id, actividad,horario)
    public actividadesClubes(int idActividad, String actividad, String horario)
    {
        this.idActividad = idActividad;
        this.actividad = actividad;
        this.horario = horario;
        this.descripcion = "";
        this.lugar = "";
    }

    //Constrictor con todos los parametros.
    public actividadesClubes(int idActividad, String actividad, String horario, String descripcion, String lugar)
    {
        this.idActividad = idActividad;
        this.actividad = actividad;
        this.horario = horario;
        this.descripcion = descripcion;
        this.lugar = lugar;
    }

    //Constructor que copia datos de una actividad ya existente.
    public actividadesClubes(actividadesClubes actividad)
    {
        this.idActividad = actividad.idActividad;
        this.actividad = actividad.actividad;
        this.descripcion = actividad.descripcion;
        this.lugar = actividad.lugar;
        this.horario = actividad.horario;
    } 

    //Getters
    public String getActividad() {
        return actividad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getidActividad() {
        return idActividad;
    }
    
    public String getHorario() {
        return horario;
    }

    public String getLugar() {
        return lugar;
    }   

    //Setters
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setID(int id) {
        this.idActividad = id;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    //Metodos adicionales.

    //Establecer el ID de la actividad validando que sea positivo.
    public void setID(int id, boolean validar)
    {
        if(validar && id <= 0)
        {
            throw new IllegalArgumentException("El ID de la actividad debe ser positivo.");
        }
        this.idActividad = id;
    }

    //Establecer el nombre de la actividad en mayusculas si se desea.
    public void setActividad(String actividad, boolean mayuscula)
    {
        if(mayuscula)
        {
            this.actividad = actividad.toUpperCase();
        }
        else{
            this.actividad = actividad;
        }
    }

    //Establecer el lugar de la actividad añadiendo su numero de sala.
    public void setLugar(String lugar, int sala)
    {
        if(sala <= 0){
            throw new IllegalArgumentException("El número de la sala debe ser positivo");
        }
        this.lugar = lugar + " - Sala número" + sala;
    }

    //Establecer la descripcion en mayusculas si se desea.
    public void setDescripcion(String descripcion, boolean mayus)
    {
        if(mayus)
        {
            this.descripcion = descripcion.toUpperCase();
        }
        else{
            this.descripcion = descripcion;
        }
    }
    
}
