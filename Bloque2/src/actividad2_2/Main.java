package actividad2_2;

public class Main extends Thread {
    public static void main(String[] args) {
        Hilo1 hilo1 = new Hilo1();
        Hilo2 hilo2 = new Hilo2();
        new Thread(hilo1).start();
        new Thread(hilo2).start();
    }
}
