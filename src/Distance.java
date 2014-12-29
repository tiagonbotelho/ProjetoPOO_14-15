import java.util.Random;

public class Distance extends Agente{

    public Distance(String forma,String cor,Coord pos,int lifespan) {
        super(forma, cor, pos,lifespan);
    }

    public String toString() {
        return "Agente Distance:\n"+super.toString();
    }

    public void move(Ambiente a) {
        Random rd = new Random();        
        if(lifespan>=1){
            lifespan--;
            memory.inserePercepcaoM(perception);
            if(perception.getVisao().isEmpty()){
                pos.setX(rd.nextInt(a.getWidth())+1);
                pos.setY(rd.nextInt(a.getHeight())+1);
            }
            else{
                Objeto prox = perception.getVisao().get(0);
                double distancia_menor = pos.distance(prox.getPos());
                for (Objeto obj : perception.getVisao()) {
                    if(pos.distance(obj.getPos())<distancia_menor){
                        prox = obj;
                        distancia_menor = pos.distance(obj.getPos());
                    }
                }
                memory.addtoObjetos(prox);
                pos.setX(prox.getPos().getX());
                pos.setY(prox.getPos().getY());
            }
            this.visionCamp(a);
            memory.addToWalk(pos);
        }
    }
}
