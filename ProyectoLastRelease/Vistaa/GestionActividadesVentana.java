package Vistaa;

import javax.swing.*;
import Modelo.ClubesDeportivos;
import Modelo.ActividadesClubes;
import Modelo.ActividadesExtraDeportivas;
import Modelo.GestorPersistencia; // Importar la clase GestorPersistencia
import java.io.IOException;
import java.awt.*;
import java.util.*;
import javax.swing.text.*;
import Excepciones.ActividadNoEncontradaException;

/**
 * Clase GestionActividadesVentana. Representa una ventana para gestionar actividades 
 * de un club deportivo, permitiendo agregar, editar, eliminar y mostrar actividades.
 */
public class GestionActividadesVentana extends JFrame {
    private JButton agregarActividadButton;
    private JButton eliminarActividadButton;
    private JButton mostrarActividadesButton;
    private JButton editarActividadButton;
    private JButton salirButton;
    private JTextPane textArea;
    private ClubesDeportivos club;
    private HashMap<Integer, ClubesDeportivos> clubes;
    private ArrayList<ActividadesClubes> actividadesClub;

    /**
     * Constructor de GestionActividadesVentana.
     * Inicializa la ventana con un club seleccionado y un mapa de clubes.
     *
     * @param clubSeleccionado El club deportivo seleccionado.
     * @param clubes Mapa de clubes deportivos.
     */
    public GestionActividadesVentana(ClubesDeportivos clubSeleccionado, HashMap<Integer, ClubesDeportivos> clubes) {
        this.club = clubSeleccionado;
        this.clubes = clubes;
        this.actividadesClub = clubSeleccionado.getActividades();
        initComponents();
    }

