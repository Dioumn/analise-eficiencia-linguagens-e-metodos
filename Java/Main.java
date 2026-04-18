package Java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        // Solicita ao usuário as dimensões da matriz e os valores mínimo e máximo
        System.out.println("Informe a dimenção da matriz: ");
        int dimencao = scan.nextInt();
        System.out.println("Informe o valor mínimo de uma célula: ");
        int min = scan.nextInt();
        System.out.println("Informe o valor máximo de uma célula: ");
        int max = scan.nextInt();

        int[][] matriz = new int[dimencao][dimencao];

        // Gera a matriz com valores aleatórios e exibe o progresso
        for (int i = 0; i < dimencao; i++) {
            for (int j = 0; j < dimencao; j++) {
                matriz[i][j] = rand.nextInt(max - min + 1) + min;
                int progress = (i * dimencao + j + 1) * 100 / (dimencao * dimencao);
                System.out.print("\rGerando matriz: " + progress + "%");
            }
        }

        // Calcula a soma dos elementos da matriz e mede o tempo gasto
        long inicio = System.nanoTime();
            int soma = 0;
            for (int i = 0; i < dimencao; i++) {
                for (int j = 0; j < dimencao; j++) {
                    soma += matriz[i][j];
                }
            }
        long fim = System.nanoTime();

        System.out.println("Tempo gasto para calcular a soma: " + (fim - inicio)/1000000000.0 + " segundos");
        System.out.println("Soma: " + soma);

        scan.close();
    }
}