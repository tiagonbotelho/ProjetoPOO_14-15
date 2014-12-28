import java.util.Random;

/**
 * Created by tiagobotelho on 14/12/14.
 */

public class Aleatorio extends Agente{


    public Aleatorio(String forma,String cor,Coord pos,int lifespan) {
        super(forma,cor,pos,lifespan);
    }

    public String toString() {
        return "Agente Aleatorio:\n" + super.toString();
    }

    public void move(Ambiente a) { //move-se para a posição de um objeto random da percepção, caso exista algum. Caso contrário vai para uma posição random do mapa
        Random rd = new Random();
        Objeto random;
        if(lifespan>=1){
            lifespan--;
            memory.inserePercepcaoM(perception); //insere percepção na memoria
            if(perception.getVisao().isEmpty()){  
                pos.setX(rd.nextInt(a.getWidth())+1);
                pos.setY(rd.nextInt(a.getHeight())+1);
            }
            else{
                random = perception.getVisao().get(rd.nextInt(perception.getVisao().size()));
                memory.addtoObjetos(random);
                pos.setX(random.getPos().getX());
                pos.setY(random.getPos().getY());
            }
            this.vision_camp(a);
            memory.add_to_walk(pos);
        }      
    }
}
