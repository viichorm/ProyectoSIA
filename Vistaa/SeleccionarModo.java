package Vistaa;

import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import Controlador.CargarArchivo;
import Modelo.ClubesDeportivos;

public class SeleccionarModo extends JFrame {
    @SuppressWarnings("unused")
    private HashMap<Integer, ClubesDeportivos> clubes;  // Declarar el HashMap de clubes

    // Constructor que recibe el HashMap de clubes
    public SeleccionarModo(HashMap<Integer, ClubesDeportivos> clubes) {
        this.clubes = clubes;  // Asignar el HashMap al atributo de la clase

        // Configuración de la ventana
        setTitle("Seleccionar Modo de Ejecución");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear panel y botones
        JPanel panel = new JPanel();
        JButton consolaButton = new JButton("Ejecutar en Consola");
        JButton ventanaButton = new JButton("Ejecutar en Ventana");

        // Añadir botones al panel
        panel.add(consolaButton);
        panel.add(ventanaButton);
        add(panel);

        // Acción para el botón de ejecutar en consola
        consolaButton.addActionListener(e -> {
            try {
                // Llamar al método para ejecutar el menú de consola con los clubes cargados
                Controlador.MenusConsola.mostrarMenuPrincipal(clubes);
                // Cerrar la ventana actual
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Acción para el botón de ejecutar en ventana
        ventanaButton.addActionListener(e -> {
            // Mostrar la ventana principal del sistema pasando los clubes cargados
            new MenuPrincipal(clubes).setVisible(true);
            // Cerrar la ventana de selección
            dispose();
        });
    }

    public static void main(String[] args) {
        try {
            // Cargar clubes desde el archivo "Clubes.txt"
            HashMap<Integer, ClubesDeportivos> clubes = CargarArchivo.cargarClubes("ArchivosTxt/Clubes.txt");

            // Cargar actividades y asociarlas a los clubes desde el archivo "Actividades.txt"
            CargarArchivo.cargarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);

            // Mostrar ventana de selección de modo
            SwingUtilities.invokeLater(() -> {
                SeleccionarModo seleccionModo = new SeleccionarModo(clubes);
                seleccionModo.setVisible(true); // Mostrar la ventana de selección
            });

        } catch (IOException e) {
            System.out.println("Error al cargar los archivos: " + e.getMessage());
        }
    }
}
