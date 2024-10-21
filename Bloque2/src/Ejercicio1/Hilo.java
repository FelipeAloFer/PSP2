package Ejercicio1;

public class Hilo extends Thread {
    int id = 0;
    public Hilo(int id) {
        this.id = id;
        System.out.println("Hola mundo " + id);
    }
}
