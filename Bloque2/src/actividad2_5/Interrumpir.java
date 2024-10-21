package actividad2_5;

public class Interrumpir extends Thread {
    public void interrumpir(Thread hilo) {
        interrupt();
    }
}
