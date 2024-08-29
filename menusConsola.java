import java.io.*;
import java.util.*;

public class menusConsola {

    public static void mostrarMenuPrincipal(HashMap<Integer, clubesDeportivos> clubes) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String op = "";

        do {
            System.out.println("***** Sistema de Información de Clubes *****");
            System.out.println("********************************************");
            System.out.println("*****    Elija una de las opciones     *****");
            System.out.println("*****   1. Lista de Clubes             *****");
            System.out.println("*****   2. Editar Clubes               *****");
            System.out.println("*****   3. Actividades de un club      *****");
            System.out.println("*****   4. Salir                       *****");
            System.out.println("********************************************");
            System.out.print("Elija la opción: ");
            op = buffer.readLine();
            System.out.println("--- La opción elegida es: " + op + "\n");

            switch (Integer.parseInt(op)) {
                case 1:
                    mostrarSubMenuListaClubes(clubes, buffer);
                    break;

                case 2:
                    mostrarSubMenuEditarClubes(clubes, buffer);
                    break;

                case 3:
                    actividadesClubs(clubes, buffer);
                    break;


                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        } while (!op.equals("4"));
    }

    public static void mostrarSubMenuListaClubes(HashMap<Integer, clubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        String op = "";

        do {
            System.out.println("*****              Submenú: Lista de Clubes             *****");
            System.out.println("*************************************************************");
            System.out.println("***** 1. Mostrar lista de IDs de los clubes             *****");
            System.out.println("***** 2. Mostrar lista completa de clubes y actividades *****");
            System.out.println("***** 3. Regresar al menú principal                     *****");
            System.out.println("*************************************************************");
            System.out.print("Elija la opción: ");
            op = buffer.readLine();
            System.out.println("--- La opción elegida es: " + op + "\n");

            switch (Integer.parseInt(op)) {
                case 1:
                    System.out.println("Lista de IDs de los Clubes:");
                    for (Integer id : clubes.keySet()) {
                        System.out.println("ID del Club: " + id);
                    }
                    break;

                case 2:
                    for (Integer id : clubes.keySet()) {
                        clubesDeportivos club = clubes.get(id);
                        System.out.println("\nClub ID: " + club.getidClub());
                        System.out.println("Nombre: " + club.getNombre());
                        System.out.println("Dirección: " + club.getDireccion());
                        System.out.println("Actividades:");
                        if (club.getActividades().isEmpty()) {
                            System.out.println("  - No hay actividades");
                        } else {
                            for (actividadesClubes actividad : club.getActividades()) {
                                System.out.println("  - " + actividad.getActividad() + " en " + actividad.getLugar() + " a las " + actividad.getHorario());
                            }
                        }
                        System.out.println("Miembros: " + club.getSocios());
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
            esperarTecla(buffer);  // Espera al usuario para continuar
        } while (!op.equals("3"));
    }

    public static void mostrarSubMenuEditarClubes(HashMap<Integer, clubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        String op = "";

        do {
            System.out.println("*****     Submenú: Editar Clubes    *****");
            System.out.println("*****************************************");
            System.out.println("***** 1. Agregar Club               *****");
            System.out.println("***** 2. Editar Club                *****");
            System.out.println("***** 3. Eliminar Club              *****");
            System.out.println("***** 4. Regresar al menú principal *****");
            System.out.println("*****************************************");
            System.out.print("Elija la opción: ");
            op = buffer.readLine();
            System.out.println("--- La opción elegida es: " + op + "\n");

            switch (Integer.parseInt(op)) {
                case 1:
                    //agregarClub(clubes, buffer);
                    System.out.println("FUNCION AUN NO DISPONIBLE");
                    break;

                case 2:
                    //editarClub(clubes, buffer);
                    System.out.println("FUNCION AUN NO DISPONIBLE");
                    break;

                case 3:
                    //eliminarClub(clubes, buffer);
                    System.out.println("FUNCION AUN NO DISPONIBLE");
                    break;

                case 4:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
            esperarTecla(buffer);  
        } while (!op.equals("4"));
    }
    
    private static void actividadesClubs(HashMap<Integer, clubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        System.out.print("Ingrese el ID del club: ");
        int idClub = Integer.parseInt(buffer.readLine());
        clubesDeportivos club = clubes.get(idClub);
    
        if (club == null) {
            System.out.println("Club no encontrado.");
            System.out.println("Regresando al menú principal...");
            return;
        }

        System.out.println("Club Seleccionado = " + club.getNombre());

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menú de Gestión de Actividades ---");
            System.out.println("1. Agregar actividad");
            System.out.println("2. Editar actividad");
            System.out.println("3. Eliminar actividad");
            System.out.println("4. Mostrar actividades");
            System.out.println("5. Volver al menú principal\n");
            System.out.print("Seleccione una opción: ");

            
            int opcion = Integer.parseInt(buffer.readLine());
    
            switch (opcion) {
                case 1:
                    actividadesClubes nuevaActividad = new actividadesClubes();
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
    
                    actividadesClubes.agregarActividad(club.getActividades(), nuevaActividad);
                    esperarTecla(buffer);
                    break;
    
                case 2:
                    System.out.print("Ingrese el ID de la actividad a editar: ");
                    int idActividadEditar = Integer.parseInt(buffer.readLine());
                    actividadesClubes.editarActividad(club.getActividades(), idActividadEditar, buffer);
                    esperarTecla(buffer);
                    break;
    
                case 3:
                    System.out.print("Ingrese el ID de la actividad a eliminar: ");
                    int idActividadEliminar = Integer.parseInt(buffer.readLine());
                    actividadesClubes.eliminarActividad(club.getActividades(), idActividadEliminar);
                    esperarTecla(buffer);
                    break;
    
                case 4:
                    actividadesClubes.mostrarActividades(club.getActividades());
                    esperarTecla(buffer);
                    break;
                    
    
                case 5:
                    continuar = false;
                    break;
    
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
    

    
    


    public static void esperarTecla(BufferedReader buffer) throws IOException {
        System.out.println("\nPresione Enter para continuar...");
        buffer.readLine(); 
    }
}
