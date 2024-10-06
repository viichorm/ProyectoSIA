package Vistaa;
import javax.swing.*;

import Controlador.CargarArchivo;
import Modelo.ClubesDeportivos;
import Modelo.GestorPersistencia;


import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;




public class MenuPrincipal extends JFrame {
    private HashMap<Integer, ClubesDeportivos> clubes;

    public MenuPrincipal(HashMap<Integer, ClubesDeportivos> clubes) {
        // Aplicar el Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.clubes = clubes;
        initCompontents();
    }
    private void initCompontents(){
        // Configuración ventana principal
        setTitle("SISTEMA DE GESTION DE CLUBES DEPORTIVOS");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana
        
        // Crear panel para los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1,10,10));
        
         // Crear un label de bienvenida
         JLabel welcomeLabel = new JLabel("SISTEMA DE GESTION DE ACTIVIDADES PARA CLUBES DEPORTIVOS", SwingConstants.CENTER);
         welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
         panel.add(welcomeLabel);

        // Crear botones para las distintas opciones del menú
        JButton listaClubesButton = new JButton("Lista de Clubes");
        JButton gestionarClubesButton = new JButton("Gestion de Clubes");
        JButton gestionarActividadesButton = new JButton("Gestión de Actividades de un Club");
        JButton generarReporteButton = new JButton("Generar Reporte");
        JButton exitButton = new JButton("Salir");

        // Añadir botones al panel
        panel.add(listaClubesButton);
        panel.add(gestionarClubesButton);
        panel.add(gestionarActividadesButton);
        panel.add(generarReporteButton);
        panel.add(exitButton);
        
        // Añadir el panel a la ventana
        add(panel);


        //Accion para el boton de lista clubes
        listaClubesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ListaClubesVentana listaVentana = new ListaClubesVentana(clubes);
                listaVentana.setVisible(true);
            }
        });

        // Acción para el botón de gestionar clubes
        gestionarClubesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de gestión de clubes
                GestionClubesVentana clubesVentana = new GestionClubesVentana(clubes);
                clubesVentana.setVisible(true);
            }
        });

        // Acción para el botón de gestionar actividades
        gestionarActividadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de gestión de actividades
                new SeleccionarClubVentana(clubes);
            }
        });

        // Accion para generar el reporte
// Acción para generar el reporte
generarReporteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Crear un HashMap para almacenar los clubes
        HashMap<Integer, ClubesDeportivos> clubes = null;
        
        // Cargar clubes desde el archivo
        try {
            clubes = CargarArchivo.cargarClubes("ArchivosTxt/Clubes.txt");
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al cargar los clubes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir si hubo un error
        }

        // Cargar las actividades de los clubes
        try {
            CargarArchivo.cargarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al cargar las actividades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir si hubo un error
        }

        // Solicitar el nombre del archivo para el reporte
        String nombreArchivo = JOptionPane.showInputDialog(null, "Introduce el nombre del archivo para el reporte (ej. reporte_clubes.txt):");

        if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
            // Asegurarse de que el nombre del archivo tenga la extensión .txt
            if (!nombreArchivo.toLowerCase().endsWith(".txt")) {
                nombreArchivo += ".txt"; // Agregar .txt si no está presente
            }

            try {
                // Llamar al método generarReporte
                GestorPersistencia.generarReporte(nombreArchivo, clubes);
                
                // Abrir el archivo automáticamente después de generarlo
                File archivo = new File(nombreArchivo);
                if (archivo.exists()) {
                    Desktop.getDesktop().open(archivo);
                } else {
                    System.err.println("El archivo no se encontró después de generarlo.");
                    JOptionPane.showMessageDialog(null, "El archivo no se encontró después de generarlo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                System.err.println("Error al generar o abrir el reporte: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al generar o abrir el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedOperationException ex) {
                System.err.println("El sistema no soporta la operación de abrir archivos: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "El sistema no soporta la operación de abrir archivos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.err.println("Nombre de archivo inválido.");
            JOptionPane.showMessageDialog(null, "Nombre de archivo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});
    // Acción para el botón de salir
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Cerrar la aplicación
            }
        }
    });
    }
}

