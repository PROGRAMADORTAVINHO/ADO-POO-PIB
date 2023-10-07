import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Description . . .
 *
 * @author: Autor
 * @version: 1.0
 *           Main Class File: topAXX.java
 *           File: Structure.java
 *           Date: DD/MM/YYYY
 */

public class Main {
    public static void main(String[] args) {

        // nome do arquivo
        String nomeDoArquivo1 = "pib.txt";
        String nomeDoArquivo2 = "regioes.txt";

        // linha temporaria
        String linha = null;

        /* ------------------------------------- */
        /* Abertura de arquivo e loop de leitura */
        /* ------------------------------------- */
        List<Estado> estados = new ArrayList<>();
        List<Regiao> regioes = new ArrayList<>();   
        
        try {
            FileReader fileReader = new FileReader(nomeDoArquivo1);
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
                System.out.println(estado.getNome()+","+ percentual+"%");
            }
            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
        }

        Regiao regiao = null;

        try {
            FileReader fileReader = new FileReader(nomeDoArquivo2);
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
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo2 + "'");
       } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo2 + "'");
        }
        /* ------------------------------------- */
        /* Exemplo de escrita em arquivo */
        /* ------------------------------------- */
        String arquivoDeSaida = "saida.txt";

        try {
            FileWriter fileWriter = new FileWriter(arquivoDeSaida);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Regiao regiao2 : regioes) {
                bufferedWriter.write("Pib da regiao " + regiao2.getNome() + " = " + regiao2.calcularPib()+"%");
                bufferedWriter.newLine();
            }
            // feche o arquivo
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }
    }
}