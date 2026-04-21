# Análise de Eficiência: C++, Java, JavaScript e Python

Este projeto compara o desempenho de quatro linguagens ao percorrer uma matriz bidimensional (2D).

O objetivo é demonstrar a diferença entre dois padrões de acesso à memória:
- por linhas (acesso sequencial)
- por colunas (acesso não sequencial)

Essa diferença ocorre devido à localidade de cache, ou seja, como o processador carrega dados da memória.

---

## Estrutura do projeto

analise-eficiencia-linguagens-e-metodos/
│
├── Version_C++/
│   └── main.cpp
│
├── Version_Java/
│   └── Main.java
│
├── Version_Javascript/
│   └── Main.mjs (ou Main.js)
│
├── Version_Python/
│   └── Main.py
│
└── README.md

---

## O que o programa faz

1. Solicita o tamanho da matriz (N x N)
2. Permite preencher com valores fixos ou aleatórios
3. Calcula a soma da matriz de duas formas:
   - por linhas
   - por colunas
4. Mede o tempo de execução de cada método

---

## Como rodar

Abra o terminal na pasta raiz do projeto:

```bash
cd analise-eficiencia-linguagens-e-metodos
```

---

### C++

```bash
cd Version_C++
g++ main.cpp -O2 -o main.exe
.\main.exe
```

---

### Java

```bash
cd Version_Java
java Main.java
```

---

### JavaScript (Node.js)

```bash
cd Version_Javascript
node Main.js
```

---

### Python

```bash
cd Version_Python
python Main.py
```

---

## Observações

* Tenha em mente que matrizes grandes (dimenções >= 10000) consomem muita memória e escalam exponencialmente.
* Em C++, use `-O2` para resultados mais otimizados/realistas.

---