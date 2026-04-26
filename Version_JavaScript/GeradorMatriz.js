function gerar(dim, tipo) {
    // Gera a matriz de acordo com o tipo
    const m = Array.from({ length: dim }, () => new Array(dim));

    // Preenche a matriz
    for (let i = 0; i < dim; i++) {
        for (let j = 0; j < dim; j++) {
            if (tipo === 0) m[i][j] = 1;
            else if (tipo === 1) m[i][j] = 100000;
            else m[i][j] = Math.floor(Math.random() * 100000) + 1;
        }
    }
    return m;
}

module.exports = { gerar };