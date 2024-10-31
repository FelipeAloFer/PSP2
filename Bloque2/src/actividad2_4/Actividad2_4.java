package actividad2_4;

import java.util.Scanner;

public class Actividad2_4 {
    public static void main(String[] args) {
        // Instancia y arranca un nuevo hilo de tipo MiHilo
        MiHilo miHilo = new MiHilo();
        miHilo.start();
        boolean condicion = true; // Controla el bucle principal

        // Inicia un Scanner para capturar la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        String input; // Variable para almacenar el comando del usuario

        // Mensaje informativo sobre los comandos disponibles
        System.out.println("Introduce 'S' para pausar el hilo, 'R' para reanudarlo o '*' para detener:");

        // Bucle principal que se ejecuta mientras la condición sea verdadera
        while (condicion) {
            input = scanner.nextLine(); // Captura la entrada del usuario

            // Si el usuario ingresa '*', interrumpe y termina el hilo
            if (input.equals("*")) {
                miHilo.interrupt(); // Interrumpe el hilo para finalizarlo
                condicion = false; // Cambia la condición para salir del bucle
            } else if (input.equalsIgnoreCase("S")) { // Pausa el hilo si el comando es 'S'
                miHilo.suspende(); // Llama a suspender el hilo
                System.out.println("Hilo pausado.");
            } else if (input.equalsIgnoreCase("R")) { // Restaura el hilo si el comando es 'R'
                miHilo.reanuda(); // Llama a reanudar el hilo
                System.out.println("Hilo en ejecución.");
            }
        }

        // Muestra el conteo final del contador tras finalizar el hilo
        System.out.println("Conteo final del contador: " + miHilo.getContador());

        // Cierra el Scanner para liberar recursos del sistema
        scanner.close();
    }
}
