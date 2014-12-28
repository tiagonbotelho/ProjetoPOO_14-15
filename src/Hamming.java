
import java.util.Random;

/**
 * Created by tiagobotelho on 14/12/14.
 */

public class Hamming extends Agente{

    public Hamming(String forma,String cor,Coord pos,int lifespan) {
        super(forma, cor, pos,lifespan);
    }

    @Override
    public String toString() {
        return "Agente Hamming:\n"+super.toString();
    }

    @Override
    public void move(Ambiente a) {
    	Random rd = new Random();
    	Objeto hamm = new Objeto();
    	int aux;
    	int final_aux=0;
    	if(lifespan>=1) {
            lifespan--;
            memory.inserePercepcaoM(perception);
            //se o campo de visão estiver vazio salta para uma posicao random
            if(perception.getVisao().isEmpty()) { 
                    System.out.println("Saltei random");
                    pos.setX(rd.nextInt(a.getWidth())+1);
                    pos.setY(rd.nextInt(a.getHeight())+1);
            }
            else {
                //se o agente não tiver objetos captados move-se para um objeto random
                if(memory.getObjetos().isEmpty()){
                    hamm = perception.getVisao().get(rd.nextInt(perception.getVisao().size()));
                }
                else{
                    for(Objeto obj: perception.getVisao()) {  //comparacao de todos os objetos no campo de visão 
                        aux = 4; //valor maximo que calHamming() possa ter é 3
                        for(Objeto mobj: memory.getObjetos()) { //com todos os objetos captados para calcHamming  
                            if(obj.calcHamming(mobj)<aux){ 
                                aux = obj.calcHamming(mobj);
                            }
                        }
                        if(final_aux <= aux) {
                                final_aux = aux;
                                hamm=obj;
                        }
                    }
                }
                memory.addtoObjetos(hamm);
                pos.setX(hamm.pos.getX()); //adiciona na memoria e salta para o objeto correspondente (hamm)
                pos.setY(hamm.pos.getY());
            }
            this.vision_camp(a);
            memory.add_to_walk(pos);
    	}
    }
}