package actividad2_2;

public class Hilo1 implements Runnable {
    public void run() {
        for (int i = 0; i > -1; i++) {
            System.out.println("TIC");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
