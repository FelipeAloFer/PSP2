package Ejercicio8; // Declaración del paquete en el que se encuentra la clase

import javax.swing.*; // Importación de la biblioteca Swing para la interfaz gráfica
import java.awt.*; // Importación de la biblioteca AWT para la interfaz gráfica y diseño de componentes

// Clase principal que extiende JFrame para crear una ventana
public class Main extends JFrame {
    // Declaración de etiquetas y botones
    private JLabel estadoHilo1, estadoHilo2, contadorHilo1, contadorHilo2;
    private JButton btnComenzar, btnSuspender1, btnReanudar1, btnSuspender2, btnReanudar2, btnFinalizar;
    private MiHilo hilo1, hilo2; // Declaración de objetos de tipo MiHilo

    // Constructor de la clase Main
    public Main() {
        setTitle("EJECUTAR, SUSPENDER Y REANUDAR HILOS"); // Título de la ventana
        setSize(400, 300); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamiento al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Inicializamos componentes
        estadoHilo1 = new JLabel("Hilo 1 Detenido"); // Etiqueta para el estado del hilo 1
        estadoHilo2 = new JLabel("Hilo 2 Detenido"); // Etiqueta para el estado del hilo 2
        contadorHilo1 = new JLabel("Contador Hilo 1: 0"); // Etiqueta para el contador del hilo 1
        contadorHilo2 = new JLabel("Contador Hilo 2: 0"); // Etiqueta para el contador del hilo 2

        // Inicialización de botones
        btnComenzar = new JButton("Comenzar Proceso");
        btnSuspender1 = new JButton("Suspender Hilo 1");
        btnReanudar1 = new JButton("Reanudar Hilo 1");
        btnSuspender2 = new JButton("Suspender Hilo 2");
        btnReanudar2 = new JButton("Reanudar Hilo 2");
        btnFinalizar = new JButton("Finalizar Proceso");

        // Desactivar botones de suspender/reanudar inicialmente
        btnSuspender1.setEnabled(false); // El botón de suspender hilo 1 desactivado al inicio
        btnReanudar1.setEnabled(false); // El botón de reanudar hilo 1 desactivado al inicio
        btnSuspender2.setEnabled(false); // El botón de suspender hilo 2 desactivado al inicio
        btnReanudar2.setEnabled(false); // El botón de reanudar hilo 2 desactivado al inicio

        // Panel para botones y etiquetas
        JPanel panelBotones = new JPanel(); // Creación del panel de botones
        panelBotones.setLayout(new GridLayout(3, 2, 10, 10)); // Establece un layout de rejilla
        // Agregar botones al panel
        panelBotones.add(btnComenzar);
        panelBotones.add(btnFinalizar);
        panelBotones.add(btnSuspender1);
        panelBotones.add(btnReanudar1);
        panelBotones.add(btnSuspender2);
        panelBotones.add(btnReanudar2);

        JPanel panelEstado = new JPanel(); // Creación del panel de estado
        panelEstado.setLayout(new GridLayout(4, 1)); // Establece un layout de rejilla vertical
        // Agregar etiquetas al panel
        panelEstado.add(estadoHilo1);
        panelEstado.add(contadorHilo1);
        panelEstado.add(estadoHilo2);
        panelEstado.add(contadorHilo2);

        // Agregamos los paneles al frame
        add(panelBotones, BorderLayout.NORTH); // Panel de botones en la parte superior
        add(panelEstado, BorderLayout.CENTER); // Panel de estado en el centro

        // Configuramos los eventos de los botones
        configurarEventos(); // Llamada a método para configurar los eventos de los botones
    }

    // Método para configurar los eventos de los botones
    private void configurarEventos() {
        // Evento para el botón Comenzar
        btnComenzar.addActionListener(e -> iniciarHilos());

        // Evento para el botón Suspender Hilo 1
        btnSuspender1.addActionListener(e -> {
            hilo1.suspende(); // Llama al método suspender en hilo1
            estadoHilo1.setText("Hilo 1 Suspendido"); // Actualiza la etiqueta de estado
            btnSuspender1.setEnabled(false); // Desactiva el botón de suspender
            btnReanudar1.setEnabled(true); // Activa el botón de reanudar
        });

        // Evento para el botón Reanudar Hilo 1
        btnReanudar1.addActionListener(e -> {
            hilo1.reanuda(); // Llama al método reanudar en hilo1
            estadoHilo1.setText("Hilo 1 Corriendo"); // Actualiza la etiqueta de estado
            btnSuspender1.setEnabled(true); // Activa el botón de suspender
            btnReanudar1.setEnabled(false); // Desactiva el botón de reanudar
        });

        // Evento para el botón Suspender Hilo 2
        btnSuspender2.addActionListener(e -> {
            hilo2.suspende(); // Llama al método suspender en hilo2
            estadoHilo2.setText("Hilo 2 Suspendido"); // Actualiza la etiqueta de estado
            btnSuspender2.setEnabled(false); // Desactiva el botón de suspender
            btnReanudar2.setEnabled(true); // Activa el botón de reanudar
        });

        // Evento para el botón Reanudar Hilo 2
        btnReanudar2.addActionListener(e -> {
            hilo2.reanuda(); // Llama al método reanudar en hilo2
            estadoHilo2.setText("Hilo 2 Corriendo"); // Actualiza la etiqueta de estado
            btnSuspender2.setEnabled(true); // Activa el botón de suspender
            btnReanudar2.setEnabled(false); // Desactiva el botón de reanudar
        });

        // Evento para el botón Finalizar
        btnFinalizar.addActionListener(e -> finalizarHilos()); // Llama al método para finalizar hilos
    }

    // Método para iniciar los hilos
    private void iniciarHilos() {
        // Iniciar hilos con 500 ms y 700 ms de pausa
        hilo1 = new MiHilo("Hilo 1", 500, contadorHilo1); // Crea un nuevo hilo con 500 ms de pausa
        hilo2 = new MiHilo("Hilo 2", 500, contadorHilo2); // Crea un nuevo hilo con 700 ms de pausa

        hilo1.start(); // Inicia el hilo 1
        hilo2.start(); // Inicia el hilo 2

        estadoHilo1.setText("Hilo 1 Corriendo"); // Actualiza el estado del hilo 1
        estadoHilo2.setText("Hilo 2 Corriendo"); // Actualiza el estado del hilo 2
        btnComenzar.setEnabled(false); // Desactiva el botón de comenzar
        btnSuspender1.setEnabled(true); // Activa el botón de suspender hilo 1
        btnSuspender2.setEnabled(true); // Activa el botón de suspender hilo 2
    }

    // Método para finalizar los hilos
    private void finalizarHilos() {
        hilo1.detener(); // Llama al método detener en hilo1
        hilo2.detener(); // Llama al método detener en hilo2

        estadoHilo1.setText("Hilo 1 Finalizado"); // Actualiza el estado del hilo 1
        estadoHilo2.setText("Hilo 2 Finalizado"); // Actualiza el estado del hilo 2
        btnSuspender1.setEnabled(false); // Desactiva el botón de suspender hilo 1
        btnReanudar1.setEnabled(false); // Desactiva el botón de reanudar hilo 1
        btnSuspender2.setEnabled(false); // Desactiva el botón de suspender hilo 2
        btnReanudar2.setEnabled(false); // Desactiva el botón de reanudar hilo 2
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Asegura que la GUI se construya en el hilo de despacho de eventos
            Main frame = new Main(); // Crea una instancia de Main
            frame.setVisible(true); // Hace visible la ventana
        });
    }
}
