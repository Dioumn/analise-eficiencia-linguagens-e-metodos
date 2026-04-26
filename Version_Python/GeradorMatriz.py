import random

def gerar(dim, tipo):
    # Gera a matriz de acordo com o tipo
    m = [[0]*dim for _ in range(dim)]


    # Preenche a matriz
    for i in range(dim):
        for j in range(dim):
            if tipo == 0:
                m[i][j] = 1
            elif tipo == 1:
                m[i][j] = 100000
            else:
                m[i][j] = random.randint(1, 100000)
    return m