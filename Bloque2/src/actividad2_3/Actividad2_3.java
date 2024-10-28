package actividad2_3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_3 extends Applet implements ActionListener {
    private Thread hilo1, hilo2;
    long CONTADOR1 = 0;
    long CONTADOR2 = 0;
    private boolean pararHilo1, pararHilo2;
    private Font fuente;
    private Button b1, b2;

    public void init() {
        setBackground(Color.pink);
        add(b1 = new Button("Finalizar hilo 1"));
        b1.addActionListener(this);
        add(b2 = new Button("Finalizar hilo 2"));
        b2.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);

        // Inicializamos los hilos
        hilo1 = new Thread(new Runnable() {
            public void run() {
                while (!pararHilo1) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CONTADOR1++;
                    repaint();
                }
            }
        });

        hilo2 = new Thread(new Runnable() {
            public void run() {
                while (!pararHilo2) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CONTADOR2++;
                    repaint();
                }
            }
        });
    }

    public void start() {
        // Iniciar ambos hilos al comenzar el applet
        pararHilo1 = false;
        pararHilo2 = false;
        if (!hilo1.isAlive()) {
            hilo1.start();
        }
        if (!hilo2.isAlive()) {
            hilo2.start();
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString("Hilo1: " + Long.toString(CONTADOR1), 80, 100);
        g.drawString("Hilo2: " + Long.toString(CONTADOR2), 80, 150);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (!pararHilo1) {
                pararHilo1 = true;
                b1.setLabel("Reanudar hilo 1");
            } else {
                pararHilo1 = false;
                b1.setLabel("Finalizar hilo 1");
                if (!hilo1.isAlive()) {
                    hilo1 = new Thread(new Runnable() {
                        public void run() {
                            while (!pararHilo1) {
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                CONTADOR1++;
                                repaint();
                            }
                        }
                    });
                    hilo1.start();
                }
            }
        }
        if (e.getSource() == b2) {
            if (!pararHilo2) {
                pararHilo2 = true;
                b2.setLabel("Reanudar hilo 2");
            } else {
                pararHilo2 = false;
                b2.setLabel("Finalizar hilo 2");
                if (!hilo2.isAlive()) {
                    hilo2 = new Thread(new Runnable() {
                        public void run() {
                            while (!pararHilo2) {
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                CONTADOR2++;
                                repaint();
                            }
                        }
                    });
                    hilo2.start();
                }
            }
        }
    }

    public void stop() {
        // Parar ambos hilos cuando se detenga el applet
        pararHilo1 = true;
        pararHilo2 = true;
    }
}

