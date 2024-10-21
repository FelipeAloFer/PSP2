package actividad2_2;

public class Hilo2 implements Runnable {
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
