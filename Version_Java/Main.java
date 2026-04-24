package Version_Java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Informe a dimenção da matriz: ");
        int dimensao = scan.nextInt();

        int[][] matriz = new int[dimensao][dimensao];

        System.out.println("Deseja preencher a matriz com valores aleatórios ou fixos? (A/F)");
        char opcao = scan.next().charAt(0);

        // Preenche a matriz com valores fixos ou aleatórios, dependendo da escolha do usuário
        if (opcao == 'F' || opcao == 'f') {
            System.out.println("Informe os valor que todas as célula devem ter: ");
            int valor = scan.nextInt();

            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    matriz[i][j] = valor;
                }
            }
        } else {
            System.out.println("Informe o valor mínimo de uma célula: ");
            int min = scan.nextInt();
            System.out.println("Informe o value máximo de uma célula: ");
            int max = scan.nextInt();

            // Gera a matriz com valores aleatórios e exibe o progresso
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    matriz[i][j] = rand.nextInt(max - min + 1) + min;
                    int progress = (i * dimensao + j + 1) * 100 / (dimensao * dimensao);
                    System.out.print("\rGerando matriz: " + progress + "%");
                }
            }
        }

        // Calcula a soma dos elementos da matriz e mede o tempo gasto para isso (jeito inteligente)
        long inicio = System.nanoTime();
            long soma = 0;
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    soma += matriz[i][j];
                }
            }
        long fim = System.nanoTime();

        System.out.println("\n--------------------Método-por-Linhas--------------------");
        System.out.println("\nTempo gasto para calcular a soma: " + (fim - inicio)/1000000000.0 + " segundos");
        System.out.println("Soma: " + soma);


        // Calcula a soma dos elementos da matriz e mede o tempo gasto para isso (jeito burro)
        inicio = System.nanoTime();
            soma = 0;
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    soma += matriz[j][i];
                }
            }
        fim = System.nanoTime();

        System.out.println("\n--------------------Método-por-Colunas--------------------");
        System.out.println("\nTempo gasto para calcular a soma: " + (fim - inicio)/1000000000.0 + " segundos");
        System.out.println("Soma: " + soma);
        System.out.println("__________________________________________________________");

        scan.close();
    }
} 