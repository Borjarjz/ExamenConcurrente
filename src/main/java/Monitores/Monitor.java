package Monitores;

class Monitor {
    private int NP = 100000;
    private int numPuestos = 3;
    private int[] puestos = new int[numPuestos];

    public synchronized int asignarNP() {
        int miNP = NP;
        NP++;
        return miNP;
    }

    public synchronized void esperarPuesto() {
        while (puestos[0] + puestos[1] + puestos[2] == 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < numPuestos; i++) {
            if (puestos[i] == 0) {
                puestos[i] = 1;
                System.out.println("Alumno " + Thread.currentThread().getName() + " Se le asigna el puesto " + (i + 1));
                break;
            }
        }
    }

    public synchronized void liberarPuesto() {
        for (int i = 0; i < numPuestos; i++) {
            if (puestos[i] == 1) {
                puestos[i] = 0;
                System.out.println("Alumno " + Thread.currentThread().getName() + " ha liberado el puesto " + (i + 1));
                break;
            }
        }
        notifyAll();
    }
}


