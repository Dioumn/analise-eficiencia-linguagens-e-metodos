function medirLinhas(m) {
    const inicio = process.hrtime.bigint();
    let soma = 0;

    for (let i = 0; i < m.length; i++)
        for (let j = 0; j < m.length; j++)
            soma += m[i][j];

    const fim = process.hrtime.bigint();

    return {
        metodo: "Linhas",
        soma,
        tempo: Number(fim - inicio)
    };
}

function medirColunas(m) {
    const inicio = process.hrtime.bigint();
    let soma = 0;

    for (let i = 0; i < m.length; i++)
        for (let j = 0; j < m.length; j++)
            soma += m[j][i];

    const fim = process.hrtime.bigint();

    return {
        metodo: "Colunas",
        soma,
        tempo: Number(fim - inicio)
    };
}

module.exports = { medirLinhas, medirColunas };