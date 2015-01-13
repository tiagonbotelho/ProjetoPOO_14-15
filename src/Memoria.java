
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
    
    public void inserePercepcaoM(Percepcao perception){
        Percepcao novo = perception;
        memoria.add(novo);
    }

    public void imprimeMemoria() {
        System.out.println("----------------------MEMORY\n");
        for (Percepcao p : memoria) {
            System.out.println("--------////------------");
            p.imprimeVisao();
            System.out.println("-------------------//--------------");
        }
        System.out.println("------END MEMORY-----------");
    }
    
    public int numberOcurrences(Objeto objeto){
        int counter = 0;
        for(Objeto obj:objetos){
            if(obj == objeto){
                counter++;
            }
        }
        return counter;
    }

    public void addtoObjetos(Objeto obj) {
        Objeto novo = obj;
        objetos.add(novo);
    }
    
    public void addToWalk(Coord pos){
        Coord novo = new Coord(pos.getX(),pos.getY());
        walk.add(novo);
    }

    public void imprimeObjetos() {
        ArrayList <Objeto> aux = new ArrayList<Objeto>();
        System.out.println("-------OBJETOS----------");
        for (Objeto m : objetos) {
            if(!aux.contains(m)){
                System.out.println(m.toString());
                System.out.println("Número de vezes: " + numberOcurrences(m));
                aux.add(m);
            }
        }
        System.out.println("---------END OBJETOS-----------");
    }
    
    public void printWalk(){
        for(Coord i: walk){
            System.out.println(i);
        }
    }

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
