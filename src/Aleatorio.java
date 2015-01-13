
import java.io.Serializable;
import java.util.Random;

/**
 * Created by tiagobotelho on 14/12/14.
 */

public class Aleatorio extends Agente implements Serializable{


    public Aleatorio(String forma,String cor,Coord pos,int lifespan) {
        super(forma,cor,pos,lifespan);
    }

    public String toString() {
        return "Agente Aleatorio:\n" + super.toString();
    }
    
    public String toString2() {
    	return "Agente Aleatorio "+super.toString2();
    }

    public void move(Ambiente a) { //move-se para a posição de um objeto random da percepção, caso exista algum. Caso contrário vai para uma posição random do mapa
        Random rd = new Random();
        Objeto random;
        if(lifespan>=1){
            lifespan--;
            memory.inserePercepcaoM(perception); //insere percepção na memoria
            if(perception.getVisao().isEmpty()){
                Coord novaPos = this.pos.noObjectCoord(a.getCampoVisao(), a);
                pos.setX(novaPos.getX());
                pos.setY(novaPos.getY());
            }
            else{
                random = perception.getVisao().get(rd.nextInt(perception.getVisao().size()));
                memory.addtoObjetos(random);
                pos.setX(random.getPos().getX());
                pos.setY(random.getPos().getY());
            }
            memory.addToWalk(pos);
        }      
    }
}
