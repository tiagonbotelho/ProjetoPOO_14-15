
import java.io.Serializable;
import java.util.Random;

/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

public class Aleatorio extends Agente implements Serializable{

	/**
	 * @param forma Forma do Agente aleatório
	 * @param cor Cor do Agente aleatório
	 * @param pos Posicao do Agente aleatório
	 * @param lifeSpan Tempo de vida do Agente aleatório
	 */
    public Aleatorio(String forma,String cor,Coord pos,int lifespan) {
        super(forma,cor,pos,lifespan);
    }

    public String toString() {
        return "Agente Aleatorio: " + super.toString();
    }
    
    public String toString2() {
    	return "Agente Aleatorio "+super.toString2();
    }
    /**
     * @param a Ambiente em que o Agente Aleatório atua
     * @see noObjectCoord Quando nao tem Objeto no campo de Visao devolve uma Coordenada dentro de metade do raio do Campo de Visao
     * @see addtoObjetos Adiciona um novo Objeto ao ArrayList
     * @see addtoWalk Adiciona uma nova Posicao ao ArrayList das Posicoes por onde o Agente passou
     */
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
            System.out.println(pos);
            memory.addToWalk(pos);
        }      
    }
}
