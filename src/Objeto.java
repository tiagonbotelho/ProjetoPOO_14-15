/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

import java.io.Serializable;
import java.util.*;

public class Objeto extends Entidade implements Serializable{
    private String tipo;
    private static final String tipos[] = {"Mesa","Cadeira","Lâmpada","Pessoa","Porta","Caixa"};
    public Objeto(String forma,String cor,Coord pos,String type) {
        super(forma, cor, pos);
        tipo=type;
    }
    public Objeto(){
        super();  
    }
    
    /**
     * 
     * @param width Valor de comprimento do Ambiente correspondente
     * @param height Valor de largura do Ambiente correspondente
     */
    
    public void ObjetoRandom(int width,int height){ //Coloca valores random (entre uns predefenidos)nos atributos do objeto
        Random rd = new Random();
        cor  = cores[rd.nextInt(cores.length)]; 
        tipo = tipos[rd.nextInt(tipos.length)];
        forma = formas[rd.nextInt(formas.length)];
        pos = new Coord(rd.nextInt(width) + 1,rd.nextInt(height)+1);
    }

    public String toString() {
        return "Objeto "+super.toString() + "Tipo: " + tipo;
    }
    
    /**
     * 
     * @param compare Objeto para ser comparado com o fim de Calcular a Diferenca de Hamming
     * @return int Retorna o valor da Diferenca de Hamming
     */

    public int calcHamming(Objeto compare) {
        int hamming=0;
        if (!forma.equals(compare.forma)) {
            hamming += 1;
        }
        if (!tipo.equals(compare.tipo)) {
            hamming+=1;
        }
        if (!cor.equals(compare.cor)) {
            hamming +=1;
        }
        
        return hamming;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String n) {
        tipo=n;
    }
}
