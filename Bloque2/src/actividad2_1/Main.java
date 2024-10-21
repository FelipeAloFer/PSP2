package actividad2_1;


public class Main extends Thread {
    public static void main(String[] args) {
        Hilo1 hilo1 = new Hilo1();
        Hilo2 hilo2 = new Hilo2();
        hilo1.start();
        hilo2.start();
    }

}
