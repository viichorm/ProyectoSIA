package Modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import Excepciones.ClubYaExistenteException;
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

    public ClubesDeportivos(int idClub, String nombre, String direccion) {
        this.idClub = idClub;
        this.nombre = nombre;
        this.direccion = direccion;
        this.actividades = new ArrayList<>();
        this.socios = new ArrayList<>();
    }

    // Métodos getters y setters
    public int getidClub() { return idClub; }
    public void setId(int idClub) { this.idClub = idClub; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public ArrayList<String> getSocios() { return socios; }
    public void setSocios(ArrayList<String> socios) { this.socios = socios; }

    public ArrayList<ActividadesClubes> getActividades() { return actividades; }
    public void setActividades(ArrayList<ActividadesClubes> actividades) { this.actividades = actividades; }


        // Método toString para mostrar la información básica del club
        @Override
        public String toString() {
            return "ID Club: " + idClub + "\nNombre: " + nombre + "\nDirección: " + direccion;
        }

    // Metodos adicionales.

    
public void agregarActividades(ArrayList<ActividadesClubes> actividades) {
    this.actividades = actividades;
}

public void agregarSocios(ArrayList<String> socios) {
    this.socios = socios;
}

// Método adicional para convertir el nombre a mayúsculas.
public void convertirNombreAMayusculas() {
    this.nombre = this.nombre.toUpperCase();
}


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
        try {
            System.out.println("Agregar nuevo club:");
            System.out.print("Ingrese el nombre del club: ");
            String nombre = buffer.readLine();
            System.out.print("Ingrese la dirección del club: ");
            String direccion = buffer.readLine();
            System.out.print("Ingrese el ID del club: ");
            int idClub = Integer.parseInt(buffer.readLine());
    
            // Verifica si el club ya existe
            if (clubes.containsKey(idClub)) {
                throw new ClubYaExistenteException("El club con ID " + idClub + " ya existe.");
            }
    
            // Preguntar si el club es premium
            System.out.print("¿Es un club premium? (s/n): ");
            String esPremium = buffer.readLine();
    
            // Crear el club
            ClubesDeportivos nuevoClub;
            if (esPremium.equalsIgnoreCase("s")) {
                System.out.print("Ingrese los beneficios adicionales del club: ");
                String beneficios = buffer.readLine();
                nuevoClub = new ClubesPremium(idClub, nombre, direccion, beneficios);
            } else {
                nuevoClub = new ClubesDeportivos(idClub, nombre, direccion);
            }
    
            // Agregar socios al club
            System.out.println("Ingrese los nombres de los socios separados por comas (o presione Enter para omitir):");
            String sociosInput = buffer.readLine();
            if (!sociosInput.isEmpty()) {
                String[] sociosArray = sociosInput.split(",");
                for (String socio : sociosArray) {
                    nuevoClub.getSocios().add(socio.trim());
                }
            }
    
            // Añadir el club a la lista
            clubes.put(idClub, nuevoClub);
    
            // Guardar los clubes actualizados
            GestorPersistencia.guardarClubes("ArchivosTxt/Clubes.txt", clubes);
    
            System.out.println("Club agregado exitosamente.");
        } catch (ClubYaExistenteException e) {
            System.out.println(e.getMessage());
            System.out.println("Volviendo al menú principal...");
        }
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
            GestorPersistencia.guardarClubes("ArchivosTxt/Clubes.txt", clubes);
            System.out.println("Club editado exitosamente.");
        } else {
            System.out.println("El club con ID " + idClub + " no existe.");
        }
    }

    public static void eliminarClub(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        System.out.print("Ingrese el ID del club a eliminar: ");
        int idClub = Integer.parseInt(buffer.readLine());
    
        // Verifica si el club existe
        if (clubes.containsKey(idClub)) {
            // Obtén la instancia del club
            ClubesDeportivos clubSeleccionado = clubes.get(idClub);
            
            // Elimina todas las actividades asociadas antes de eliminar el club
            ArrayList<ActividadesClubes> actividades = clubSeleccionado.getActividades();
            if (!actividades.isEmpty()) {
                ActividadesClubes.eliminarActividad(actividades); // Llama al método para eliminar actividades
            }
    
            // Elimina el club
            clubes.remove(idClub);
            GestorPersistencia.guardarClubes("ArchivosTxt/Clubes.txt", clubes);
            System.out.println("Club eliminado exitosamente.");
        } else {
            System.out.println("El club con ID " + idClub + " no existe.");
        }
    }
    
}

