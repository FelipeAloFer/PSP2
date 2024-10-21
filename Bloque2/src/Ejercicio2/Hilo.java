package Ejercicio2;

public class Hilo implements Runnable {
    int id = 0;
    String cadena = "";

    public Hilo(String cadena, int id) {
        this.cadena = cadena;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(id * 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hola Mundo " + cadena + " " + id);
    }
}