
import java.util.ArrayList;

/**
 * Created by tiagobotelho on 14/12/14.
 */
public class Memoria {
    private ArrayList <Percepcao> memoria;
    private ArrayList <Objeto> objetos;  /*TODO Adicionar contador dos objetos passados*/ 
    private ArrayList <Coord> walk;
    /*TODO calcular distancia percorrida*/

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
            p.imprime_Visao();
            System.out.println("-------------------//--------------");
        }
        System.out.println("------END MEMORY-----------");
    }
    
    public int number_ocurrences(Objeto objeto){
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
    
    public void add_to_walk(Coord pos){
        Coord novo = pos;
        walk.add(novo);
    }

    public void imprimeObjetos() {
        ArrayList <Objeto> aux = new ArrayList();
        System.out.println("-------OBJETOS----------");
        for (Objeto m : objetos) {
            if(!aux.contains(m)){
                System.out.println(m.toString());
                System.out.println("Número de vezes: " + number_ocurrences(m));
                aux.add(m);
            }
        }
        System.out.println("---------END OBJETOS-----------");
    }
    
    public void print_walk(){
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
