from GeradorMatriz import gerar
from Medidor import medir_linhas, medir_colunas

dimensoes = [4000, 8000, 12000]
repeticoes = 5

with open("resultados/resultados_python.csv", "w") as f:
    f.write("dimensao,metodo,tipo,intervalo,execucao,tempo_segundos,soma\n")

    # Loop pelos tipos de matrizes e dimensões
    for dim in dimensoes:
        for tipo in range(3):

            matriz = gerar(dim, tipo)

            tipoStr = "Aleatorio" if tipo == 2 else "Fixo"
            intervaloStr = "1" if tipo == 0 else ("100000" if tipo == 1 else "1-100000")

            # warm-up só de leve
            for _ in range(2):
                medir_linhas(matriz)
                medir_colunas(matriz)

            # Loop pelas repetições, alternando a ordem dos métodos para evitar vieses
            for r in range(repeticoes):
                linhasPrimeiro = (r % 2 == 0)

                def salvar(res):
                    metodo, soma, tempo = res
                    tempo = tempo / 1e9
                    f.write(f"{dim},{metodo},{tipoStr},{intervaloStr},{r},{tempo},{soma}\n")

                if linhasPrimeiro:
                    salvar(medir_linhas(matriz))
                    salvar(medir_colunas(matriz))
                else:
                    salvar(medir_colunas(matriz))
                    salvar(medir_linhas(matriz))

print("Testes finalizados.")