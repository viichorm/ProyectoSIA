import java.util.*;

public class actividadesClubes {
    private String actividad;
    private String descripcion;
    private int idActividad;
    private String horario;
    private String lugar;

    // getters
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

    // setters

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


}
