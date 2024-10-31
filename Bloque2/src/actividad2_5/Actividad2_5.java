package actividad2_5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_5 extends Applet implements ActionListener {
    // Clase interna para el hilo que maneja el contador
    class HiloContador extends Thread {
        private boolean parar; // Bandera que indica si el hilo debe detenerse
        private boolean pausado; // Bandera que indica si el hilo está en pausa
        long contador = 0; // Valor del contador que lleva el hilo

        public HiloContador() {
            parar = false; // Inicializa el hilo como activo
            pausado = false; // Inicializa el hilo sin estar en pausa
        }

        @Override
        public void run() {
            // Bucle que mantiene el hilo en ejecución hasta que se indique detenerlo
            while (!parar) {
                synchronized (this) {
                    // Mantiene el hilo en pausa si la bandera pausado está activa
                    while (pausado) {
                        try {
                            wait(); // Espera hasta que el hilo se reanude
                        } catch (InterruptedException e) {
                            e.printStackTrace(); // Muestra cualquier error de interrupción
                        }
                    }
                }
                try {
                    Thread.sleep(300); // Hace que el hilo espere 300 ms
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Muestra errores si se interrumpe el hilo
                }
                contador++; // Aumenta el contador
                repaint(); // Redibuja la pantalla para actualizar el valor del contador
            }
        }

        public synchronized void pausar() {
            pausado = true; // Activa el modo de pausa
        }

        public synchronized void reanudar() {
            pausado = false; // Desactiva la pausa
            notify(); // Notifica para continuar la ejecución del hilo
        }

        public void detener() {
            parar = true; // Activa la bandera para detener el hilo
        }

        public long getContador() {
            return contador; // Retorna el contador actual
        }

        public boolean isPausado() {
            return pausado; // Retorna si el hilo está en pausa o no
        }
    }

    private HiloContador hilo1; // Objeto para el primer hilo contador
    private HiloContador hilo2; // Objeto para el segundo hilo contador

    private Font fuente; // Fuente de texto para mostrar los contadores
    private Button b1, b2; // Botones para controlar los hilos

    @Override
    public void init() {
        setBackground(Color.pink); // Define el color de fondo
        setLayout(new FlowLayout()); // Establece el diseño

        // Configuración de los botones
        b1 = new Button("Finalizar hilo 1"); // Botón para controlar el hilo 1
        b1.addActionListener(this); // Agrega un escuchador de eventos al botón
        add(b1); // Añade el botón al applet

        b2 = new Button("Finalizar hilo 2"); // Botón para controlar el hilo 2
        b2.addActionListener(this); // Agrega un escuchador de eventos al botón
        add(b2); // Añade el botón al applet

        fuente = new Font("Serif", Font.BOLD, 26); // Configura la fuente para el texto

        // Creación e inicio de los hilos
        hilo1 = new HiloContador(); // Crea el primer hilo
        hilo2 = new HiloContador(); // Crea el segundo hilo
        hilo1.start(); // Inicia el primer hilo
        hilo2.start(); // Inicia el segundo hilo
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400); // Limpia la zona de dibujo
        g.setFont(fuente); // Establece la fuente
        g.drawString("Contador Hilo 1: " + hilo1.contador, 20, 50); // Dibuja el contador del hilo 1
        g.drawString("Contador Hilo 2: " + hilo2.contador, 20, 80); // Dibuja el contador del hilo 2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Comprueba si el evento proviene del botón del hilo 1
        if (e.getSource() == b1) {
            if (!hilo1.isPausado()) { // Si el hilo no está en pausa
                hilo1.pausar(); // Activa la pausa para el hilo
                b1.setLabel("Reanudar hilo 1"); // Cambia el texto del botón
            } else {
                hilo1.reanudar(); // Quita la pausa del hilo
                b1.setLabel("Finalizar hilo 1"); // Cambia el texto del botón
            }
        } else if (e.getSource() == b2) { // Si el evento es del botón del hilo 2
            if (!hilo2.isPausado()) { // Si el hilo no está en pausa
                hilo2.pausar(); // Activa la pausa para el hilo
                b2.setLabel("Reanudar hilo 2"); // Cambia el texto del botón
            } else {
                hilo2.reanudar(); // Quita la pausa del hilo
                b2.setLabel("Finalizar hilo 2"); // Cambia el texto del botón
            }
        }
    }

    @Override
    public void stop() {
        if (hilo1 != null) {
            hilo1.detener(); // Detiene el primer hilo
        }
        if (hilo2 != null) {
            hilo2.detener(); // Detiene el segundo hilo
        }
        hilo1 = null; // Libera la referencia al primer hilo
        hilo2 = null; // Libera la referencia al segundo hilo
    }
}
