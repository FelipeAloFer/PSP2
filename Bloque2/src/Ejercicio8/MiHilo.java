package Ejercicio8; // Declaración del paquete en el que se encuentra la clase

import actividad2_4.SolicitarSuspender; // Importación de la clase SolicitarSuspender

import javax.swing.*; // Importación de la biblioteca Swing para la interfaz gráfica

// Clase que extiende Thread para crear un hilo
public class MiHilo extends Thread {
    private SolicitarSuspender solicitarSuspender = new SolicitarSuspender(); // Instancia de SolicitarSuspender para gestionar la suspensión
    private int contador = 0; // Contador del hilo
    private boolean parar = false; // Indica si el hilo debe detenerse
    private int pausa; // Pausa del hilo
    private JLabel contadorLabel; // JLabel para mostrar el contador

    // Constructor con parámetros
    public MiHilo(String nombre, int pausa, JLabel contadorLabel) {
        super(nombre); // Llama al constructor de Thread con el nombre del hilo
        this.pausa = pausa; // Establece la pausa
        this.contadorLabel = contadorLabel; // Establece el JLabel para actualizar el contador
    }

    // Método para suspender el hilo
    public void suspende() {
        solicitarSuspender.setSuspender(true); // Activa la suspensión
    }

    // Método para reanudar el hilo
    public void reanuda() {
        solicitarSuspender.setSuspender(false); // Desactiva la suspensión
    }

    // Método para detener el hilo
    public void detener() {
        parar = true; // Indica que el hilo debe detenerse
        reanuda(); // Asegura que el hilo no esté suspendido
        interrupt(); // Interrumpe el hilo
    }

    // Método que contiene la lógica del hilo
    @Override
    public void run() {
        try {
            while (!parar) { // Bucle que se ejecuta mientras 'parar' sea false
                contador++; // Incrementa el contador
                contadorLabel.setText("Contador " + getName() + ": " + contador); // Actualiza el JLabel con el nuevo valor del contador

                // Pausa entre iteraciones
                try {
                    Thread.sleep(pausa); // El hilo se pausa por el tiempo especificado
                } catch (InterruptedException e) {
                    System.out.println("Hilo interrumpido durante sleep"); // Mensaje de log si se interrumpe el hilo
                    break; // Sale del bucle
                }

                solicitarSuspender.esperando(); // Espera si el hilo está suspendido
            }
            System.out.println("Hilo detenido. Contador final: " + contador); // Mensaje de log al finalizar el hilo
        } catch (Exception e) {
            System.out.println("Excepción en hilo: " + e.getMessage()); // Manejo de excepciones
        }
    }

    // Método para obtener el contador actual
    public int getContador() {
        return contador; // Devuelve el valor del contador
    }
}
