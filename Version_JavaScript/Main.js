import readline from 'readline';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const question = (str) => new Promise(resolve => rl.question(str, resolve));

const dimencao = parseInt(await question("Informe a dimensão da matriz: "), 10);

const matriz = Array.from({ length: dimencao }, () => Array(dimencao));

const opcao = (await question("Deseja preencher a matriz com valores aleatórios ou fixos? (A/F): "))
    .trim().charAt(0);

// Preenchimento
if (opcao === 'F' || opcao === 'f') {
    const valor = parseInt(await question("Informe o valor que todas as células devem ter: "), 10);

    for (let i = 0; i < dimencao; i++) {
        for (let j = 0; j < dimencao; j++) {
            matriz[i][j] = valor;
        }
    }
} else {
    const min = parseInt(await question("Informe o valor mínimo de uma célula: "), 10);
    const max = parseInt(await question("Informe o valor máximo de uma célula: "), 10);

    for (let i = 0; i < dimencao; i++) {
        for (let j = 0; j < dimencao; j++) {
            matriz[i][j] = Math.floor(Math.random() * (max - min + 1)) + min;

            const progress = Math.floor((i * dimencao + j + 1) * 100 / (dimencao * dimencao));
            process.stdout.write(`\rGerando matriz: ${progress}%`);
        }
    }
    console.log();
}

// -------- MÉTODO POR LINHAS --------
let inicio = process.hrtime.bigint();

let soma = 0;
for (let i = 0; i < dimencao; i++) {
    for (let j = 0; j < dimencao; j++) {
        soma += matriz[i][j];
    }
}

let fim = process.hrtime.bigint();

console.log("--------------------Método-por-Linhas--------------------");
console.log(`\nTempo: ${Number(fim - inicio) / 1e9} segundos`);
console.log("Soma:", soma);

// -------- MÉTODO POR COLUNAS --------
inicio = process.hrtime.bigint();

soma = 0;
for (let i = 0; i < dimencao; i++) {
    for (let j = 0; j < dimencao; j++) {
        soma += matriz[j][i];
    }
}

fim = process.hrtime.bigint();

console.log("--------------------Método-por-Colunas--------------------");
console.log(`\nTempo: ${Number(fim - inicio) / 1e9} segundos`);
console.log("Soma:", soma);
console.log("__________________________________________________________");

rl.close();