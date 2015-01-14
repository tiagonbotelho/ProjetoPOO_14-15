/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Ambiente implements Serializable{
    private int campoVisao;
    private int width;
    private int height;
    private int lifeSpan;
    private int tamanho;
    private ArrayList <Entidade> entidades;
    
    /**
     * 
     * @param width
     * @param height
     * @param lifeSpan
     * @param campoVisao
     */
        
    public Ambiente(int width,int height,int lifeSpan, int campoVisao){
        this.width  = width;
        this.height = height;
        this.tamanho = width * height;
        this.lifeSpan = lifeSpan;
        this.entidades = new ArrayList <Entidade>();
        this.campoVisao = campoVisao;
    }
    
    /**
     * @see ObjetoRandom Cria um Objeto aleatório com coordenadas limitadas pela largura e altura
     * @see addtoEntidade Adiciona o Objeto aleatório ao ArrayList de Entidades
     */
    
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
    
    /**
     * 
     * @param coord
     * @return false
     */
    
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
    
    /**
     * 
     * @return i
     */

    public int countAgents() {
        int i=0;
        for(Entidade e: entidades) {
            if(e instanceof Agente) {
                i++;
            }
        }
        return i;
    }
    
    /**
     * 
     * @param entidade
     * @return boolean Sucesso ou Insucesso da Operação
     */
    
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
    
    /**
     * @see visionCamp Atualiza a Percepcao do Agente
     */
    
    public void updateAllPerceptions(){
        for(Entidade e:entidades){
            if(e instanceof Agente){
                ((Agente)e).visionCamp(this);
            }
        }
    }
    
    /**
     * 
     * @param id Recebe o ID da Entidade Correspondente
     */
    
    public void deleteEntity(int id){
        Iterator<Entidade> iterador = entidades.iterator();
        while(iterador.hasNext()){
            Entidade aux = iterador.next();
            if(aux.getId() == id){
                iterador.remove();
            }
        }
    }
    
    /**
     * @see move Movimenta o Agente
     */
    
    public void moveAgents(){ //Move todos os agentes
        Iterator <Entidade> iterador = entidades.iterator();
        while(iterador.hasNext()){
            Entidade aux = iterador.next();
            if(aux instanceof Agente){    
                ((Agente)aux).move(this);
            }
        }
    }
    
    /**
     * 
     * @param id ID da Entidade
     * @return Entidade Se encontra retorna a Entidade caso contrário retorna null
     * 
     */

    public Entidade getEntityByID(int id){ //Devolve entidade que tenha o id passado como argumento, caso nao exista devolve null
        for(Entidade e:entidades){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    
    /**
     * @see escreveLinha Escreve uma linha no ficheiro
     * @see closeWrite Fecha a escrita
     */
    
  public void writeEMem(){
	  try{
  		FicheirodeTexto mem=new FicheirodeTexto();
  		mem.abreEscrita("memoria.txt");
  		ArrayList <Objeto> objt;
  		for(Entidade aux: entidades) {
  			if(aux instanceof Agente) {
  				objt=((Agente) aux).getMemory().getObjetos();
  				if(objt.isEmpty()==false){
	  				mem.escreveLinha(aux.toString2());
	  				mem.escreveLinha("--------------------------------");
	  				for(Objeto o: objt) {
	  					mem.escreveLinha(o.toString()+"\n");
	  				}
  				}
  				else{
	  				mem.escreveLinha(aux.toString2());
	  				mem.escreveLinha("--------------------------------");
	  				mem.escreveLinha("MEMORY IS EMPTY!");	
  				}
  				mem.escreveLinha("--------------------------------");
  			}
  		}
  		mem.closeWrite();
  	}catch(IOException e){
  		e.printStackTrace();
  	}
   }
  
  /**
   * @see escreveLinha Escreve uma linha no ficheiro Correspondente
   * @see closeWrite Fecha a Escrita no Ficheiro Correspondente
   */
  
  public void writeEPreception(){
	  int count=0;
	  try{
		  FicheirodeTexto  mem=new FicheirodeTexto();
		  mem.abreEscrita("preception.txt");
		  ArrayList <Percepcao> aux;
		  ArrayList <Objeto> obj;
		  for(Entidade helper: entidades) {
			  if(helper instanceof Agente) {
				  aux=((Agente) helper).getMemory().getMemoria();
				  count=1;
				  if(aux.isEmpty()==false) {
					  mem.escreveLinha(helper.toString2());
					  mem.escreveLinha("--------------------------------------");
					  for(Percepcao p: aux) {
						  mem.escreveLinha("----------- [P"+count+"] -----------");
						  obj=p.getVisao();
						  for(Objeto o: obj) {
							  mem.escreveLinha(o.toString()+"\n");
						  }
						  mem.escreveLinha("----------------//-----------------");
						  count++;
					  }
				  }
				  else {
					  mem.escreveLinha(helper.toString2());
					  mem.escreveLinha("--------------------------------------");
					  mem.escreveLinha("Perception Empty!");
				  }
				  mem.escreveLinha("--------------------------------------\n");
			  }
		  }
		  mem.closeWrite();
	  }catch(IOException e) {
	  }
  }
  
  /**
   * @see abreEsrita Abre a escrita a um ficheiro Correspondente
   * @see escreveLinha Escreve Linha no ficheiro correspondente
   * @see closeWrite Fecha o ficheiro correspondente para Escrita 
   */
  
  public void writeEWalk(){
	  try{
		  FicheirodeTexto mem= new FicheirodeTexto();
		  mem.abreEscrita("walk.txt");
		  ArrayList <Coord> aux;
		  for(Entidade helper: entidades) {
			  if(helper instanceof Agente) {
				  aux=((Agente) helper).getMemory().getWalk();
				  if(aux.isEmpty()==false) {
					  mem.escreveLinha(helper.toString2());
					  mem.escreveLinha("--------------------------------------");
					  for(Coord c: aux) {
						  mem.escreveLinha(c.toString());
					  }
				  }
				  else{
					  mem.escreveLinha(helper.toString2());
					  mem.escreveLinha("--------------------------------------");
					  mem.escreveLinha("O objeto nao andou");
				  }
				  mem.escreveLinha("--------------------------------------\n");
			  }
		  }
		 mem.closeWrite(); 
	  }catch(IOException e){
		  e.printStackTrace();
	  	}
  	}
  
  /**
   * @see abreEscrita Abre ficheiro correspondente para Escrita
   * @see escreveLinha Escreve uma linha no ficheiro Correspondente
   * @see calcDistance Calcula a distância percorrida pelo Agente
   * @see closeWrite Fecha o ficheiro correspondente para Escrita 
   * 
   */
  
  public void WriteEStat(){
	  ArrayList<Objeto>obj;
	  ArrayList<Objeto>size;
	  try{
		  FicheirodeTexto mem= new FicheirodeTexto();
		  mem.abreEscrita("stats.txt");
		  for(Entidade e: entidades) {
			  if(e instanceof Agente) {
				  mem.escreveLinha(e.toString2());
				  mem.escreveLinha("--------------------------------------");
				  if(((Agente) e).calcDistance()!=0){
					  mem.escreveLinha("Distancia Percorrida: "+((Agente) e).calcDistance());
				  }
				  else{
					  mem.escreveLinha("O Agente nao se deslocou");
				  }
				  obj=((Agente) e).getMemory().getObjetos();
				  size=new ArrayList<Objeto>();
				  for(Objeto o: obj) {
					  if(size.indexOf(o)==-1){
						  size.add(o);
					  }
				  }
				  mem.escreveLinha("Numero de Objetos Diferentes: "+size.size());
				  mem.escreveLinha("Numero de Objetos Aprendidos: "+obj.size());
				  mem.escreveLinha("--------------------------------------\n");  
			  }
		  }
		  mem.closeWrite();
	  }catch(IOException e){
		  e.printStackTrace();
	  }
  }
    
  /**
   * @see escreveLinha Escreve uma linha no ficheiro correspondente
   * @see abreEscrita Abre o ficheiro correspondente para escrita
   * @see fechaEscrita Fecha o ficheiro correspondente para escrita
   * @throws IOException
   */
  
    public void saveAmbient() throws IOException{
        FicheirodeTexto config = new FicheirodeTexto();
        FicheirodeObjetos entidades = new FicheirodeObjetos();
        config.abreEscrita("config.txt");
        config.escreveLinha("width = ".concat(Integer.toString(width)));
        config.escreveLinha("height = ".concat(Integer.toString(height)));
        config.escreveLinha("lifespan = ".concat(Integer.toString(lifeSpan)));
        config.escreveLinha("campovisao = ".concat(Integer.toString(campoVisao)));
        config.closeWrite();
        entidades.abreEscrita("entidades.dat");
        entidades.escreveObjeto(this);
        entidades.fechaEscrita();
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

    public int getCampoVisao() {
        return campoVisao;
    }

    public void setCampoVisao(int n) {
        this.campoVisao=n;
    }
}
