
/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Percepcao implements Serializable{

    private ArrayList <Objeto> visao;
    
    public Percepcao() {
        visao=new ArrayList<Objeto>();
    }
    public Percepcao(ArrayList<Objeto> visao){
        this.visao = visao;
    }

    /**
     * 
     * @param obj Objeto a ser inserido no ArrayList da visao
     */
    
    public void insereObjetoP(Objeto obj) {
        visao.add(obj);
    }
    

    public ArrayList<Objeto> getVisao() {
        return visao;
    }

    public void setVisao(ArrayList<Objeto> n) {
        visao=n;
    }
    
    public String toString() {
    	String aux="";
    	for(Objeto o: visao) {
            aux += o.toString();
    	}
    	return aux;
    }
}
