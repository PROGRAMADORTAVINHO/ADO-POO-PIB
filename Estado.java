public class Estado {

    private final String nome;
    private Double pib;

    public void setPib(Double pib) {
        this.pib = pib;
    }

    public Estado(String nome, double pib) {
        this.nome = nome;
        this.pib = pib;
    }

    public String getNome() {
        return nome;
    }

    public double getPib() {
        return pib;
    }
}