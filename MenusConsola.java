import java.io.*;
import java.util.*;

public class MenusConsola {

    public static void mostrarMenuPrincipal(HashMap<Integer, ClubesDeportivos> clubes) throws IOException {
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
            System.out.print("Seleccione una opción: ");
            op = buffer.readLine();
            System.out.println("--- La opción seleccionada es: " + op + "\n");

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

    public static void mostrarSubMenuListaClubes(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        String op = "";

        do {
            System.out.println("*****              Submenú: Lista de Clubes             *****");
            System.out.println("*************************************************************");
            System.out.println("***** 1. Mostrar lista de IDs y Nombres de los clubes   *****");
            System.out.println("***** 2. Mostrar lista completa de clubes y actividades *****");
            System.out.println("***** 3. Regresar al menú principal                     *****");
            System.out.println("*************************************************************");
            System.out.print("Seleccione una opción: ");
            op = buffer.readLine();
            System.out.println("--- La opción seleccionada es: " + op + "\n");

            switch (Integer.parseInt(op)) {
                case 1:
                    System.out.println("Lista de IDs de los Clubes:");
                    for (Integer id : clubes.keySet()) {
                        ClubesDeportivos club = clubes.get(id);
                        System.out.println("ID del Club: " + club.getidClub() + " || Nombre: " + club.getNombre());
                    }
                    break;

                case 2:
                    for (Integer id : clubes.keySet()) {
                        ClubesDeportivos club = clubes.get(id);
                        System.out.println("\nClub ID: " + club.getidClub());
                        System.out.println("Nombre: " + club.getNombre());
                        System.out.println("Dirección: " + club.getDireccion());
                        System.out.println("Actividades:");
                        if (club.getActividades().isEmpty()) {
                            System.out.println("  - No hay actividades");
                        } else {
                            for (ActividadesClubes actividad : club.getActividades()) {
                                System.out.println("  - " + actividad.getActividad() + " en " + actividad.getLugar() + " a las " + actividad.getHorario());
                            }
                        }
                        System.out.println("Socios: " + club.getSocios());
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
            esperarTecla(buffer);  
        } while (!op.equals("3"));
    }

    public static void mostrarSubMenuEditarClubes(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        String op = "";

        do {
            System.out.println("*****     Submenú: Editar Clubes    *****");
            System.out.println("*****************************************");
            System.out.println("***** 1. Agregar Club               *****");
            System.out.println("***** 2. Editar Club                *****");
            System.out.println("***** 3. Eliminar Club              *****");
            System.out.println("***** 4. Regresar al menú principal *****");
            System.out.println("*****************************************");
            System.out.print("Seleccione una opción: ");
            op = buffer.readLine();
            System.out.println("--- La opción seleccionada es: " + op + "\n");

            switch (Integer.parseInt(op)) {
                case 1:
                    ClubesDeportivos.agregarClub(clubes, buffer);
                    
                    break;

                case 2:
                    ClubesDeportivos.editarClub(clubes, buffer);
                    
                    break;

                case 3:
                    ClubesDeportivos.eliminarClub(clubes, buffer);
                    
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
    
    private static void actividadesClubs(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        System.out.print("Ingrese el ID del club: ");
        int idClub = Integer.parseInt(buffer.readLine());
        ClubesDeportivos club = clubes.get(idClub);
    
        if (club == null) {
            System.out.println("Club no encontrado.");
            System.out.println("Regresando al menú principal...");
            return;
        }

        System.out.println("\nClub Seleccionado = " + club.getNombre());

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n*****       Menú de Gestión de Actividades       *****");
            System.out.println("******************************************************");
            System.out.println("***** 1. Agregar actividad                      *****");
            System.out.println("***** 2. Editar actividad                       *****");
            System.out.println("***** 3. Eliminar actividad                     *****");
            System.out.println("***** 4. Mostrar actividades                    *****");
            System.out.println("***** 5. Volver al menú principal               *****");
            System.out.println("******************************************************");
            System.out.print("Seleccione una opción: ");

            
            int opcion = Integer.parseInt(buffer.readLine());
    
            switch (opcion) {
                case 1:
                    ActividadesClubes nuevaActividad = new ActividadesClubes();
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
    
                    ActividadesClubes.agregarActividad(club.getActividades(), nuevaActividad);
                    esperarTecla(buffer);
                    break;
    
                case 2:
                    System.out.print("Ingrese el ID de la actividad a editar: ");
                    int idActividadEditar = Integer.parseInt(buffer.readLine());
                    ActividadesClubes.editarActividad(club.getActividades(), idActividadEditar, buffer);
                    esperarTecla(buffer);
                    break;
    
                case 3:
                    System.out.print("Ingrese el ID de la actividad a eliminar: ");
                    int idActividadEliminar = Integer.parseInt(buffer.readLine());
                    ActividadesClubes.eliminarActividad(club.getActividades(), idActividadEliminar);
                    esperarTecla(buffer);
                    break;
    
                case 4:
                    ActividadesClubes.mostrarActividades(club.getActividades());
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
