package Ejercicio9;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio9 extends Applet implements ActionListener {
    class HiloContador extends Thread {
        private boolean parar;
        private boolean pausado;
        private long contador = 0;
        private final String nombre;
        private final int tiempoEspera;

        public HiloContador(String nombre, int tiempoEspera) {
            this.nombre = nombre;
            this.tiempoEspera = tiempoEspera;
            parar = false;
            pausado = false;
        }

        @Override
        public void run() {
            while (!parar) {
                synchronized (this) {
                    while (pausado) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(tiempoEspera);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
                repaint();
            }
        }

        public synchronized void pausar() {
            pausado = true;
        }

        public synchronized void reanudar() {
            pausado = false;
            notify();
        }

        public void detener() {
            parar = true;
        }

        public long getContador() {
            return contador;
        }

        public boolean isPausado() {
            return pausado;
        }

        public String getEstado() {
            return pausado ? "Pausado" : "Ejecutándose";
        }
    }

    private HiloContador hilo1;
    private HiloContador hilo2;

    private Font fuente;
    private Button bInicio, bPausarHilo1, bPausarHilo2, bFinalizar;
    private Label etiquetaHilo1, etiquetaHilo2;

    @Override
    public void init() {
        setBackground(Color.white);
        setLayout(null);

        fuente = new Font("Serif", Font.BOLD, 16);

        // Botón de inicio
        bInicio = new Button("Comenzar Proceso");
        bInicio.setBounds(100, 20, 200, 30);
        bInicio.addActionListener(this);
        add(bInicio);

        // Botón para pausar/reanudar Hilo 1
        bPausarHilo1 = new Button("Interrumpir");
        bPausarHilo1.setBounds(80, 80, 100, 30);
        bPausarHilo1.addActionListener(this);
        add(bPausarHilo1);

        // Botón para pausar/reanudar Hilo 2
        bPausarHilo2 = new Button("Interrumpir");
        bPausarHilo2.setBounds(220, 80, 100, 30);
        bPausarHilo2.addActionListener(this);
        add(bPausarHilo2);

        // Etiqueta para el contador de Hilo 1
        etiquetaHilo1 = new Label("HILO 1");
        etiquetaHilo1.setBounds(100, 130, 100, 30);
        etiquetaHilo1.setFont(fuente);
        add(etiquetaHilo1);

        // Etiqueta para el contador de Hilo 2
        etiquetaHilo2 = new Label("HILO 2");
        etiquetaHilo2.setBounds(240, 130, 100, 30);
        etiquetaHilo2.setFont(fuente);
        add(etiquetaHilo2);

        // Botón para finalizar el proceso
        bFinalizar = new Button("Finalizar Proceso");
        bFinalizar.setBounds(100, 220, 200, 30);
        bFinalizar.addActionListener(this);
        add(bFinalizar);

        // Inicializamos los hilos pero no los iniciamos hasta que se presione el botón
        hilo1 = new HiloContador("Hilo 1", 300);
        hilo2 = new HiloContador("Hilo 2", 300);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);

        // Muestra el contador y el estado del primer hilo en la posición correcta
        g.drawString("Contador Hilo 1: " + hilo1.getContador(), 80, 180);

        // Muestra el contador y el estado del segundo hilo en la posición correcta
        g.drawString("Contador Hilo 2: " + hilo2.getContador(), 220, 180);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bInicio) {
            if (!hilo1.isAlive()) hilo1.start();
            if (!hilo2.isAlive()) hilo2.start();
        } else if (e.getSource() == bPausarHilo1) {
            if (!hilo1.isPausado()) {
                hilo1.pausar();
            } else {
                hilo1.reanudar();
            }
        } else if (e.getSource() == bPausarHilo2) {
            if (!hilo2.isPausado()) {
                hilo2.pausar();
            } else {
                hilo2.reanudar();
            }
        } else if (e.getSource() == bFinalizar) {
            hilo1.detener();
            hilo2.detener();
            repaint();
            System.out.println("Valor final del contador Hilo 1: " + hilo1.getContador());
            System.out.println("Valor final del contador Hilo 2: " + hilo2.getContador());
        }
    }

    @Override
    public void stop() {
        if (hilo1 != null) {
            hilo1.detener();
        }
        if (hilo2 != null) {
            hilo2.detener();
        }
        hilo1 = null;
        hilo2 = null;
    }
}
