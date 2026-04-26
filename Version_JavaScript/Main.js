const fs = require("fs");

const { gerar } = require('./GeradorMatriz');
const { medirLinhas, medirColunas } = require('./Medidor');

const dimensoes = [4000, 8000, 12000];
const repeticoes = 5;

let csv = "dimensao,metodo,tipo,intervalo,execucao,tempo_segundos,soma\n";

for (const dim of dimensoes) {
    for (let tipo = 0; tipo < 3; tipo++) {

        const matriz = gerar(dim, tipo);

        const tipoStr = tipo === 2 ? "Aleatorio" : "Fixo";
        const intervaloStr = tipo === 0 ? "1" :
                             tipo === 1 ? "100000" : "1-100000";

        // warm-up pra evitar que o Node.js otimize o código durante os testes (tipo o Java)
        for (let i = 0; i < 2; i++) {
            medirLinhas(matriz);
            medirColunas(matriz);
        }

        for (let r = 0; r < repeticoes; r++) {
            const linhasPrimeiro = (r % 2 === 0);

            const salvar = (res) => {
                const tempo = res.tempo / 1e9;
                csv += `${dim},${res.metodo},${tipoStr},${intervaloStr},${r},${tempo},${res.soma}\n`;
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

fs.writeFileSync("../resultados/resultados_js.csv", csv);
console.log("Testes finalizados.");