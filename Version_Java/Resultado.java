package Version_Java;

public class Resultado {

    public final String metodo;
    public final long soma;
    public final long tempo;

    public Resultado(String metodo, long soma, long tempo) {
        this.metodo = metodo;
        this.soma = soma;
        this.tempo = tempo;
    }
}