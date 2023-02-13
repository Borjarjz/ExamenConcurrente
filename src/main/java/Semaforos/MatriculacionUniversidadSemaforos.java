package Semaforos;
import java.util.concurrent.Semaphore;

public class MatriculacionUniversidadSemaforos {

    private static int NP = 100000;
    private static Semaphore puestosMatricula = new Semaphore(3);
    private static Semaphore mutex = new Semaphore(1);

    public static class Alumno extends Thread {
        private int id;

        public Alumno(int id) {
            this.id = id;
        }

        public void run() {
            try {
                puestosMatricula.acquire();
                System.out.println("Alumno " + id + " obtiene un puesto de matriculación");
                mutex.acquire();
                NP++;
                System.out.println("Alumno " + id + " obtiene el NP " + NP);
                mutex.release();
                puestosMatricula.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 15; i++) {
            Alumno alumno = new Alumno(i);
            alumno.start();
        }
    }

}
