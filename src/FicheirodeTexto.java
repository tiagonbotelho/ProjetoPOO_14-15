
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Tiago Botelho 
 * @author Pedro Belém
 * 
 */
public class FicheirodeTexto {
    BufferedReader buffReader;
    BufferedWriter buffWriter;
    
    public FicheirodeTexto(){
        
    }
    
    /**
     * 
     * @param ficheiro Ficheiro onde pretendemos Ler uma determinada Informacao
     * @throws IOException
     */
    
    public void abreLeitura(String ficheiro) throws IOException{
        buffReader = new BufferedReader(new FileReader(ficheiro));  
    }
    
    /**
     * 
     * @param ficheiro Ficheiro onde pretendemos escrever uma determinada Informacao
     * @throws IOException
     */
    
    public void abreEscrita(String ficheiro) throws IOException{
        buffWriter = new BufferedWriter(new FileWriter(ficheiro));
    }
    
    /**
     * 
     * @return String Retorna a linha que foi procurada no ficheiro correspondente
     * @throws IOException
     */
    
    public String leLinha() throws IOException{
        return buffReader.readLine();
    }
    
    /**
     * 
     * @param linha Frase que queremos escrever no ficheiro correspondente
     * @throws IOException
     */
        
    public void escreveLinha(String linha) throws IOException{
        buffWriter.write(linha,0,linha.length());
        buffWriter.newLine();
    }
    
    /**
     * 
     * @return int[] Retorna o numero no ficheiro correspondente 
     * @throws IOException
     */
    
    public int[] readNumber() throws IOException{
        int[] result = new int[2];
        String numero = this.leLinha();
        if(numero!=null){
            try{
                result[1] = Integer.parseInt(numero);
            }catch (NumberFormatException e){
                result[0] = -1;
            }
        }
        else{
            result[0] = -1;
        }
        return result;
    }
    
    /**
     * 
     * @param n Posicao que queremos saltar no ficheiro correspondente
     * @throws IOException
     */
    
    public void salta(int n) throws IOException{
        buffReader.skip(n);
    }
    
    /**
     * 
     * @throws IOException
     */
    
    public void closeRead() throws IOException{
        buffReader.close();        
    }
    
    /**
     * 
     * @throws IOException
     */
    
    public void closeWrite() throws IOException{
        buffWriter.close();
    }
}
