package Version_Java;

import java.util.Random;

public class GeradorMatriz {

    private static final Random rand = new Random();

    public static int[][] gerar(int dim, int tipo) {
        int[][] m = new int[dim][dim];

        // cria a matriz de acordo com o tipo
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {

                // tipo: 0 = (todos os valores iguais a 1), 1 = (todos os valores iguais a 100000), 2 = (valores aleatórios de 1 a 100000)
                switch (tipo) {
                    case 0: m[i][j] = 1; break;
                    case 1: m[i][j] = 100000; break;
                    case 2: m[i][j] = rand.nextInt(100000) + 1; break;
                }
            }
        }
        return m;
    }
}