    /**
     * Inicializa los componentes gráficos de la ventana.
     */
    private void initComponents() {
        setTitle("Gestión de Actividades: " + club.getNombre());
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear botones
        agregarActividadButton = new JButton("Agregar Actividad");
        editarActividadButton = new JButton("Editar Actividad");
        eliminarActividadButton = new JButton("Eliminar Actividad");
        mostrarActividadesButton = new JButton("Mostrar Actividades");
        salirButton = new JButton("Salir");

        // Crear área de texto
        textArea = new JTextPane();
        textArea.setEditable(false);
        
        // Agregar panel para botones
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(agregarActividadButton);
        panel.add(editarActividadButton);
        panel.add(eliminarActividadButton);
        panel.add(mostrarActividadesButton);
        panel.add(salirButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Listeners para las acciones
        agregarActividadButton.addActionListener(e -> {
            try {
                agregarActividad();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        editarActividadButton.addActionListener(e -> {
            try {
                editarActividad();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        eliminarActividadButton.addActionListener(e -> {
            try {
                eliminarActividad();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        mostrarActividadesButton.addActionListener(e -> mostrarActividades());

        salirButton.addActionListener(e -> dispose());
    }

        /**
     * Muestra un diálogo para agregar una nueva actividad al club seleccionado.
     * Permite agregar tanto actividades deportivas como extra deportivas.
     *
     * @throws IOException Si ocurre un error al guardar las actividades en el archivo.
     */

private void agregarActividad() throws IOException {
        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();
        JTextField horarioField = new JTextField();
        JTextField idField = new JTextField();
        JTextField lugarField = new JTextField();
        JCheckBox esExtraDeportivaCheck = new JCheckBox("Es actividad extra deportiva");
        JTextField tipoExtraField = new JTextField();
        tipoExtraField.setEnabled(false); // Se habilitará solo si se marca la casilla
        
        // Habilitar el campo del tipo de actividad extra deportiva si se selecciona la casilla
        esExtraDeportivaCheck.addActionListener(e -> tipoExtraField.setEnabled(esExtraDeportivaCheck.isSelected()));
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nombre de la Actividad:"));
        panel.add(nombreField);
        panel.add(new JLabel("Descripción de la Actividad:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Horario de la Actividad:"));
        panel.add(horarioField);
        panel.add(new JLabel("ID de la Actividad:"));
        panel.add(idField);
        panel.add(new JLabel("Lugar de la Actividad:"));
        panel.add(lugarField);
        panel.add(esExtraDeportivaCheck);
        panel.add(new JLabel("Tipo de Actividad Extra Deportiva:"));
        panel.add(tipoExtraField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Actividad", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int idActividad = Integer.parseInt(idField.getText());
                String nombre = nombreField.getText();
                String descripcion = descripcionField.getText();
                String horario = horarioField.getText();
                String lugar = lugarField.getText();
    
                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || descripcion.isEmpty() || horario.isEmpty() || lugar.isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos deben estar completos.");
                }
    
                ActividadesClubes nuevaActividad;
                if (esExtraDeportivaCheck.isSelected()) {
                    String tipoExtraDeportivo = tipoExtraField.getText();
                    if (tipoExtraDeportivo.isEmpty()) {
                        throw new IllegalArgumentException("Debe especificar el tipo de actividad extra deportiva.");
                    }
                    nuevaActividad = new ActividadesExtraDeportivas(idActividad, nombre, horario, tipoExtraDeportivo);
                } else {
                    nuevaActividad = new ActividadesClubes(idActividad, nombre, horario);
                }
    
                nuevaActividad.agregarDescripcion(descripcion);
                nuevaActividad.agregarLugar(lugar);
    
                ActividadesClubes.agregarActividad(actividadesClub, nuevaActividad);
                GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes); // Guarda todas las actividades de todos los clubes
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID de la actividad debe ser un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Muestra un diálogo para editar una actividad existente, buscando por su ID.
     *
     * @throws IOException Si ocurre un error al guardar las actividades en el archivo.
     */

    private void editarActividad() throws IOException {
        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID de la Actividad a Editar:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Buscar Actividad", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int idActividad = Integer.parseInt(idField.getText());
                ActividadesClubes actividad = buscarActividadPorId(idActividad);

                if (actividad != null) {
                    JTextField nombreField = new JTextField(actividad.getActividad());
                    JTextField descripcionField = new JTextField(actividad.getDescripcion());
                    JTextField horarioField = new JTextField(actividad.getHorario());
                    JTextField lugarField = new JTextField(actividad.getLugar());

                    panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Nuevo Nombre:"));
                    panel.add(nombreField);
                    panel.add(new JLabel("Nueva Descripción:"));
                    panel.add(descripcionField);
                    panel.add(new JLabel("Nuevo Horario:"));
                    panel.add(horarioField);
                    panel.add(new JLabel("Nuevo Lugar:"));
                    panel.add(lugarField);

                    result = JOptionPane.showConfirmDialog(null, panel, "Editar Actividad", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        actividad.setActividad(nombreField.getText());
                        actividad.agregarDescripcion(descripcionField.getText());
                        actividad.setHorario(horarioField.getText());
                        actividad.agregarLugar(lugarField.getText());

                        GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
                        JOptionPane.showMessageDialog(null, "Actividad editada exitosamente.");
                    }
                } else {
                    throw new ActividadNoEncontradaException("No se encontró una actividad con ese ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID de la actividad debe ser un número válido.");
            } catch (ActividadNoEncontradaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    
        /**
     * Busca una actividad por su ID dentro de la lista de actividades del club.
     *
     * @param idActividad El ID de la actividad a buscar.
     * @return La actividad encontrada, o null si no existe.
     */

    private ActividadesClubes buscarActividadPorId(int idActividad) {
        for (ActividadesClubes actividad : actividadesClub) {
            if (actividad.getidActividad() == idActividad) {
                return actividad;
            }
        }
        return null;
    }

        /**
     * Muestra un cuadro de diálogo para eliminar una actividad existente del club.
     *
     * @throws IOException Si ocurre un error al guardar las actividades actualizadas.
     */
    
    private void eliminarActividad() throws IOException {
        // Panel para pedir el ID de la actividad a eliminar
        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID de la Actividad a Eliminar:"));
        panel.add(idField);
    
        // Mostrar el cuadro de diálogo para ingresar el ID de la actividad
        int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Actividad", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                int idActividad = Integer.parseInt(idField.getText());
    
                // Buscar la actividad por ID
                boolean actividadEncontrada = false;
                ActividadesClubes actividadAEliminar = null;
    
                for (ActividadesClubes actividad : actividadesClub) {
                    if (actividad.getidActividad() == idActividad) {
                        actividadAEliminar = actividad;
                        actividadEncontrada = true;
                        break;
                    }
                }
    
                if (actividadEncontrada && actividadAEliminar != null) {
                    // Mostrar los datos de la actividad a eliminar
                    String mensaje = "¿Está seguro que desea eliminar la actividad?\n\n" +
                                     "ID: " + actividadAEliminar.getidActividad() + "\n" +
                                     "Nombre: " + actividadAEliminar.getActividad() + "\n" +
                                     "Descripción: " + actividadAEliminar.getDescripcion() + "\n" +
                                     "Horario: " + actividadAEliminar.getHorario() + "\n" +
                                     "Lugar: " + actividadAEliminar.getLugar();
    
                    int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    
                    // Si el usuario confirma la eliminación
                    if (opcion == JOptionPane.YES_OPTION) {
                        actividadesClub.remove(actividadAEliminar); // Eliminar la actividad
                        try {
                            // Guardar las actividades actualizadas
                            GestorPersistencia.guardarActividades("ArchivosTxt/ActividadesClubes.txt", clubes);
                            JOptionPane.showMessageDialog(null, "Actividad eliminada exitosamente.");
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Error al guardar los cambios: " + e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La actividad no fue eliminada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró una actividad con ese ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID de la actividad debe ser un número válido.");
            }
        }
    }

        /**
     * Muestra todas las actividades del club, diferenciando entre actividades deportivas 
     * y extra deportivas. Las actividades se muestran en colores diferentes.
     */
    
    private void mostrarActividades() {
        textArea.setText(""); // Limpiar el área de texto
        ArrayList<ActividadesClubes> actividades = club.getActividades(); // Asegúrate de que este método exista

        if (actividades.isEmpty()) {
            textArea.setText("No hay actividades registradas para este club.");
        } else {
            StyledDocument doc = textArea.getStyledDocument();
            SimpleAttributeSet styleDeportivas = new SimpleAttributeSet();
            StyleConstants.setForeground(styleDeportivas, Color.BLUE); // Color para actividades deportivas
            SimpleAttributeSet styleExtraDeportivas = new SimpleAttributeSet();
            StyleConstants.setForeground(styleExtraDeportivas, Color.RED); // Color para actividades extra deportivas

            try {
                // Mostrar actividades deportivas
                doc.insertString(doc.getLength(), "--- Actividades Deportivas ---\n", styleDeportivas);
                boolean tieneDeportivas = false;

                for (ActividadesClubes actividad : actividades) {
                    if (!(actividad instanceof ActividadesExtraDeportivas)) {
                        tieneDeportivas = true;
                        doc.insertString(doc.getLength(), "ID: " + actividad.getidActividad() + " - Nombre: " + actividad.getActividad() + "\n", null);
                        doc.insertString(doc.getLength(), "Descripción: " + actividad.getDescripcion() + "\n", null);
                        doc.insertString(doc.getLength(), "Horario: " + actividad.getHorario() + "\n", null);
                        doc.insertString(doc.getLength(), "Lugar: " + actividad.getLugar() + "\n", null);
                        doc.insertString(doc.getLength(), "---------------------------\n", null);
                    }
                }

                if (!tieneDeportivas) {
                    doc.insertString(doc.getLength(), "No hay actividades deportivas registradas.\n", null);
                }

                // Separador para actividades extra deportivas
                doc.insertString(doc.getLength(), "--- Actividades Extra Deportivas ---\n", styleExtraDeportivas);

                // Mostrar actividades extra deportivas
                boolean tieneExtraDeportivas = false;
                for (ActividadesClubes actividad : actividades) {
                    if (actividad instanceof ActividadesExtraDeportivas) {
                        tieneExtraDeportivas = true;
                        ActividadesExtraDeportivas extra = (ActividadesExtraDeportivas) actividad;
                        doc.insertString(doc.getLength(), "ID: " + extra.getidActividad() + " - Nombre: " + extra.getActividad() + "\n", null);
                        doc.insertString(doc.getLength(), "Descripción: " + extra.getDescripcion() + "\n", null);
                        doc.insertString(doc.getLength(), "Horario: " + extra.getHorario() + "\n", null);
                        doc.insertString(doc.getLength(), "Lugar: " + extra.getLugar() + "\n", null);
                        doc.insertString(doc.getLength(), "---------------------------\n", null);
                    }
                }

                if (!tieneExtraDeportivas) {
                    doc.insertString(doc.getLength(), "No hay actividades extra deportivas registradas.\n", null);
                }
            } catch (BadLocationException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }
    }    
}
