package actividad2_1;

public class Hilo2 extends Thread {
    public Hilo2() {
        System.out.println("Hilo2 creado");
    }

    public void run() {
        for (int i = 0; i > -1; i++) {
            System.out.println("TAC");
            try {
                Thread.sleep(2100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
