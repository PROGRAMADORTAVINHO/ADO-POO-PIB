import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // nome do arquivo
        String ArquivoPib = "pib.txt";
        String ArquivoRegioes = "regioes.txt";

        // linha temporaria
        String linha = null;

        /* --------------------------------------- */
        /* Abertura dos arquivos e loop de leitura */
        /* --------------------------------------- */
        List<Estado> estados = new ArrayList<>();
        List<Regiao> regioes = new ArrayList<>();   
        
        try {
            FileReader fileReader = new FileReader(ArquivoPib);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                String[] vect = linha.split(";");
                String nome = vect[0];
                Double pib = Double.parseDouble(vect[1]);
                estados.add(new Estado(nome,pib));
            }
            Double pibTotal = 0d;
            for ( Estado estado : estados ){
                pibTotal += estado.getPib();
            }
            for(Estado estado : estados){
                Double percentual = (estado.getPib()/pibTotal)*100;
                System.out.println(estado.getNome() + " = " + percentual + "%");
            }
            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + ArquivoPib + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + ArquivoPib + "'");
        }

        Regiao regiao = null;

        try {
            FileReader fileReader = new FileReader(ArquivoRegioes);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                if( linha.equals("Norte") || linha.equals("Nordeste")|| linha.equals("Sudeste") || linha.equals("Sul") || linha.equals("Centro-Oeste")){  
                    String nome = linha ;
                    regiao = new Regiao(nome);
                    regioes.add(regiao);
                }else if( linha.isEmpty()){
                    continue;
                }else{
                     for (Estado estado : estados) {
                        // Comparar a linha do arquivo com o nome do estado
                        if (linha.equals(estado.getNome())) {
                            regiao.adicionarEstado(estado);
                            break;
                        }
                    }
                } 
            }
            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + ArquivoRegioes + "'");
       } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + ArquivoRegioes + "'");
        }
        /* ------------------------------------- */
        /*        Escrita em arquivo txt         */
        /* ------------------------------------- */
        String arquivoDeSaida = "saida.txt";

        try {
            FileWriter fileWriter = new FileWriter(arquivoDeSaida);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Regiao regiaoSaida : regioes) {
                bufferedWriter.write("Pib da regiao " + regiaoSaida.getNome() + " = " + regiaoSaida.calcularPib()+"%");
                bufferedWriter.newLine();
            }
            // feche o arquivo
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }
    }
}