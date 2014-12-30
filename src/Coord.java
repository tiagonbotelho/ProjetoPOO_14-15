
import java.util.ArrayList;
import java.util.Random;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coord(){
        
    }
    
    public boolean validCoord(Ambiente a){
        if(x>0 && x<=a.getWidth()){
            if(y>0 && y<=a.getHeight()){
                return true;
            }
        }
        return false;
    }

    public double distance(Coord nova) { //calcula a distancia entre duas coords
        double distancia;
        distancia=Math.pow((nova.x -  this.x),2.0) + Math.pow((nova.y - this.y), 2.0);
        distancia=Math.sqrt(distancia);

        return distancia;
    }
    
    public Coord noObjectCoord(int radius,Ambiente a){
        Random rd = new Random();
        Coord nova = new Coord(rd.nextInt(radius*2)+ x - radius,rd.nextInt(radius*2) + y-radius);
        while(this.distance(nova)<(double)radius/2 && nova.validCoord(a)){
            nova.x = rd.nextInt(radius*2)+ x - radius;
            nova.y = rd.nextInt(radius*2) + y-radius;
        }  /* TODO test*/
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
