package Vistaa;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        // Configuración básica de la ventana
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana

        // Crear botones para las distintas opciones del menú
        JButton gestionarClubesButton = new JButton("Gestionar Clubes");
        JButton gestionarActividadesButton = new JButton("Gestionar Actividades");

        // Panel para organizar los botones
        JPanel panel = new JPanel();
        panel.add(gestionarClubesButton);
        panel.add(gestionarActividadesButton);
        
        // Añadir el panel a la ventana
        add(panel);

        // Acción para el botón de gestionar clubes
        gestionarClubesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de gestión de clubes
                GestionClubesVentana clubesVentana = new GestionClubesVentana();
                clubesVentana.setVisible(true);
            }
        });

        // Acción para el botón de gestionar actividades
        gestionarActividadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de gestión de actividades
                GestionActividadesVentana actividadesVentana = new GestionActividadesVentana();
                actividadesVentana.setVisible(true);
            }
        });
    }
}

