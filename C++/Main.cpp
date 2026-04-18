#include <iostream>

using namespace std;

int main() {
    int dimencao, min, max;
    cout << "Informe a dimenção da matriz: "; cin >> dimencao;
    cout << "Informe o valor mínimo de uma célula: "; cin >> min;
    cout << "Informe o valor máximo de uma célula: "; cin >> max;

    int matriz[dimencao][dimencao];

    // Gera a matriz com valores aleatórios e exibe o progresso
    for (int i = 0; i < dimencao; i++) {
        for (int j = 0; j < dimencao; j++) {
            matriz[i][j] = rand() % (max - min + 1) + min;
            int progress = (i * dimencao + j + 1) * 100 / (dimencao * dimencao);
            cout << "\rGerando matriz: " << progress << "%";
        }
    }

    // Calcula a soma dos elementos da matriz
    int soma = 0;
    for (int i = 0; i < dimencao; i++) {
        for (int j = 0; j < dimencao; j++) {
            soma += matriz[i][j];
        }
    }
    cout << "\nSoma: " << soma << endl;


    return 0;
}