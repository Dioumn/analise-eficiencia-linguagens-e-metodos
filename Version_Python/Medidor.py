import time

def medir_linhas(m):
    inicio = time.perf_counter_ns()
    soma = 0

    # jeito inteligente
    for i in range(len(m)):
        for j in range(len(m)):
            soma += m[i][j]

    fim = time.perf_counter_ns()
    return ("Linhas", soma, fim - inicio)

def medir_colunas(m):
    inicio = time.perf_counter_ns()
    soma = 0

    # jeito burro
    for i in range(len(m)):
        for j in range(len(m)):
            soma += m[j][i]

    fim = time.perf_counter_ns()
    return ("Colunas", soma, fim - inicio)