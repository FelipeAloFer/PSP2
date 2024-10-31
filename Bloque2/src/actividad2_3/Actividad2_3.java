package actividad2_3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_3 extends Applet implements ActionListener {
    private Thread hilo1, hilo2; // Definimos dos hilos
    long CONTADOR1 = 0; // Variable para llevar el conteo del hilo 1
    long CONTADOR2 = 0; // Variable para llevar el conteo del hilo 2
    private boolean pararHilo1, pararHilo2; // Bandera para detener los hilos
    private Font fuente; // Fuente de texto para mostrar los contadores
    private Button b1, b2; // Botones para controlar los hilos

    public void init() {
        setBackground(Color.pink); // Establece el color de fondo del applet
        add(b1 = new Button("Finalizar hilo 1")); // Crea el botón para el hilo 1
        b1.addActionListener(this); // Añade el ActionListener al botón
        add(b2 = new Button("Finalizar hilo 2")); // Crea el botón para el hilo 2
        b2.addActionListener(this); // Añade el ActionListener al botón
        fuente = new Font("Verdana", Font.BOLD, 26); // Define la fuente para el texto

        // Inicializamos el primer hilo con un Runnable
        hilo1 = new Thread(new Runnable() {
            public void run() {
                while (!pararHilo1) { // Ciclo mientras no se detenga el hilo 1
                    try {
                        Thread.sleep(300); // Pausa el hilo por 300 ms
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // Muestra cualquier error de interrupción
                    }
                    CONTADOR1++; // Incrementa el contador del hilo 1
                    repaint(); // Redibuja el applet para mostrar el contador actualizado
                }
            }
        });

        // Inicializamos el segundo hilo con otro Runnable
        hilo2 = new Thread(new Runnable() {
            public void run() {
                while (!pararHilo2) { // Ciclo mientras no se detenga el hilo 2
                    try {
                        Thread.sleep(300); // Pausa el hilo por 300 ms
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // Muestra cualquier error de interrupción
                    }
                    CONTADOR2++; // Incrementa el contador del hilo 2
                    repaint(); // Redibuja el applet para mostrar el contador actualizado
                }
            }
        });
    }

    public void start() {
        // Se inicia el applet y se reinician las banderas de parada de los hilos
        pararHilo1 = false;
        pararHilo2 = false;
        if (!hilo1.isAlive()) { // Verifica si el hilo 1 está activo
            hilo1.start(); // Inicia el hilo 1 si no está en ejecución
        }
        if (!hilo2.isAlive()) { // Verifica si el hilo 2 está activo
            hilo2.start(); // Inicia el hilo 2 si no está en ejecución
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400); // Limpia el área de dibujo
        g.setFont(fuente); // Establece la fuente para el texto
        g.drawString("Hilo1: " + Long.toString(CONTADOR1), 80, 100); // Dibuja el contador del hilo 1
        g.drawString("Hilo2: " + Long.toString(CONTADOR2), 80, 150); // Dibuja el contador del hilo 2
    }

    public void actionPerformed(ActionEvent e) {
        // Acción si el botón b1 (para el hilo 1) es presionado
        if (e.getSource() == b1) {
            if (!pararHilo1) { // Si el hilo 1 está en ejecución
                pararHilo1 = true; // Activa la bandera de parada para el hilo 1
                b1.setLabel("Reanudar hilo 1"); // Cambia el texto del botón
            } else { // Si el hilo 1 está en pausa
                pararHilo1 = false; // Quita la pausa del hilo 1
                b1.setLabel("Finalizar hilo 1"); // Cambia el texto del botón
                if (!hilo1.isAlive()) { // Si el hilo 1 no está en ejecución
                    // Se reinicia el hilo 1 con un nuevo Runnable
                    hilo1 = new Thread(new Runnable() {
                        public void run() {
                            while (!pararHilo1) { // Bucle de ejecución del hilo 1
                                try {
                                    Thread.sleep(300); // Pausa el hilo por 300 ms
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace(); // Muestra errores de interrupción
                                }
                                CONTADOR1++; // Incrementa el contador del hilo 1
                                repaint(); // Redibuja el applet para actualizar el contador
                            }
                        }
                    });
                    hilo1.start(); // Inicia el hilo 1
                }
            }
        }

        // Acción si el botón b2 (para el hilo 2) es presionado
        if (e.getSource() == b2) {
            if (!pararHilo2) { // Si el hilo 2 está en ejecución
                pararHilo2 = true; // Activa la bandera de parada para el hilo 2
                b2.setLabel("Reanudar hilo 2"); // Cambia el texto del botón
            } else { // Si el hilo 2 está en pausa
                pararHilo2 = false; // Quita la pausa del hilo 2
                b2.setLabel("Finalizar hilo 2"); // Cambia el texto del botón
                if (!hilo2.isAlive()) { // Si el hilo 2 no está en ejecución
                    // Reinicia el hilo 2 con un nuevo Runnable
                    hilo2 = new Thread(new Runnable() {
                        public void run() {
                            while (!pararHilo2) { // Bucle de ejecución del hilo 2
                                try {
                                    Thread.sleep(300); // Pausa el hilo por 300 ms
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace(); // Muestra errores de interrupción
                                }
                                CONTADOR2++; // Incrementa el contador del hilo 2
                                repaint(); // Redibuja el applet para actualizar el contador
                            }
                        }
                    });
                    hilo2.start(); // Inicia el hilo 2
                }
            }
        }
    }

    public void stop() {
        // Detiene ambos hilos cuando se para el applet
        pararHilo1 = true; // Marca para detener el hilo 1
        pararHilo2 = true; // Marca para detener el hilo 2
    }
}
