import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
public class ClubesDeportivos{
    private int idClub; //identificador del club
    private String nombre; //nombre del club
    private String direccion; //direccion donde se encuentra el club
    private ArrayList<ActividadesClubes> actividades; //actividades cuales tiene el club
    private ArrayList<String> socios; //Socios a quienes les pertenecen el club

    // Constructor default.
    public ClubesDeportivos()
    {
        this.nombre = "";
        this.direccion = "";
        this.idClub = 0;
        this.actividades = new ArrayList<>();
        this.socios = new ArrayList<>();
    }

    // Constructor con parametros basicos (idClub, nombre, direccion)
    public ClubesDeportivos(int idClub, String nombre, String direccion)
    {
        this.idClub = idClub;
        this.nombre = nombre;
        this.direccion = direccion;
        this.actividades = new ArrayList<>();
        this.socios = new ArrayList<>();
    }

    // Constructor con todos los parametros.
    public ClubesDeportivos(int idClub, String nombre, String direccion, ArrayList<ActividadesClubes> actvidades, ArrayList<String> socios)
    {
        this.idClub = idClub;
        this.nombre = nombre;
        this.direccion = direccion;
        this.actividades = actvidades;
        this.socios = socios;

    }

    // Constructor que copia datos ya de un club ya existente.
    public ClubesDeportivos(ClubesDeportivos club)
    {
        this.idClub = club.idClub;
        this.nombre = club.nombre;
        this.direccion = club.direccion;
        this.actividades = new ArrayList<>(club.actividades);
        this.socios = new ArrayList<>(club.socios);
    }

    // Getters
    public String getNombre(){return nombre;}
    public String getDireccion(){return direccion;}
    public int getidClub(){return idClub;}
    public ArrayList<ActividadesClubes> getActividades(){return actividades;}
    public ArrayList<String> getSocios(){return socios;}

    // Setters
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setDireccion(String direccion){this.direccion = direccion;}
    public void setId(int id){this.idClub = id;}
    public void setActividades(ArrayList<ActividadesClubes> actividades) {this.actividades = actividades;}
    public void setSocios(ArrayList<String> Socios){this.socios = Socios;}


    // Metodos adicionales.

    // Establecer el Id del club validando que sea positivo.
    public void setId(int id, boolean validar)
    {
        if(validar && id <= 0)
        {
            throw new IllegalArgumentException("El ID del club debe ser positivo."); 
        }
        this.idClub = id;
    }
    // Establecer el nombre del club en mayusculas si se desea.
    public void setNombre(String nombre, boolean mayuscula)
    {
        if(mayuscula == true)
        {
            this.nombre = nombre.toUpperCase();
        }
        else{
            this.nombre = nombre;
        }
    }

    // Establecer la direccion del club añadiendo su comuna.
    public void setDireccion(String direccion, String comuna)
    {
        this.direccion = direccion + ", " + comuna;
    }

    // Añadir un socio en especifico.
    public void addSocio(String socio)
    {
        if(this.socios == null)
        {
            this.socios = new ArrayList<>();
        }
        this.socios.add(socio);
    }

    // Añadir multiples socios desde un arreglo.
    public void addSocio(String[] sociosA)
    {
        if(this.socios == null)
        {
            this.socios = new ArrayList<>();
        }
        this.socios.addAll(Arrays.asList(sociosA));
    }

    //Metodos para la gestión de clubes.

    public static void agregarClub(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        System.out.println("Agregar nuevo club:");
        System.out.print("Ingrese el nombre del club: ");
        String nombre = buffer.readLine();
        System.out.print("Ingrese la dirección del club: ");
        String direccion = buffer.readLine();
        System.out.print("Ingrese el ID del club: ");
        int idClub = Integer.parseInt(buffer.readLine());

        ClubesDeportivos nuevoClub = new ClubesDeportivos();
        nuevoClub.setNombre(nombre);
        nuevoClub.setDireccion(direccion);
        nuevoClub.setId(idClub);
        nuevoClub.setActividades(new ArrayList<>());
        nuevoClub.setSocios(new ArrayList<>());

        clubes.put(idClub, nuevoClub);
        System.out.println("Club agregado exitosamente.");
    }

    public static void editarClub(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        System.out.print("Ingrese el ID del club a editar: ");
        int idClub = Integer.parseInt(buffer.readLine());

        if (clubes.containsKey(idClub)) {
            ClubesDeportivos club = clubes.get(idClub);

            System.out.print("Ingrese el nuevo nombre del club (actual: " + club.getNombre() + "): ");
            String nuevoNombre = buffer.readLine();
            if (!nuevoNombre.isEmpty()) {
                club.setNombre(nuevoNombre);
            }

            System.out.print("Ingrese la nueva dirección del club (actual: " + club.getDireccion() + "): ");
            String nuevaDireccion = buffer.readLine();
            if (!nuevaDireccion.isEmpty()) {
                club.setDireccion(nuevaDireccion);
            }

            System.out.println("Club editado exitosamente.");
        } else {
            System.out.println("El club con ID " + idClub + " no existe.");
        }
    }

    public static void eliminarClub(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        System.out.print("Ingrese el ID del club a eliminar: ");
        int idClub = Integer.parseInt(buffer.readLine());

        if (clubes.containsKey(idClub)) {
            clubes.remove(idClub);
            System.out.println("Club eliminado exitosamente.");
        } else {
            System.out.println("El club con ID " + idClub + " no existe.");
        }
        

    }
}

