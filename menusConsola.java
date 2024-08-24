import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
            System.out.println("*****   3. Salir                       *****");
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
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        } while (!op.equals("3"));
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
    public static void esperarTecla(BufferedReader buffer) throws IOException {
        System.out.println("\nPresione Enter para continuar...");
        buffer.readLine(); 
    }
}
