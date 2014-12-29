import java.util.ArrayList;

public class Percepcao {

    private ArrayList <Objeto> visao;
    
    public Percepcao() {
        visao=new ArrayList<Objeto>();
    }
    public Percepcao(ArrayList<Objeto> visao){
        this.visao = visao;
    }

    public void insereObjetoP(Objeto obj) {
        Objeto novo = obj;
        visao.add(novo);
    }
    public void imprimeVisao() {
        if(visao.isEmpty()==false){
            System.out.println("Percepção: ");
        }
        for(Object o :visao) {
            System.out.println(o.toString());
        }
    }

    public ArrayList<Objeto> getVisao() {
        return visao;
    }

    public void setVisao(ArrayList<Objeto> n) {
        visao=n;
    }
}
