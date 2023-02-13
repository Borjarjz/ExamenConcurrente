package Dados;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DadosProcesos {

    public static int[] tiradaDados(int numDados) {
        List<Process> procesos = new ArrayList<>();
        int[] resultados = new int[numDados];

        // Crea numDados procesos que simularán el lanzamiento de un dado
        for (int i = 0; i < numDados; i++) {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "LanzamientoDado");
            try {
                Process p = pb.start();
                procesos.add(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Recoge los valores de todos los dados
        for (int i = 0; i < numDados; i++) {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "LanzamientoDado");
            try {
                Process p = pb.start();
                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = reader.readLine();
                    if (line != null) {
                        resultados[i] = Integer.parseInt(line);
                    } else {
                        System.out.println("Error al leer la salida estándar del proceso");
                    }
                } else {
                    System.out.println("El proceso terminó con código de salida " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Devuelve el array con los valores obtenidos
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
