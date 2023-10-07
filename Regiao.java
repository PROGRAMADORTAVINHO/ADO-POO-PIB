import java.util.*;


public class Regiao {

    private final String nome;
    private ArrayList<Estado> estados;


    public Regiao(String nome) {
        this.nome = nome;
        this.estados = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }
    

    public void adicionarEstado(Estado estado) {
        estados.add(estado);
    }

    public Double calcularPib(){
        Double pib = 0d;
        for ( Estado estado : estados ){
            pib += estado.getPib();
        }
        return pib;
    }

}

