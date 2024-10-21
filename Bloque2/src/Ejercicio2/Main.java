package Ejercicio2;


public class Main {
    public static void main(String[] args) {
        Hilo hilo1 = new Hilo( "yo soy el hilo", 1);
        Hilo hilo2 = new Hilo( "yo soy el hilo", 2);
        Hilo hilo3 = new Hilo( "yo soy el hilo", 3);
        Hilo hilo4 = new Hilo( "yo soy el hilo", 4);
        Hilo hilo5 = new Hilo( "yo soy el hilo", 5);
        hilo1.run();
        hilo2.run();
        hilo3.run();
        hilo4.run();
        hilo5.run();
    }
}
