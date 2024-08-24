import java.util.HashMap;
import java.util.Scanner;

public class menusConsola 
{
    private HashMap<Integer, clubesDeportivos> clubes;
    
    public  menusConsola(HashMap<Integer, clubesDeportivos> clubes)
    {
         this.clubes = clubes;
    }

    public void mostrarMenu()
    {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("\n---Gestión de actividades en clubes deportivos---");
            System.out.println("A continuación, por favor seleecione una opcion: ");
            System.out.println("1. Agregar actividad al sistema");
            System.out.println("2. Mostrar listado de actividades.");
            System.out.println("3. Salir del sistema");
            opcion = scanner.nextInt();
            scanner.nextLine(); //Limpiar el buffer

            switch (opcion)
            {
                case 1:
                    agregarActividad(scanner);
                    break;
                case 2:
                    mostrarListado();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no valida. Por favor, intente nuevamente.");
            }
        } while(opcion != 3);
        
        scanner.close();
    }

    private void agregarActividad(Scanner scanner)
    {
        System.out.println("Ingrese el ID del club: ");
        int idClub = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        if (clubes.containsKey(idClub))
        {
            System.out.println("Ingrese la actividad a agregar: ");
            String actividad = scanner.nextLine();

            System.out.println("Ingrese la descripción de la actividad: ");
            String descripcion = scanner.nextLine();

            System.out.println("Ingrese el ID de la actividad: ");
            int idActividad = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Ingrese el horario de la actividad: ");
            String horario = scanner.nextLine();

            System.out.print("Ingrese el lugar de la actividad: ");
            String lugar = scanner.nextLine();

            actividadesClubes nuevaActividad = new actividadesClubes();
            nuevaActividad.setActividad(actividad);
            nuevaActividad.setDescripcion(descripcion);
            nuevaActividad.setID(idActividad);
            nuevaActividad.setHorario(horario);
            nuevaActividad.setLugar(lugar);

            clubes.get(idClub).getActividades().add(nuevaActividad);
            System.out.println("Actividad añadida con éxito al club con ID: " + idClub);
        }
        else
        {
            System.out.println("El club con ID " + idClub + " no existe.");
        }
    }

    private void mostrarListado()
    {
        for (Integer id: clubes.keySet())
        {
            clubesDeportivos club = clubes.get(id);
            System.out.println("\nClub ID: " + club.getidClub());
            System.out.println("Nombre: " + club.getNombre());
            System.out.println("Dirección: " + club.getDireccion());
            System.out.println("Actividades:");

            if (club.getActividades().isEmpty())
            {
                System.out.println("  - No hay actividades")
            }

            for(actividadesClubes actividad: club.getActividades())
            {
                System.out.println("  - " + actividad.getActividad() + " en " + actividad.getLugar() + " a las " + actividad.getHorario());
            }
        }
    }
}
