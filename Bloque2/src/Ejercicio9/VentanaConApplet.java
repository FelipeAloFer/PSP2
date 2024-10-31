package Ejercicio9;

import javax.swing.*;
import java.awt.*;

public class VentanaConApplet extends JFrame {

    public VentanaConApplet() {
        setTitle("EJECUTAR, SUSPENDER Y REANUDAR HILOS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el applet y configurarlo
        Ejercicio9 applet = new Ejercicio9();
        applet.init(); // Llamar a init para inicializar el applet
        applet.start(); // Llamar a start para iniciar el applet

        // Añadir el applet al JFrame
        getContentPane().add(applet, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaConApplet ventana = new VentanaConApplet();
            ventana.setVisible(true);
        });
    }
}
