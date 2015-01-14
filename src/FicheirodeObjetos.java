
/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class FicheirodeObjetos implements Serializable{
	private ObjectInputStream iS;
	private ObjectOutputStream oS;
	
	/**
	 * 
	 * @param nomeDoFicheiro nome do Ficheiro em que estamos a trabalhar atualmente
	 * @throws IOException
	 */
	
	public void abreLeitura(String nomeDoFicheiro) throws IOException
	{
		iS= new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
	}
	
	/**
	 * 
	 * @param nomeDoFicheiro nome do Ficheiro em que estamos a trabalhar atualmente
	 * @throws IOException
	 */
	
	public void abreEscrita(String nomeDoFicheiro) throws IOException
	{
		oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
	}
	
	/**
	 * 
	 * @return Object Retorna o Objeto lido no ficheiro
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
    public Object leObjeto() throws IOException, ClassNotFoundException{
        return iS.readObject();
    }
    
    /**
     * 
     * @param objeto Escreve no ficheiro correspondente o Objeto
     * @throws IOException
     */
    
    public void escreveObjeto(Object objeto) throws IOException{
        oS.writeObject(objeto);
    }
    
    /**
     * 
     * @return Memoria Retorna do Objeto Lido
     * @throws IOException
     * @throws ClassNotFoundException
     */
    
	public Memoria leMemoria() throws IOException, ClassNotFoundException
	{
		return (Memoria) iS.readObject();
	}
	
	/**
	 * 
	 * @param m Memoria que tencionamos escrever no ficheiro correspondente
	 * @throws IOException
	 */
	
	public void escreveMemoria(Memoria m) throws IOException
	{
		oS.writeObject(m);
	}
	
	/**
	 * 
	 * @param a Escreve o agente no ficheiro correspondente
	 * @throws IOException
	 */
	
	public void escreveAgente(Agente a) throws IOException
	{
		oS.writeObject(a);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	
	public void fechaLeitura() throws IOException
	{
            iS.close();
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	
	public void fechaEscrita() throws IOException
	{
            oS.close();
	}

}
