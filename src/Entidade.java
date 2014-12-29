
/**
 * Created by tiagobotelho on 14/12/14.
 */
public abstract class Entidade {
    protected int id;
    protected String forma;
    protected String cor;
    protected Coord pos;
    protected static String cores[] = {"Azul","Amarelo","Vermelho","Verde","Laranja","Preto","Branco","Cinzento","Roxo"}; 
    protected static String formas[] = {"Quadrado","Retângulo","Círculo","Triângulo","Tetaedro","Losango","Pentágono","Hexágono"};
    public Entidade(String forma, String cor,  Coord pos) {
        this.id=0;
        this.forma=forma;
        this.cor=cor;
        this.pos=pos;
    }
    public Entidade(){
        
    }

    @Override
    public String toString() { /*FIXME tostrings*/
        return "ID: "+id+" Forma: "+forma+" Cor: "+cor+ " Coordinates: "+ pos.toString();
    }

    public int add_ID(Ambiente a){ 
        int new_id = 1;
        for(Entidade e:a.getEntidades()){  //Percorre toda lista até encontrar um id nao utilizado
            if(e.getId()==new_id){
                new_id++;
            }
            else{
                break;
            }
        }
        return new_id; 
    }
    public int getId(){
        return id;
    }
    public String getForma(){
        return forma;
    }
    public String getCor(){
        return cor;
    }
    public Coord getPos(){
        return pos;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setForma(String forma){
        this.forma = forma;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setCoord(Coord pos){
        this.pos = pos;
    }
}
