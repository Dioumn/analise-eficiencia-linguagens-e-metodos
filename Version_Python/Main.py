import random
import time


dimensao = int(input("Informe a dimensão da matriz: "))

matriz = [[0] * dimensao for _ in range(dimensao)]

opcao = input("Deseja preencher a matriz com valores aleatórios ou fixos? (A/F) ")[0]

# Preenche a matriz com valores fixos ou aleatórios, dependendo da escolha do usuário
if opcao in ('F', 'f'):
    valor = int(input("Informe os valor que todas as célula devem ter: "))
    for i in range(dimensao):
        for j in range(dimensao):
            matriz[i][j] = valor
else:
    min_val = int(input("Informe o valor mínimo de uma célula: "))
    max_val = int(input("Informe o value máximo de uma célula: "))

    # Gera a matriz com valores aleatórios e exibe o progresso
    total = dimensao * dimensao
    for i in range(dimensao):
        for j in range(dimensao):
            matriz[i][j] = random.randint(min_val, max_val)
            progress = (i * dimensao + j + 1) * 100 // total
            print(f"\rGerando matriz: {progress}%", end="")
    print()

# Calcula a soma dos elementos da matriz e mede o tempo gasto para isso (jeito inteligente)
inicio = time.perf_counter_ns()
soma = 0
for i in range(dimensao):
    for j in range(dimensao):
        soma += matriz[i][j]
fim = time.perf_counter_ns()

print("--------------------Método-por-Linhas--------------------")
print(f"\nTempo gasto para calcular a soma: {(fim - inicio) / 1_000_000_000:.6f} segundos")
print(f"Soma: {soma}")

# Calcula a soma dos elementos da matriz e mede o tempo gasto para isso (jeito burro)
inicio = time.perf_counter_ns()
soma = 0
for i in range(dimensao):
    for j in range(dimensao):
        soma += matriz[j][i]
fim = time.perf_counter_ns()

print("--------------------Método-por-Colunas--------------------")
print(f"\nTempo gasto para calcular a soma: {(fim - inicio) / 1_000_000_000:.6f} segundos")
print(f"Soma: {soma}")
print("__________________________________________________________")
