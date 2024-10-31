package actividad2_5;

import javax.swing.*;
import java.awt.*;

public class VentanaApplet extends JFrame {

    public VentanaApplet() {
        setTitle("EJECUTAR, SUSPENDER Y REANUDAR HILOS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el applet y configurarlo
        Actividad2_5 applet = new Actividad2_5();
        applet.init(); // Llamar a init para inicializar el applet
        applet.start(); // Llamar a start para iniciar el applet

        // Añadir el applet al JFrame
        getContentPane().add(applet, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaApplet ventana = new VentanaApplet();
            ventana.setVisible(true);
        });
    }
}
