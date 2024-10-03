package Vistaa;

import javax.swing.*;
import Modelo.ClubesDeportivos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GestionClubesVentana extends JFrame {
    private HashMap<Integer, ClubesDeportivos> clubes;  // Lista de clubes

    public GestionClubesVentana() {
        setTitle("Gestión de Clubes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Para cerrar solo esta ventana
        setLocationRelativeTo(null);

        JButton agregarClubButton = new JButton("Agregar Club");
        JButton editarClubButton = new JButton("Editar Club");
        JButton eliminarClubButton = new JButton("Eliminar Club");
        JButton mostrarClubButton = new JButton("Mostrar Club");

        JPanel panel = new JPanel();
        panel.add(agregarClubButton);
        panel.add(editarClubButton);
        panel.add(eliminarClubButton);
        panel.add(mostrarClubButton);

        add(panel);

        // Listeners para cada acción
        agregarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir ventana de agregar club
            }
        });

        editarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir ventana de editar club
            }
        });

        eliminarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir ventana de eliminar club
            }
        });

        mostrarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir ventana de mostrar club
            }
        });
    }
}
