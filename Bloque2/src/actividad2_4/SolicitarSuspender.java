package actividad2_4;

public class SolicitarSuspender {
    private boolean suspender;

    public synchronized void set (boolean b) {
        suspender = b;
        notifyAll();
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait();
        }
    }
}
