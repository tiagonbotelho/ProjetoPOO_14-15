
import java.util.*;

public abstract class Agente extends Entidade{
    protected Percepcao perception;
    protected Memoria memory;
    protected int lifespan;

    public Agente(String forma,String cor,Coord pos,int lifespan){
        super(forma,cor,pos);
        perception = new Percepcao();
        memory = new Memoria();
        this.lifespan = lifespan;
    }


    public void move(Ambiente a) {

    }

    public void vision_camp(Ambiente a) { //Atualiza a perception do agente
        Percepcao test = new Percepcao();
        for(Entidade e: a.getEntidades()){ //Adiciona na percepcao todos os objetos com a distancia inferior ao campodevisao e superior a 0
            if(pos.distance(e.getPos())<=a.getCampoVisao() && pos.distance(e.getPos())>0){
                if(e instanceof Objeto){
                   test.insereObjetoP((Objeto)e);
                }
            }
        }
        perception = test;
    }
    
    @Override
    public String toString(){
        return "Lifespan: " + lifespan + "\n"+ super.toString();
    }

    public Percepcao getPerception() {
        return perception;
    }

    public void setPerception(Percepcao n) {
        perception=n;
    }

    public Memoria getMemory() {
        return memory;
    }

    public void setMemory(Memoria n) {
        memory=n;
    }
    
    public int getlifeSpan(){
        return lifespan;
    }
    
    public void setlifeSpan(int lifespan){
        this.lifespan = lifespan;
    }
}
