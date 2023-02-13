package Dados;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DadosHilos {

    public static class HiloDado extends Thread {
        int resultado;

        @Override
        public void run() {
            // Simula el lanzamiento de un dado
            resultado = (int) (Math.random() * 6 + 1);
        }
    }

    public static int[] tiradaDados(int numDados) {
        List<HiloDado> hilos = new ArrayList<>();
        int[] resultados = new int[numDados];

        // Crea numDados hilos
        for (int i = 0; i < numDados; i++) {
            HiloDado hilo = new HiloDado();
            hilos.add(hilo);
        }

        // Ejecuta todos los hilos
        for (HiloDado hilo : hilos) {
            hilo.start();
        }

        // Recoge los resultados de todos los hilos
        for (int i = 0; i < numDados; i++) {
            HiloDado hilo = hilos.get(i);
            try {
                hilo.join();
                resultados[i] = hilo.resultado;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Devuelve el array con los resultados obtenidos
        return resultados;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el número de dados a lanzar: ");
        int numDados = scanner.nextInt();
        int[] resultados = tiradaDados(numDados);
        System.out.print("Resultados: ");

        for (int i = 0; i < numDados; i++) {
            System.out.print(resultados[i] + " ");
        }
        System.out.println();
        scanner.close();
    }
}
