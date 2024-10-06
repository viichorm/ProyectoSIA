package Vistaa;

import javax.swing.*;
import Excepciones.CampoInvalidoException;
import Excepciones.ClubYaExistenteException;
import Modelo.ClubesDeportivos;
import Modelo.ClubesPremium;
import Modelo.GestorPersistencia; // Importar la clase GestorPersistencia
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.awt.*;
import java.util.*;

/**
 * GestionClubesVentana es una clase que proporciona una interfaz gráfica
 * para gestionar clubes deportivos, permitiendo agregar, editar, eliminar
 * y visualizar clubes. Además, se integra con la clase GestorPersistencia 
 * para la persistencia de datos.
 * 
 * Extiende JFrame para utilizar una ventana como base.
 */
public class GestionClubesVentana extends JFrame {
    private HashMap<Integer, ClubesDeportivos> clubes;
    private String archivoClubes = "ArchivosTxt/Clubes.txt";

    /**
     * Constructor para la ventana de gestión de clubes.
     * 
     * @param clubes HashMap que contiene los clubes registrados. 
     */
    public GestionClubesVentana(HashMap<Integer, ClubesDeportivos> clubes) {
        this.clubes = clubes;
        initComponents();
    }

    /**
     * Inicializa los componentes gráficos de la ventana, 
     * incluyendo botones para agregar, editar, eliminar, ver clubes y salir.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        setTitle("Gestión de Clubes Deportivos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);

        JButton agregarClubButton = new JButton("Agregar Club");
        JButton editarClubButton = new JButton("Editar Club");
        JButton eliminarClubButton = new JButton("Eliminar Club");
        JButton mostrarClubesButton = new JButton("Mostrar Club");
        JButton salirButton = new JButton("Salir");

        add(panel);

        // Listeners para cada acción
        agregarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarClub(clubes);
            }
        });

        editarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarClub(clubes);
            }
        });

        eliminarClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarClub(clubes);
            }
        });

        mostrarClubesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verClubes(clubes);
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(agregarClubButton);
        panel.add(editarClubButton);
        panel.add(eliminarClubButton);
        panel.add(mostrarClubesButton);
        panel.add(salirButton);
    }

    /**
     * Método para agregar un nuevo club deportivo o premium.
     * Solicita los datos del club al usuario mediante cuadros de texto y casillas de verificación.
     * 
     * @param clubes HashMap que contiene los clubes registrados.
     */
    private void agregarClub(HashMap<Integer, ClubesDeportivos> clubes) {
        JTextField nombreField = new JTextField();
        JTextField direccionField = new JTextField();
        JTextField idField = new JTextField();
        JTextField sociosField = new JTextField();
        JCheckBox esPremiumCheckBox = new JCheckBox("¿Es un Club Premium?");
        JTextField beneficiosField = new JTextField(); // Campo para los beneficios

        esPremiumCheckBox.addActionListener(e -> {
            beneficiosField.setEnabled(esPremiumCheckBox.isSelected());
        });

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nombre del Club:"));
        panel.add(nombreField);
        panel.add(new JLabel("Dirección del Club:"));
        panel.add(direccionField);
        panel.add(new JLabel("ID del Club:"));
        panel.add(idField);
        panel.add(new JLabel("Socios (separados por comas):"));
        panel.add(sociosField);
        panel.add(esPremiumCheckBox);
        panel.add(new JLabel("Beneficios (si es club premium):"));
        panel.add(beneficiosField);

        beneficiosField.setEnabled(false); 

        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Club", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText().trim();
                String direccion = direccionField.getText().trim();
                String[] socios = sociosField.getText().split(",");
                ArrayList<String> listaSocios = new ArrayList<>();

                for (String socio : socios) {
                    if (!socio.trim().isEmpty()) {
                        listaSocios.add(socio.trim());
                    }
                }

                if (nombre.isEmpty() || direccion.isEmpty()) {
                    throw new CampoInvalidoException("Los campos de nombre y dirección no pueden estar vacíos.");
                }

                int idClub = Integer.parseInt(idField.getText());

                if (idClub <= 0) {
                    throw new CampoInvalidoException("El ID debe ser un número positivo.");
                }

                if (clubes.containsKey(idClub)) {
                    throw new ClubYaExistenteException("El club con ID " + idClub + " ya existe.");
                }

                ClubesDeportivos nuevoClub;
                if (esPremiumCheckBox.isSelected()) {
                    String beneficios = beneficiosField.getText().trim();
                    if (beneficios.isEmpty()) {
                        throw new CampoInvalidoException("Debe ingresar los beneficios si el club es premium.");
                    }
                    nuevoClub = new ClubesPremium(idClub, nombre, direccion, beneficios);
                } else {
                    nuevoClub = new ClubesDeportivos(idClub, nombre, direccion);
                }

                nuevoClub.setSocios(listaSocios);
                clubes.put(idClub, nuevoClub);

                try {
                    GestorPersistencia.guardarClubes(archivoClubes, clubes);
                    JOptionPane.showMessageDialog(null, "Club agregado exitosamente.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el club: " + e.getMessage());
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID del club debe ser un número válido.");
            } catch (CampoInvalidoException | ClubYaExistenteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    /**
     * Método para editar un club existente. Solicita el ID del club al usuario y permite modificar los datos.
     * Si el club es premium, también permite editar los beneficios adicionales.
     * 
     * @param clubes HashMap que contiene los clubes registrados.
     */
    private void editarClub(HashMap<Integer, ClubesDeportivos> clubes) {
        String inputId = JOptionPane.showInputDialog("Ingrese el ID del Club a editar:");
        try {
            int idClub = Integer.parseInt(inputId);
            if (clubes.containsKey(idClub)) {
                ClubesDeportivos club = clubes.get(idClub);

                JTextField nombreField = new JTextField(club.getNombre());
                JTextField direccionField = new JTextField(club.getDireccion());
                JTextField sociosField = new JTextField(String.join(", ", club.getSocios())); 

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Nombre del Club:"));
                panel.add(nombreField);
                panel.add(new JLabel("Dirección del Club:"));
                panel.add(direccionField);
                panel.add(new JLabel("Socios (separados por comas):"));
                panel.add(sociosField);

                // Si es un club premium, permitir editar beneficios
                if (club instanceof ClubesPremium) {
                    JTextField beneficiosField = new JTextField(((ClubesPremium) club).getBeneficiosAdicionales());
                    panel.add(new JLabel("Beneficios Adicionales:"));
                    panel.add(beneficiosField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Editar Club Premium", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        club.setNombre(nombreField.getText());
                        club.setDireccion(direccionField.getText());
                        club.setSocios(new ArrayList<>(Arrays.asList(sociosField.getText().split(", "))));
                        ((ClubesPremium) club).setBeneficiosAdicionales(beneficiosField.getText());
                    }
                } else {
                    int result = JOptionPane.showConfirmDialog(null, panel, "Editar Club", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        club.setNombre(nombreField.getText());
                        club.setDireccion(direccionField.getText());
                        club.setSocios(new ArrayList<>(Arrays.asList(sociosField.getText().split(", "))));
                    }
                }

                try {
                    GestorPersistencia.guardarClubes(archivoClubes, clubes);
                    JOptionPane.showMessageDialog(null, "Club editado exitosamente.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al guardar los cambios: " + e.getMessage());
                }

            } else {
                throw new CampoInvalidoException("No se encontró ningún club con ID " + idClub);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
        } catch (CampoInvalidoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /**
     * Método para eliminar un club existente. Solicita el ID del club al usuario y pide confirmación antes de eliminarlo.
     * 
     * @param clubes HashMap que contiene los clubes registrados.
     */
    private void eliminarClub(HashMap<Integer, ClubesDeportivos> clubes) {
        String inputId = JOptionPane.showInputDialog("Ingrese el ID del Club a eliminar:");
        try {
            int idClub = Integer.parseInt(inputId);
            if (clubes.containsKey(idClub)) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el club con ID " + idClub + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    clubes.remove(idClub);
                    GestorPersistencia.guardarClubes(archivoClubes, clubes);
                    JOptionPane.showMessageDialog(null, "Club eliminado exitosamente.");
                }
            } else {
                throw new CampoInvalidoException("No se encontró ningún club con ID " + idClub);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
        } catch (CampoInvalidoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los cambios: " + e.getMessage());
        }
    }

    /**
     * Método para visualizar los clubes registrados. Muestra una lista de clubes en un cuadro de diálogo.
     * 
     * @param clubes HashMap que contiene los clubes registrados.
     */
    private void verClubes(HashMap<Integer, ClubesDeportivos> clubes) {
        StringBuilder sb = new StringBuilder();
        for (ClubesDeportivos club : clubes.values()) {
            sb.append(club.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Listado de Clubes", JOptionPane.INFORMATION_MESSAGE);
    }
}
