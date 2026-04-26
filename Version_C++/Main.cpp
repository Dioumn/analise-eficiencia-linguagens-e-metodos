#include <iostream>
#include <vector>
#include <fstream>
#include <chrono>
#include <random>

using namespace std;
using namespace chrono;

struct Resultado {
    string metodo;
    long long soma;
    long long tempo;
};

// Gera uma matriz quadrada de dimensão 'dim' e tipo 'tipo' (0: todos 1, 1: todos 100000, 2: aleatório entre 1 e 100000)
vector<vector<int>> gerar(int dim, int tipo) {
    vector<vector<int>> m(dim, vector<int>(dim));
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(1, 100000);

    for (int i = 0; i < dim; i++) {
        for (int j = 0; j < dim; j++) {
            switch (tipo) {
                case 0: m[i][j] = 1; break;
                case 1: m[i][j] = 100000; break;
                case 2: m[i][j] = dist(gen); break;
            }
        }
    }
    return m;
}

Resultado medirLinhas(const vector<vector<int>>& m) {
    auto inicio = high_resolution_clock::now();
    long long soma = 0;

    // jeito inteligente
    for (int i = 0; i < m.size(); i++)
        for (int j = 0; j < m.size(); j++)
            soma += m[i][j];

    auto fim = high_resolution_clock::now();

    return {"Linhas", soma, duration_cast<nanoseconds>(fim - inicio).count()};
}

Resultado medirColunas(const vector<vector<int>>& m) {
    auto inicio = high_resolution_clock::now();
    long long soma = 0;

    // jeito burro
    for (int i = 0; i < m.size(); i++)
        for (int j = 0; j < m.size(); j++)
            soma += m[j][i];

    auto fim = high_resolution_clock::now();

    return {"Colunas", soma, duration_cast<nanoseconds>(fim - inicio).count()};
}

int main() {
    vector<int> dimensoes = {4000, 8000, 12000};
    int repeticoes = 5;

    ofstream file("../resultados/resultados_cpp.csv");
    file << "dimensao,metodo,tipo,intervalo,execucao,tempo_segundos,soma\n";

    for (int dim : dimensoes) {
        for (int tipo = 0; tipo < 3; tipo++) {

            auto matriz = gerar(dim, tipo);

            string tipoStr = (tipo == 2) ? "Aleatorio" : "Fixo";
            string intervaloStr = (tipo == 0) ? "1" :
                                  (tipo == 1) ? "100000" : "1-100000";

            // warm-up (simbólico por que o C++ não tem nem JIT)
            for (int i = 0; i < 2; i++) {
                medirLinhas(matriz);
                medirColunas(matriz);
            }

            for (int r = 0; r < repeticoes; r++) {
                bool linhasPrimeiro = (r % 2 == 0);

                auto salvar = [&](Resultado res) {
                    double tempo = res.tempo / 1e9;
                    file << dim << "," << res.metodo << "," << tipoStr << ","
                         << intervaloStr << "," << r << "," << tempo << ","
                         << res.soma << "\n";
                };

                if (linhasPrimeiro) {
                    salvar(medirLinhas(matriz));
                    salvar(medirColunas(matriz));
                } else {
                    salvar(medirColunas(matriz));
                    salvar(medirLinhas(matriz));
                }
            }
        }
    }

    file.close();
    cout << "Testes finalizados.\n";
}