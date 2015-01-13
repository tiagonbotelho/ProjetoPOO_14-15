
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Ambiente {
    private int campoVisao;
    private int width;
    private int height;
    private int lifeSpan;
    private int tamanho;
    private ArrayList <Entidade> entidades;
        
    public Ambiente(int width,int height,int lifeSpan, int campoVisao){
        this.width  = width;
        this.height = height;
        this.tamanho = width * height;
        this.lifeSpan = lifeSpan;
        this.entidades = new ArrayList <Entidade>();
        this.campoVisao = campoVisao;
    }
    
    public void preencheAmbiente(){ //Preenche 1/5 do ambiente com objetos random
        Objeto aux;
        for(int i = 0;i<tamanho/5;i++){
            aux = new Objeto();
            aux.ObjetoRandom(width, height);
            if(this.addEntidade(aux)==false){
              i--;  
            }
        }
    }
    public boolean checkCoord(Coord coord){
        for(Entidade e : entidades){
            if(e instanceof Objeto){
                if(coord.getX() == e.getPos().getX() && coord.getY() == e.getPos().getY()){
                    return true;
                }
            }
        }
        return false;
    }

    public int countAgents() {
        int i=0;
        for(Entidade e: entidades) {
            if(e instanceof Objeto) {
                i++;
            }
        }
        return i;
    }
    
    public boolean addEntidade(Entidade entidade){
        if(entidade instanceof Agente || this.checkCoord(entidade.getPos()) == false){ //se for agente não precisa de verificar a posição
            int posicao = entidades.size(); //assume que a posição em que a entidade vai ser inserida seja a ultima
            entidade.setId(entidade.addID(this)); //calcula o id
            for(Entidade e:entidades){
                if(e.getId()>entidade.getId()){  //quando encontrar id superior ao seu insere nessa posição.
                    posicao = entidades.indexOf(e);
                    break;
                }
            }
            entidades.add(posicao,entidade);
            return true;
        }
        else{
            return false;
        }
    }
    
    public void updateAllPerceptions(){
        for(Entidade e:entidades){
            if(e instanceof Agente){
                ((Agente)e).visionCamp(this);
            }
        }
    }
    
    
    public void deleteEntity(int id){
        Iterator<Entidade> iterador = entidades.iterator();
        while(iterador.hasNext()){
            Entidade aux = iterador.next();
            if(aux.getId() == id){
                iterador.remove();
            }
        }
    }
    
    public void moveAgents(){ //Move todos os agentes
        Iterator <Entidade> iterador = entidades.iterator();
        while(iterador.hasNext()){
            Entidade aux = iterador.next();
            if(aux instanceof Agente){    
                ((Agente)aux).move(this);
            }
        }
    }
    
    public void saveAmbient() throws IOException{
        File configfile = new File("config.txt");
        FicheirodeTexto config = new FicheirodeTexto();
        if(!configfile.exists()){
            configfile.createNewFile();
        }
        config.abreEscrita("config.txt");
        config.escreveLinha("width = ".concat(Integer.toString(width)));
        config.escreveLinha("height = ".concat(Integer.toString(height)));
        config.escreveLinha("lifespan = ".concat(Integer.toString(lifeSpan)));
        config.escreveLinha("campovisao = ".concat(Integer.toString(campoVisao)));
        config.closeWrite();
    }

    public Entidade getEntityByID(int id){ //Devolve entidade que tenha o id passado como argumento, caso nao exista devolve null
        for(Entidade e:entidades){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    
    void printAgents(){
        for(Entidade entity : entidades){
            if(entity instanceof Agente){
                System.out.println(entity);
            }
        }
    }
    
    public int getWidth() {
        return width;
    }
    public void setWidth(int n) {
        width=n;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int n) {
        height=n;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(int n) {
        lifeSpan=n;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int n) {
        tamanho=n;
    }

    public ArrayList<Entidade> getEntidades() {
        return entidades;
    }

    public void setEntidades(ArrayList<Entidade> n) {
        entidades=new ArrayList<Entidade>();
        entidades=n;
    }

    public void imprimeLista() {
        for(Entidade e : entidades) {
            System.out.println(e.toString());
        }
    }


    public int getCampoVisao() {
        return campoVisao;
    }

    public void setCampoVisao(int n) {
        this.campoVisao=n;
    }
}
