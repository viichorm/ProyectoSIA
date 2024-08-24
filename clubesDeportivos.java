import java.util.*;
public class clubesDeportivos {
    private String nombre; //nombre del club
    private String direccion; //direccion donde se encuentra el club
    private int idClub; //identificador del club
    private HashMap <String, actividadesClubes> actividades; //actividades cuales tiene el club
    private ArrayList<String> miembros; //miembros que pertenecen al club

    // getters

    public String getNombre(){return nombre;}
    public String getDireccion(){return direccion;}
    public int getidClub(){return idClub;}
    public HashMap<String, actividadesClubes> getActividades(){return actividades;}
    public ArrayList<String> getMiembros(){return miembros;}

    //setters

    public void setNombre(String nombre){this.nombre = nombre;}
    public void setDireccion(String direccion){this.direccion = direccion;}
    public void setID(int id){this.idClub = id;}
    public void setActividades(HashMap<String, actividadesClubes> actividades) {this.actividades = actividades;}
    public void setMiembros(ArrayList<String> miembros){this.miembros = miembros;}



}
