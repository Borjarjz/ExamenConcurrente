package Monitores;

class Alumno extends Thread {
    private Monitor monitor;

    public Alumno(Monitor monitor) {
        this.monitor = monitor;

    }

    public void run() {
        monitor.esperarPuesto();
        int miNP = monitor.asignarNP();
        System.out.println("Alumno " + Thread.currentThread().getName() + " ha sido asignado NP: " + miNP);
        monitor.liberarPuesto();
    }
}