import java.io.Serializable;
import java.util.Random;

public class Distance extends Agente implements Serializable{

    public Distance(String forma,String cor,Coord pos,int lifespan) {
        super(forma, cor, pos,lifespan);
    }

    public String toString() {
        return "Agente Distance:\n"+super.toString();
    }
    
    public String toString2() {
    	return "Agente Distance "+super.toString2();
    }

    /**
     * @param a Ambiente em que estamos a trabalhar atualmente
     * @see inserePercepcaoM Insere a Percepcao no ArrayList de memorias
     * @see noObjectCoord Se nao exisir nenhum objeto valido no seu campo de Visão e-lhe atribuida uma nova Posicao
     * @see distance Devolve-nos a distância entre dois objetos
     * @see addtoObjetos Adiciona o Objeto novo ao ArrayList de Objetos
     * @see addToWalk Adiciona a nova Coordenada ao ArrayList de Coordenadas
     */
    
    public void move(Ambiente a) {
        Random rd = new Random();        
        if(lifespan>=1){
            lifespan--;
            memory.inserePercepcaoM(perception);
            if(perception.getVisao().isEmpty()){
                Coord novaPos = this.pos.noObjectCoord(a.getCampoVisao(), a);
                pos.setX(novaPos.getX());
                pos.setY(novaPos.getY());
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
            memory.addToWalk(pos);
        }
    }
}
