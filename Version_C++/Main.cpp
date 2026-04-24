#include <iostream>
#include <vector>
#include <cstdlib>  // rand, srand
#include <ctime>    // time
#include <chrono>   // medir tempo

using namespace std;

int main() {
    srand(time(0)); // inicializa o gerador de aleatórios

    int dimensao;
    cout << "Informe a dimensao da matriz: " << flush;
    cin >> dimensao;

    vector<vector<int>> matriz(dimensao, vector<int>(dimensao));

    cout << "Deseja preencher a matriz com valores aleatorios ou fixos? (A/F): " << flush;
    char opcao;
    cin >> opcao;

    if (opcao == 'F' || opcao == 'f') {
        cout << "Informe o valor que todas as celulas devem ter: " << flush;
        int valor;
        cin >> valor;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                matriz[i][j] = valor;
            }
        }
    } else {
        int min, max;
        cout << "Informe o valor minimo de uma celula: ";
        cin >> min;
        cout << "Informe o valor maximo de uma celula: ";
        cin >> max;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                matriz[i][j] = rand() % (max - min + 1) + min;

                int progress = (i * dimensao + j + 1) * 100 / (dimensao * dimensao);
                cout << "Gerando matriz: " << progress << "%\n";
            }
        }
    }

    // Método por linhas
    auto inicio = chrono::high_resolution_clock::now();

    long long soma = 0;
    for (int i = 0; i < dimensao; i++) {
        for (int j = 0; j < dimensao; j++) {
            soma += matriz[i][j];
        }
    }

    auto fim = chrono::high_resolution_clock::now();
    chrono::duration<double> tempo = fim - inicio;

    cout << "\n--------------------Metodo-por-Linhas--------------------\n";
    cout << "Tempo: " << tempo.count() << " segundos\n";
    cout << "Soma: " << soma << endl;

    // Método por colunas
    inicio = chrono::high_resolution_clock::now();

    soma = 0;
    for (int i = 0; i < dimensao; i++) {
        for (int j = 0; j < dimensao; j++) {
            soma += matriz[j][i];
        }
    }

    fim = chrono::high_resolution_clock::now();
    tempo = fim - inicio;

    cout << "--------------------Metodo-por-Colunas--------------------\n";
    cout << "Tempo: " << tempo.count() << " segundos\n";
    cout << "Soma: " << soma << endl;

    return 0;
}