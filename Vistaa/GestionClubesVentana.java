package Vistaa;

import Modelo.ClubesDeportivos;
import Modelo.GestorPersistencia;
import Excepciones.ClubYaExistenteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GestionClubesVentana extends JFrame {
    private JTextField idField, nombreField, direccionField, idEliminarField;
    private JButton agregarButton, editarButton, eliminarButton, mostrarButton;
    private JTextArea displayArea;
    private HashMap<Integer, ClubesDeportivos> clubes;
    private String archivoClubes = "ArchivosTxt/Clubes.txt";

    public GestionClubesVentana(HashMap<Integer, ClubesDeportivos> clubes) {
        this.clubes = clubes;

        setTitle("Gestión de Clubes");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entradas
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("ID Club:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        inputPanel.add(direccionField);

        inputPanel.add(new JLabel("ID Club a Eliminar:"));
        idEliminarField = new JTextField();
        inputPanel.add(idEliminarField);

        add(inputPanel, BorderLayout.NORTH);

        // Botones de acción
        agregarButton = new JButton("Agregar Club");
        editarButton = new JButton("Editar Club");
        eliminarButton = new JButton("Eliminar Club");
        mostrarButton = new JButton("Mostrar Clubes");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(mostrarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Área para mostrar información de los clubes
        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Listeners de los botones
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarClub();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarClub();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarClub();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarClubes();
            }
        });

        setVisible(true);
    }

    private void agregarClub() {
        int idClub = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();

        try {
            // Verifica si el club ya existe
            if (clubes.containsKey(idClub)) {
                throw new ClubYaExistenteException("El club con ID " + idClub + " ya existe.");
            }

            ClubesDeportivos nuevoClub = new ClubesDeportivos(idClub, nombre, direccion);
            clubes.put(idClub, nuevoClub);
            GestorPersistencia.guardarClubes(archivoClubes, clubes);
            displayArea.append("Club agregado: " + nuevoClub + "\n");
        } catch (ClubYaExistenteException | IOException e) {
            displayArea.append(e.getMessage() + "\n");
        }
    }

    private void editarClub() {
        int idClub = Integer.parseInt(idField.getText());

        if (clubes.containsKey(idClub)) {
            ClubesDeportivos club = clubes.get(idClub);
            String nuevoNombre = nombreField.getText();
            String nuevaDireccion = direccionField.getText();

            if (!nuevoNombre.isEmpty()) {
                club.setNombre(nuevoNombre);
            }
            if (!nuevaDireccion.isEmpty()) {
                club.setDireccion(nuevaDireccion);
            }

            try {
                GestorPersistencia.guardarClubes(archivoClubes, clubes);
                displayArea.append("Club editado: " + club + "\n");
            } catch (IOException e) {
                displayArea.append("Error al guardar los cambios: " + e.getMessage() + "\n");
            }
        } else {
            displayArea.append("El club con ID " + idClub + " no existe.\n");
        }
    }

    private void eliminarClub() {
        int idClub = Integer.parseInt(idEliminarField.getText());

        if (clubes.containsKey(idClub)) {
            clubes.remove(idClub);
            try {
                GestorPersistencia.guardarClubes(archivoClubes, clubes);
                displayArea.append("Club eliminado con ID: " + idClub + "\n");
            } catch (IOException e) {
                displayArea.append("Error al guardar los cambios: " + e.getMessage() + "\n");
            }
        } else {
            displayArea.append("El club con ID " + idClub + " no existe.\n");
        }
    }

    private void mostrarClubes() {
        displayArea.setText(""); // Limpiar el área de texto
        for (ClubesDeportivos club : clubes.values()) {
            displayArea.append(club.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        // Simulación de la carga de clubes para la prueba
        HashMap<Integer, ClubesDeportivos> clubes = new HashMap<>();
        
        // Cargar clubes desde el archivo si es necesario (opcional)
        try (BufferedReader br = new BufferedReader(new FileReader("ArchivosTxt/Clubes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split("\\|");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String direccion = datos[2];
                ClubesDeportivos club = new ClubesDeportivos(id, nombre, direccion);
                clubes.put(id, club);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar clubes desde el archivo: " + e.getMessage());
        }

        new GestionClubesVentana(clubes);
    }
}
