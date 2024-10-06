package Controlador;

import java.io.*;
import java.util.*;

import Modelo.ActividadesClubes;
import Modelo.ClubesDeportivos;
import Modelo.GestorFiltros;
import Modelo.GestorPersistencia;
import Excepciones.ActividadNoEncontradaException;
import Excepciones.ClubNoEncontradoException;
import Excepciones.EntradaInvalidaException;

public class MenusConsola {

    public static void mostrarMenuPrincipal(HashMap<Integer, ClubesDeportivos> clubes) throws IOException, ActividadNoEncontradaException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String op = "";

        try {
            do {
                System.out.println("***** Sistema de Información de Clubes *****");
                System.out.println("********************************************");
                System.out.println("*****    Elija una de las opciones     *****");
                System.out.println("*****   1. Lista de Clubes             *****");
                System.out.println("*****   2. Editar Clubes               *****");
                System.out.println("*****   3. Actividades de un club      *****");
                System.out.println("*****   4. Generar Reporte             *****");
                System.out.println("*****   5. Salir                       *****");
                System.out.println("********************************************");
                System.out.print("Seleccione una opción: ");
                
                try {
                    op = buffer.readLine();
                    int opcion = validarOpcion(op, 1, 5);
                    System.out.println("--- La opción seleccionada es: " + opcion + "\n");
    
                    switch (opcion) {
                        case 1:
                            mostrarSubMenuListaClubes(clubes, buffer);
                            break;

                        case 2:
                            mostrarSubMenuEditarClubes(clubes, buffer);
                            break;

                        case 3:
                            try {
                                actividadesClubs(clubes, buffer);
                            } catch (ClubNoEncontradoException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 4:
                            System.out.print("Introduce el nombre del archivo para el reporte (ej. reporte_clubes.txt): ");
                            try {
                                String nombreArchivo = buffer.readLine();
                                GestorPersistencia.generarReporte(nombreArchivo, clubes);
                            } catch (IOException e) {
                                System.err.println("Error al leer el nombre del archivo: " + e.getMessage());
                            }
                            break;

                        case 5:
                            System.out.println("Saliendo del programa...");
                            break;

                        default:
                            System.out.println("Opción no válida, intente nuevamente.");
                            break;
                    }
                } catch (EntradaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            } while (!op.equals("5"));

        } catch (IOException e) {
            System.out.println("Ocurrió un error de entrada/salida: " + e.getMessage());
        }
    }

    public static void mostrarSubMenuListaClubes(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException {
        String op = "";

        try {
            do {
                System.out.println("*****              Submenú: Lista de Clubes             *****");
                System.out.println("*************************************************************");
                System.out.println("***** 1. Mostrar lista de IDs y Nombres de los clubes   *****");
                System.out.println("***** 2. Mostrar lista completa de clubes y actividades *****");
                System.out.println("***** 3. Filtrar y mostrar actividades por hora         *****");
                System.out.println("***** 4. Regresar al menú principal                     *****");
                System.out.println("*************************************************************");
                System.out.print("Seleccione una opción: ");
                try {
                    op = buffer.readLine();
                    int opcion = validarOpcion(op, 1, 4);
                    System.out.println("--- La opción seleccionada es: " + opcion + "\n");

                    switch (opcion) {
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
                            System.out.print("Ingrese el horario a filtrar: ");
                            String horario = buffer.readLine();
                            ArrayList<ActividadesClubes> actividadesFiltradasHorario = GestorFiltros.filtrarActividadesPorHorario(horario, clubes);
                            GestorFiltros.mostrarActividadesFiltradas(actividadesFiltradasHorario);
                            break;

                        case 4:
                            System.out.println("Regresando al menú principal...");
                            break;

                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                } catch (EntradaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
                esperarTecla(buffer);
            } while (!op.equals("4"));
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    public static void mostrarSubMenuEditarClubes(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException, EntradaInvalidaException {
        String op = "";

        try {
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
                int opcion = validarOpcion(op, 1, 4);
                System.out.println("--- La opción seleccionada es: " + opcion + "\n");

                switch (opcion) {
                    case 1:
                        ClubesDeportivos.agregarClubConsola(clubes, buffer);
                        GestorPersistencia.guardarClubes("ArchivosTxt/Clubes.txt", clubes);
                        break;

                    case 2:
                        ClubesDeportivos.editarClubConsola(clubes, buffer);
                        GestorPersistencia.guardarClubes("ArchivosTxt/Clubes.txt", clubes);
                        break;

                    case 3:
                        ClubesDeportivos.eliminarClubConsola(clubes, buffer);
                        GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
                        GestorPersistencia.guardarClubes("ArchivosTxt/Clubes.txt", clubes);
                        break;

                    case 4:
                        System.out.println("Regresando al menú principal...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                esperarTecla(buffer);
            } while (!op.equals("4"));
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    private static void actividadesClubs(HashMap<Integer, ClubesDeportivos> clubes, BufferedReader buffer) throws IOException, ClubNoEncontradoException, EntradaInvalidaException, ActividadNoEncontradaException {
        System.out.print("Ingrese el ID del club: ");
        try {
            int idClub = Integer.parseInt(buffer.readLine());
            ClubesDeportivos club = clubes.get(idClub);
        
            if (club == null) {
                throw new ClubNoEncontradoException("El club con ID " + idClub + " no fue encontrado.");
            }

            System.out.println("\nClub Seleccionado = " + club.getNombre());
        
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n*****       Menú de Gestión de Actividades       *****");
                System.out.println("******************************************************");
                System.out.println("***** 1. Agregar actividad                         *****");
                System.out.println("***** 2. Editar actividad                          *****");
                System.out.println("***** 3. Eliminar actividad                        *****");
                System.out.println("***** 4. Mostrar actividades                       *****");
                System.out.println("***** 5. Volver al menú principal                  *****");
                System.out.println("******************************************************");
                System.out.print("Seleccione una opción: ");
                
                String opcionStr = buffer.readLine();
                try {
                    int opcion = validarOpcion(opcionStr, 1, 5);
                    switch (opcion) {
                        case 1:
                            ActividadesClubes.agregarActividadConsola(club.getActividades(), buffer);
                            GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
                            break;
                            
                        case 2:
                            ActividadesClubes.editarActividadConsola(club.getActividades(), buffer);
                            GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
                            break;

                        case 3:
                            ActividadesClubes.eliminarActividadConsola(club.getActividades(), buffer);
                            GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
                            break;

                        case 4:
                            ActividadesClubes.mostrarActividadesConsola(club.getActividades());
                            break;

                        case 5:
                            continuar = false;
                            break;

                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                } catch (EntradaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Entrada no válida. Se esperaba un número.");
        }
    }

    public static void esperarTecla(BufferedReader buffer) throws IOException {
        System.out.println("\nPresione Enter para continuar...");
        buffer.readLine(); 
    }

    private static int validarOpcion(String input, int min, int max) throws EntradaInvalidaException {
        try {
            int opcion = Integer.parseInt(input);
            if (opcion < min || opcion > max) {
                throw new EntradaInvalidaException("La opción debe estar entre " + min + " y " + max + ".");
            }
            return opcion;
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Entrada no válida. Se esperaba un número.");
        }
    }
}

