
/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

import java.io.Serializable;
import java.util.Random;

public class Hamming extends Agente implements Serializable{

    public Hamming(String forma,String cor,Coord pos,int lifespan) {
        super(forma, cor, pos,lifespan);
    }

    @Override
    public String toString() {
        return "Agente Hamming:\n"+super.toString();
    }
    
    public String toString2() {
    	return "Agente Hamming "+super.toString2();
    }

    @Override
    /**
     * @param a Ambiente em que estamos a trabalhar atualmente
     * @see inserePercepcaoM Atualiza a Percepcao do agente correspondente
     * @see getVisao Obtem a visao de Objetos dentro do seu Campo de Visao
     * @see noObjectCoord Caso nao exista nenhum objeto válido no seu Campo de Visao é dada uma Posição Aleatória dentro do seu Campo de Visao
     * @see calcHamming devolve a Diferenca de Hamming de dois Objetos
     * @see addToObjetos Adiciona o Objeto ao ArrayList de Objetos
     * @see addToWalk Adiciona as Coordenadas atuais ao ArrayList de Coordenadas
     */
    
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
                Coord novaPos = this.pos.noObjectCoord(a.getCampoVisao(), a);
                pos.setX(novaPos.getX());
                pos.setY(novaPos.getY());
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
            memory.addToWalk(pos);
    	}
    }
}