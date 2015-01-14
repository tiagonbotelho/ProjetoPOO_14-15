
/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulador {
    
    public static Ambiente a;
    
    /**
     * 
     * @param input String a ser protegida
     * @return bool Verifica se o input é válido ou não
     */
    
    private static boolean protectChar(String input) {
    	String simbols="?!.,;:-_`´^/()%&$#[]{}=+*|\""; 
    	char [] items = input.toCharArray();
    	for(char c: items) {
    		if(Character.isLetter(c) || simbols.indexOf(c)!=-1) {
                    return false;
    		
    		}
    	}
    	return true;
    }
    

    /**
     * 
     * @return Ambiente Retorna o novo ambiente que foi criado
     * @see protectChar Protecao de inputs
     */

    public static Ambiente newAmbient() {
    	
        String height,width;
        String lifeSpan;
        String campoVisao;
        Ambiente novo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o tamanho do novo ambiente.");
        System.out.print("Altura: ");
        height = sc.nextLine();
        while(!protectChar(height)) {
        	System.out.print("Altura invalida por favor introduza um valor válido: ");
        	height=sc.nextLine();
        }
        System.out.print("Largura: ");
        width = sc.nextLine();
        while(!protectChar(width)) {
        	System.out.print("Largura invalida por favor introduza um valor válido: ");
        	width=sc.nextLine();
        }
        System.out.print("Qual o tempo de vida dos robots que pretende?");
		lifeSpan = sc.nextLine();
        while(!protectChar(lifeSpan)) {
        	System.out.print("Input invalido por favor introduza um valor correto: ");
        	lifeSpan=sc.nextLine();
        }
        System.out.print("Qual o tamanho do campo de visão dos robots que pretende?");
        campoVisao = sc.nextLine();
        while(!protectChar(campoVisao)) {
        	System.out.print("Valor invalido por favor introduza um valor correto: ");
        }
        novo = new Ambiente(Integer.parseInt(width),Integer.parseInt(height), Integer.parseInt(lifeSpan), Integer.parseInt(campoVisao));
        return novo;
    }

    public static void main(String[] args) {

        try{
            if(!init()){
                System.out.println("Erro na busca");
                a = newAmbient();
                a.preencheAmbiente();
            }
        }catch(FileNotFoundException e){
            System.out.println("O ficheiro config.txt não existe.");
            a = newAmbient();
            a.preencheAmbiente();
        }catch(IOException e){
            System.out.println(e);
            return;
        } catch (ClassNotFoundException ex) {

        }
        NewFrame frame = new NewFrame(a);
        frame.setVisible(true);
    }

    /**
     * 
     * @return boolean Retorna falso caso alguma falha na informacao do ficheiro ocorra
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
            
    public static boolean init() throws FileNotFoundException, IOException, ClassNotFoundException{
        FicheirodeTexto config = new FicheirodeTexto();
        FicheirodeObjetos entidadesfile = new FicheirodeObjetos();
        int width,height,lifespan,campovisao;
        int[] aux;
        config.abreLeitura("config.txt");
        config.salta("width = ".length());
        if((aux = config.readNumber())[0] == 0){
            width = aux[1];
        }
        else{
            config.closeRead();
            return false;
        }
        config.salta("height = ".length());
        if((aux = config.readNumber())[0] == 0){
            height = aux[1];
        }
        else{
            config.closeRead();            
            return false;
        }
        config.salta("lifespan = ".length());
        if((aux = config.readNumber())[0] == 0){
            lifespan = aux[1];
        }
        else{
            config.closeRead();            
            return false;
        }
        config.salta("campovisao = ".length());
        if((aux = config.readNumber())[0] == 0){
            campovisao = aux[1];
        }
        else{
            config.closeRead();
            return false;
        }
        a = new Ambiente(width,height,lifespan,campovisao);
        try{
            entidadesfile.abreLeitura("entidades.dat");
            for(Entidade entidade:((Ambiente)entidadesfile.leObjeto()).getEntidades()){
                if(entidade.getPos().validCoord(a)){
                    a.addEntidade(entidade);
                }
            }
            entidadesfile.fechaLeitura();
        } catch(FileNotFoundException e){
            a.preencheAmbiente();
        }
        config.closeRead();
        return true;
    }
}
