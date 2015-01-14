/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Memoria implements Serializable{
    private ArrayList <Percepcao> memoria;
    private ArrayList <Objeto> objetos; 
    private ArrayList <Coord> walk;

    public Memoria(ArrayList<Percepcao> memory, ArrayList<Objeto> objects,ArrayList<Coord> walk) {
        memoria=memory;
        objetos=objects;
        this.walk = walk;
    }
    
    public Memoria(){
        memoria = new ArrayList<Percepcao>();
        objetos = new ArrayList<Objeto>();
        walk = new ArrayList<Coord>();
    }
    
    /**
     * 
     * @param perception Percepcao atual adicionada ao ArrayList
     */
    
    public void inserePercepcaoM(Percepcao perception){
        Percepcao novo = perception;
        memoria.add(novo);
    }


    /**
     * 
     * @param objeto Objeto a ser comparado
     * @return int Retorna o numero de ocorrencias do Objeto no ArrayList
     */
    
    public int numberOcurrences(Objeto objeto){
        int counter = 0;
        for(Objeto obj:objetos){
            if(obj == objeto){
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * 
     * @param obj Objeto a ser adicionado ao ArrayList
     */

    public void addtoObjetos(Objeto obj) {
        Objeto novo = obj;
        objetos.add(novo);
    }
    
    /**
     * 
     * @param pos Posicao a ser adicionada ao ArrayList
     */
    
    public void addToWalk(Coord pos){
        Coord novo = new Coord(pos.getX(),pos.getY());
        walk.add(novo);
    }
    
    public String toString(){
    	String aux="";
    	for(Percepcao o: memoria) {
            aux+="Percepção:\n";
            aux+=o.toString();
    	}
    	return aux;
    }
    
    /**
     * @see numberOcurrences Devolve o numero de ocorrencias do Objeto
     */

    
    public ArrayList<Percepcao> getMemoria() {
        return memoria;
    }

    public void setMemoria(ArrayList<Percepcao> n) {
        memoria = new ArrayList<Percepcao>();
        memoria=n;
    }
    
    public ArrayList <Objeto> getObjetos(){
        return objetos;
    }
    
    public void setObjetos(ArrayList<Objeto> objetos){
        objetos = new ArrayList<Objeto>();
        this.objetos = objetos;
    }
    
    public ArrayList<Coord> getWalk() {
        return walk;
    }

    public void setWalk(ArrayList<Coord> n) {
        walk=n;
    }
}
