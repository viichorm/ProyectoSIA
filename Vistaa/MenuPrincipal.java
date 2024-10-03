package Vistaa;

import javax.swing.*;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Clubes");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton gestionarClubes = new JButton("Gestionar Clubes");
        JButton gestionarActividades = new JButton("Gestionar Actividades");

        gestionarClubes.addActionListener(e -> new GestionClubesVentana());

        add(gestionarClubes);
        add(gestionarActividades);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
