package Vistaa;

import javax.swing.*;

import Modelo.ActividadesClubes;
import Modelo.ClubesDeportivos;
import Modelo.ClubesPremium;
import Modelo.GestorFiltros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import javax.swing.text.*;

public class ListaClubesVentana extends JFrame {
    private HashMap<Integer, ClubesDeportivos> clubes;
    private JButton verTodosButton;
    private JButton verNombresButton;
    private JButton filtrarPorHorarioButton;
    private JTextPane textPane; // Cambiar JTextArea a JTextPane

    public ListaClubesVentana(HashMap<Integer, ClubesDeportivos> clubes) {
        this.clubes = clubes; // Asegúrate de que esta línea está presente
        initComponents();
    }

    public void initComponents() {        
        setTitle("Lista de Clubes");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear los botones
        verTodosButton = new JButton("Ver Todos los Datos");
        verNombresButton = new JButton("Ver Nombres e ID");
        filtrarPorHorarioButton = new JButton("Filtrar por Horario"); 

        // Crear el área de texto
        textPane = new JTextPane(); // Cambiar a JTextPane
        textPane.setEditable(false);

        // Agregar botones al panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(verTodosButton);
        buttonPanel.add(verNombresButton);
        buttonPanel.add(filtrarPorHorarioButton);

        // Agregar paneles a la ventana
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textPane), BorderLayout.CENTER); // Cambiar a textPane

        // Acciones de los botones
        verTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodosDatos();
            }
        });

        verNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNombresConId();
            }
        });

        // Acción para el nuevo botón de filtrar por horario
        filtrarPorHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFiltrado();
            }
        });
    }

    // Método para mostrar todos los datos de los clubes
    private void mostrarTodosDatos() {
        textPane.setText(""); // Limpiar el área de texto
        if (clubes.isEmpty()) {
            textPane.setText("No hay clubes registrados.");
            return; // Salir si no hay clubes
        }

        StyledDocument doc = textPane.getStyledDocument(); // Ahora está bien
        SimpleAttributeSet styleNormales = new SimpleAttributeSet();
        StyleConstants.setForeground(styleNormales, Color.BLUE); // Color para clubes normales
        SimpleAttributeSet stylePremium = new SimpleAttributeSet();
        StyleConstants.setForeground(stylePremium, Color.BLUE); // Color para clubes premium

        try {
            // Mostrar clubes normales
            doc.insertString(doc.getLength(), "--- Clubes Normales ---\n", styleNormales);
            boolean tieneNormales = false;

            for (ClubesDeportivos club : clubes.values()) {
                if (!(club instanceof ClubesPremium)) {
                    tieneNormales = true;
                    doc.insertString(doc.getLength(), "ID: " + club.getidClub() + "\n", null);
                    doc.insertString(doc.getLength(), "Nombre: " + club.getNombre() + "\n", null);
                    doc.insertString(doc.getLength(), "Dirección: " + club.getDireccion() + "\n", null);
                    doc.insertString(doc.getLength(), "Actividades: " + club.getActividades() + "\n", null);
                    doc.insertString(doc.getLength(), "Socios: " + club.getSocios() + "\n", null);
                    doc.insertString(doc.getLength(), "---------------------------\n", null);
                }
            }

            if (!tieneNormales) {
                doc.insertString(doc.getLength(), "No hay clubes normales registrados.\n", null);
            }

            // Separador para clubes premium
            doc.insertString(doc.getLength(), "--- Clubes Premium ---\n", stylePremium);

            // Mostrar clubes premium
            boolean tienePremium = false;
            for (ClubesDeportivos club : clubes.values()) {
                if (club instanceof ClubesPremium) {
                    tienePremium = true;
                    ClubesPremium clubPremium = (ClubesPremium) club;
                    doc.insertString(doc.getLength(), "ID: " + clubPremium.getidClub() + "\n", null);
                    doc.insertString(doc.getLength(), "Nombre: " + clubPremium.getNombre() + "\n", null);
                    doc.insertString(doc.getLength(), "Dirección: " + clubPremium.getDireccion() + "\n", null);
                    doc.insertString(doc.getLength(), "Actividades: " + clubPremium.getActividades() + "\n", null);
                    doc.insertString(doc.getLength(), "Socios: " + clubPremium.getSocios() + "\n", null);
                    doc.insertString(doc.getLength(), "Beneficios Adicionales: " + clubPremium.getBeneficiosAdicionales() + "\n", null);
                    doc.insertString(doc.getLength(), "---------------------------\n", null);
                }
            }

            if (!tienePremium) {
                doc.insertString(doc.getLength(), "No hay clubes premium registrados.\n", null);
            }
        } catch (BadLocationException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
    // Método para mostrar solo nombres e IDs de los clubes
    private void mostrarNombresConId() {
        textPane.setText(""); // Limpiar el área de texto
        if (clubes.isEmpty()) {
            textPane.setText("No hay clubes registrados.");
            return; // Salir si no hay clubes
        }

        StyledDocument doc = textPane.getStyledDocument(); // Obtener el documento
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setForeground(style, Color.BLACK); // Color para los nombres de los clubes

        try {
            for (Integer id : clubes.keySet()) {
                ClubesDeportivos club = clubes.get(id);
                doc.insertString(doc.getLength(), "ID: " + id + " - Nombre: " + club.getNombre() + "\n", style);
            }
        } catch (BadLocationException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Método para filtrar actividades por horario
    private void mostrarFiltrado() {
        String horario = JOptionPane.showInputDialog(this, "Introduce el horario para filtrar (ej. 1300):");
        if (horario != null && !horario.trim().isEmpty()) {
            ArrayList<ActividadesClubes> actividadesFiltradas = GestorFiltros.filtrarActividadesPorHorario(horario, clubes);
            textPane.setText(""); // Limpiar el área de texto

            if (actividadesFiltradas.isEmpty()) {
                textPane.setText("No se encontraron actividades para el horario especificado.");
            } else {
                StyledDocument doc = textPane.getStyledDocument(); // Obtener el documento
                SimpleAttributeSet styleClub = new SimpleAttributeSet();
                StyleConstants.setForeground(styleClub, Color.BLUE); // Color para el nombre del club
                SimpleAttributeSet styleActividad = new SimpleAttributeSet();
                StyleConstants.setForeground(styleActividad, Color.BLACK); // Color para la actividad
                SimpleAttributeSet style = new SimpleAttributeSet();
                StyleConstants.setForeground(style, Color.MAGENTA);
                try {
                    doc.insertString(doc.getLength(), "--- Actividades Filtradas ---\n", style);
                    for (ActividadesClubes actividad : actividadesFiltradas) {
                        // Buscar el club que contiene esta actividad
                        String nombreClub = "";
                        for (ClubesDeportivos club : clubes.values()) {
                            if (club.getActividades().contains(actividad)) {
                                nombreClub = club.getNombre(); // Encontrar el club al que pertenece la actividad
                                break;
                            }
                        }
                        // Mostrar el club y la actividad
                        doc.insertString(doc.getLength(), "Club: " + nombreClub + "\n", styleClub);
                        doc.insertString(doc.getLength(), "ID: " + actividad.getidActividad() + " - Nombre: " + actividad.getActividad() + "\n", styleActividad);
                        doc.insertString(doc.getLength(), "Descripción: " + actividad.getDescripcion() + "\n", null);
                        doc.insertString(doc.getLength(), "Horario: " + actividad.getHorario() + "\n", null);
                        doc.insertString(doc.getLength(), "Lugar: " + actividad.getLugar() + "\n", null);
                        doc.insertString(doc.getLength(), "---------------------------\n", null);
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace(); // Manejo de excepciones
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Horario inválido. Intenta de nuevo.");
        }
    }

}