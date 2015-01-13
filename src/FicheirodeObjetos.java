import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FicheirodeObjetos {
	private ObjectInputStream iS;
	private ObjectOutputStream oS;
	
	public void abreLeitura(String nomeDoFicheiro) throws IOException
	{
		iS= new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
	}
	
	public void abreEscrita(String nomeDoFicheiro) throws IOException
	{
		oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
	}
	
	public Memoria leMemoria() throws IOException, ClassNotFoundException
	{
		return (Memoria) iS.readObject();
	}
	
	public void escreveMemoria(Memoria m) throws IOException
	{
		oS.writeObject(m);
	}
	
	public void escreveAgente(Agente a) throws IOException
	{
		oS.writeObject(a);
	}
	
	public void fechaLeitura() throws IOException
	{
		iS.close();
	}
	
	public void fechaEscrita() throws IOException
	{
		oS.close();
	}

}
