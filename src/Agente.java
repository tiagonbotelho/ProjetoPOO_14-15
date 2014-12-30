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
    
    public int calcDistance() {
    	int acc=0;
    	int i=0;
    	double aux;
    	Coord comp1;
    	Coord comp2;
    	ArrayList <Coord> walk = this.getMemory().getWalk();
    	int size = walk.size();
    	double x;
    	double y;
    	while(i<size-1) {
    		comp1=walk.get(i);
    		comp2=walk.get(i+1);
    		x=(double)comp2.getX() - (double)comp1.getX();
    		y=(double)comp2.getY() - (double)comp1.getY();
    		x=Math.pow(x, 2.0);
    		y=Math.pow(y, 2.0);
    		aux=Math.sqrt(x+y);
    		acc+=(int)aux;
    		i++;
    	}
    	return acc;
    }

    public void visionCamp(Ambiente a) { //Atualiza a perception do agente
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
