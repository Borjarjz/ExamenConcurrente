package Monitores;

public class MatriculacionUniversidadMonitores {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        for (int i = 0; i < 15; i++) {
            Alumno alumno = new Alumno(monitor);
            alumno.setName("Alumno-"+i);
            alumno.start();
        }
    }
}