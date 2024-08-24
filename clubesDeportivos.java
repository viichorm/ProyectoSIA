import java.util.*;
public class clubesDeportivos {
    private String nombre; //nombre del club
    private String direccion; //direccion donde se encuentra el club
    private int idClub; //identificador del club
    private ArrayList<actividadesClubes> actividades; //actividades cuales tiene el club
    private ArrayList<String> Socios; //Socios a quienes les pertenecen el club

    // getters

    public String getNombre(){return nombre;}
    public String getDireccion(){return direccion;}
    public int getidClub(){return idClub;}
    public ArrayList<actividadesClubes> getActividades(){return actividades;}
    public ArrayList<String> getSocios(){return Socios;}

    //setters

    public void setNombre(String nombre){this.nombre = nombre;}
    public void setDireccion(String direccion){this.direccion = direccion;}
    public void setID(int id){this.idClub = id;}
    public void setActividades(ArrayList<actividadesClubes> actividades) {this.actividades = actividades;}
    public void setSocios(ArrayList<String> Socios){this.Socios = Socios;}



}
