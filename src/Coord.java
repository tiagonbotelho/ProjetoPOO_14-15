
/**
 * @author Tiago Botelho
 * @author Pedro Belém
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Coord implements Serializable{
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coord(){
        
    }
    
    /**
     * 
     * @param a Ambiente em que a simulação esta a trabalhar
     * @return boolean Controlo de a variável se é ou não válida (true or false)
     */
    
    public boolean validCoord(Ambiente a){
        if(x>0 && x<=a.getWidth()){
            if(y>0 && y<=a.getHeight()){
                System.out.println("valid "+ x + " " + y);
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param nova Nova coordenada com qual vamos comparar
     * @return double Representa a distancia obtida entre as duas Entidades
     * @see distancia Variável à qual vamos atribuir o valor da Distância
     */

    public double distance(Coord nova) { //calcula a distancia entre duas coords
        double distancia;
        distancia=Math.pow((nova.x -  this.x),2.0) + Math.pow((nova.y - this.y), 2.0);
        distancia=Math.sqrt(distancia);

        return distancia;
    }
    
    /**
     * 
     * @param radius Raio de visao para sabermos os tamanhos onde podemos saltar o Agente
     * @param a Ambiente em que estamos a trabalhar atualmente
     * @return Coord Retorna a nova coordenada Obtida
     */
    
    public Coord noObjectCoord(int radius,Ambiente a){
        Random rd = new Random();
        Coord nova = new Coord(rd.nextInt(radius*2 + 1)+ x - radius,rd.nextInt(radius*2 + 1) + y-radius);
        while(!nova.validCoord(a) || this.distance(nova)<(double)(radius/2) || this.distance(nova)>(double)radius){
            nova.x = rd.nextInt(radius*2 + 1)+ x - radius;
            nova.y = rd.nextInt(radius*2 + 1) + y - radius;
        }
        if(a.getCampoVisao()<=a.getWidth() && a.getCampoVisao()<=a.getHeight()){
            while(!nova.validCoord(a) || this.distance(nova)<(double)(radius/2) || this.distance(nova)>(double)radius){
                nova.x = rd.nextInt(radius*2 + 1)+ x - radius;
                nova.y = rd.nextInt(radius*2 + 1) + y - radius;
                System.out.println("nova - " + nova);
                System.out.println(this.distance(nova));
            }              
        }
        else{
            nova.x = rd.nextInt(a.getWidth() + 1);
            nova.y = rd.nextInt(a.getHeight() + 1);
        }
        return nova;
        
    }
    
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }

    public int getX() {
        return x;
    }

    public void setX (int n) {
        x=n;
    }

    public int getY() {
        return y;
    }

    public void setY(int n) {
        y=n;
    }
}
