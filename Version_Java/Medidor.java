package Version_Java;

public class Medidor {

    public static Resultado medirLinhas(int[][] m) {
        long inicio = System.nanoTime();
        long soma = 0;

        // percorre a matriz por linhas (jeito sigma de acessar a matriz - melhor uso de cache)
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m.length; j++)
                soma += m[i][j];

        long fim = System.nanoTime();

        return new Resultado("Linhas", soma, (fim - inicio));
    }

    public static Resultado medirColunas(int[][] m) {
        long inicio = System.nanoTime();
        long soma = 0;

        // percorre a matriz por colunas (jeito burro de acessar a matriz - pior uso de cache)
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m.length; j++)
                soma += m[j][i];

        long fim = System.nanoTime();

        return new Resultado("Colunas", soma, (fim - inicio));
    }
}