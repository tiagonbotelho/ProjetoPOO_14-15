
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
 *
 * @author Pedro
 */
public class FicheirodeTexto {
    BufferedReader buffReader;
    BufferedWriter buffWriter;
    
    public FicheirodeTexto(){
        
    }
    
    public void abreLeitura(String ficheiro) throws IOException{
        buffReader = new BufferedReader(new FileReader(ficheiro));  
    }
    
    public void abreEscrita(String ficheiro) throws IOException{
        buffWriter = new BufferedWriter(new FileWriter(ficheiro));
    }
    
    public String leLinha() throws IOException{
        return buffReader.readLine();
    }
        
    public void escreveLinha(String linha) throws IOException{
        buffWriter.write(linha,0,linha.length());
        buffWriter.newLine();
    }
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
    public void salta(int n) throws IOException{
        buffReader.skip(n);
    }
    
    public void closeRead() throws IOException{
        buffReader.close();        
    }
    
    public void closeWrite() throws IOException{
        buffWriter.close();
    }
}
