package Vistaa;

import javax.swing.*;
import Modelo.ActividadesClubes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionActividadesVentana extends JFrame {
    private ArrayList<ActividadesClubes> actividades;  // Lista de actividades

    public GestionActividadesVentana() {
        setTitle("Gestión de Actividades");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton agregarActividadButton = new JButton("Agregar Actividad");
        JButton editarActividadButton = new JButton("Editar Actividad");
        JButton eliminarActividadButton = new JButton("Eliminar Actividad");
        JButton mostrarActividadesButton = new JButton("Mostrar Actividades");

        JPanel panel = new JPanel();
        panel.add(agregarActividadButton);
        panel.add(editarActividadButton);
        panel.add(eliminarActividadButton);
        panel.add(mostrarActividadesButton);

        add(panel);

        // Listeners para las acciones
        agregarActividadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar una nueva actividad
            }
        });

        editarActividadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar una actividad existente
            }
        });

        eliminarActividadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar una actividad
            }
        });

        mostrarActividadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar todas las actividades
            }
        });
    }
}

