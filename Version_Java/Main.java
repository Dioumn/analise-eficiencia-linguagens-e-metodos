package Version_Java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Main {

    // Configurações dos testes
    static int[] dimensoes = {4000, 8000, 12000};
    static int repeticoes = 5;

    public static void main(String[] args) throws IOException {

        try (FileWriter writer = new FileWriter("resultados/resultados_java.csv")) {

            writer.write("dimensao,metodo,tipo,intervalo,execucao,tempo_segundos,soma\n");

            for (int dim : dimensoes) {
                for (int tipo = 0; tipo < 3; tipo++) {

                    int[][] matriz = GeradorMatriz.gerar(dim, tipo);

                    String tipoStr = getTipoStr(tipo);
                    String intervaloStr = getIntervaloStr(tipo);

                    // Warm-up pra evitar que o Java brinque com a minha cara (foi horrivel descobrir isso)
                    for (int i = 0; i < 2; i++) {
                        Medidor.medirLinhas(matriz);
                        Medidor.medirColunas(matriz);
                    }

                    // Alterna a ordem pra evitar que o primeiro método testado tenha vantagem por estar na cache
                    for (int r = 0; r < repeticoes; r++) {

                        boolean linhasPrimeiro = (r % 2 == 0);

                        if (linhasPrimeiro) {
                            salvar(writer, dim, tipoStr, intervaloStr, r, Medidor.medirLinhas(matriz));
                            salvar(writer, dim, tipoStr, intervaloStr, r, Medidor.medirColunas(matriz));
                        } else {
                            salvar(writer, dim, tipoStr, intervaloStr, r, Medidor.medirColunas(matriz));
                            salvar(writer, dim, tipoStr, intervaloStr, r, Medidor.medirLinhas(matriz));
                        }
                    }
                }
            }
        }

        System.out.println("Testes finalizados.");
    }

    static void salvar(FileWriter writer, int dim, String tipo, String intervalo, int execucao, Resultado r) throws IOException {

        double tempo = r.tempo / 1_000_000_000.0;

        writer.write(String.format(
            Locale.US,
            "%d,%s,%s,%s,%d,%.6f,%d\n",
            dim,
            r.metodo,
            tipo,
            intervalo,
            execucao,
            tempo,
            r.soma
        ));
    }

    static String getTipoStr(int tipo) {
        switch (tipo) {
            case 0: return "Fixo";
            case 1: return "Fixo";
            default: return "Aleatorio";
        }
    }

    static String getIntervaloStr(int tipo) {
        switch (tipo) {
            case 0: return "1";
            case 1: return "100000";
            default: return "1-100000";
        }
    }
}