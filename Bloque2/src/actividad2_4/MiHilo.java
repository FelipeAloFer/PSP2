package actividad2_4;

public class MiHilo extends Thread {
    private SolicitarSuspender suspender = new SolicitarSuspender();

    public void Suspender() {
        suspender.set(true);
    }

    public void Reanudar() {
        suspender.set(false);
    }

    @Override
    public void run() {
        try {
            suspender.esperandoParaReanudar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
