package Vistaa;

import Modelo.ClubesDeportivos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

/**
 * Clase que permite seleccionar un club desde una lista o ingresar su ID para gestionar sus actividades.
 */

public class SeleccionarClubVentana extends JFrame {
    private HashMap<Integer, ClubesDeportivos> clubes;
    private JList<String> clubList;
    private DefaultListModel<String> listModel;
    private JTextField idField;  // Campo para ingresar ID manualmente
    private JButton seleccionarButton;
    private JButton ingresarIdButton; // Botón para seleccionar por ID


        /**
     * Constructor que inicializa la ventana de selección de club.
     *
     * @param clubes Mapa que contiene los clubes deportivos disponibles.
     */

    public SeleccionarClubVentana(HashMap<Integer, ClubesDeportivos> clubes) {
        this.clubes = clubes;
        initComponents();
    }

        /**
     * Método que inicializa y configura los componentes de la interfaz gráfica.
     */

    private void initComponents() {
        setTitle("Seleccionar club al que desea gestionar sus actividades.");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el modelo de la lista y la lista en sí
        listModel = new DefaultListModel<>();
        clubList = new JList<>(listModel);
        clubList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clubList.setVisibleRowCount(10);

        // Llenar el modelo de la lista con los clubes
        mostrarNombresConId();

        // Campo para ingresar ID manualmente
        idField = new JTextField(10);
        JPanel idPanel = new JPanel();
        idPanel.add(new JLabel("O ingrese ID de club:"));
        idPanel.add(idField);

        // Crear botones
        seleccionarButton = new JButton("Seleccionar club con el cursor");
        ingresarIdButton = new JButton("Seleccionar club por ID");

        // Panel para el botón y el campo de ID
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(idPanel, BorderLayout.CENTER);
        panel.add(seleccionarButton, BorderLayout.NORTH);
        panel.add(ingresarIdButton, BorderLayout.SOUTH); // Agregar el nuevo botón

        // Agregar componentes a la ventana con barra de desplazamiento
        JScrollPane scrollPane = new JScrollPane(clubList);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Acción del botón "Seleccionar Club por Cursor"
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Intentar obtener el club seleccionado de la lista
                String seleccion = clubList.getSelectedValue();
                if (seleccion != null) {
                    int clubId = obtenerIdDesdeSeleccion(seleccion);
                    abrirGestionActividadesVentana(clubId);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un club.");
                }
            }
        });

        // Acción del botón "Seleccionar Club por ID"
        ingresarIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idField.getText().isEmpty()) {
                    try {
                        int clubId = Integer.parseInt(idField.getText());
                        abrirGestionActividadesVentana(clubId);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID.");
                }
            }
        });

        setVisible(true);
    }

    /**
     * Método que abre la ventana de gestión de actividades para el club seleccionado.
     *
     * @param clubId ID del club seleccionado.
     */
    
    private void abrirGestionActividadesVentana(int clubId) {
        ClubesDeportivos clubSeleccionado = clubes.get(clubId);
        if (clubSeleccionado != null) {
            // Abrir la ventana de gestión de actividades para el club seleccionado
            GestionActividadesVentana gestionActividadesVentana = new GestionActividadesVentana(clubSeleccionado, clubes);
            gestionActividadesVentana.setVisible(true);
            dispose();  // Cerrar esta ventana al abrir la de gestión
        } else {
            JOptionPane.showMessageDialog(null, "El club con ID " + clubId + " no existe.");
        }
    }

    
        /**
     * Método auxiliar para obtener el ID del club desde la selección en la lista.
     *
     * @param seleccion Cadena seleccionada de la lista.
     * @return ID del club.
     */
    
    private int obtenerIdDesdeSeleccion(String seleccion) {
        // Extraer el ID del club seleccionado en la lista
        String[] partes = seleccion.split(" - ");
        return Integer.parseInt(partes[0].replace("ID: ", "").trim());
    }

    // Método para mostrar nombres e IDs de los clubes en la lista
    private void mostrarNombresConId() {
        listModel.clear();
        if (clubes.isEmpty()) {
            listModel.addElement("No hay clubes registrados.");
            return;
        }

        for (Integer id : clubes.keySet()) {
            ClubesDeportivos club = clubes.get(id);
            listModel.addElement("ID: " + id + " - Nombre: " + club.getNombre());
        }
    }
}