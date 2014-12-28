import java.math.*;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Coord nova) { //calcula a distancia entre duas coords
        double distancia;
        distancia=Math.pow((nova.x -  this.x),2.0) + Math.pow((nova.y - this.y), 2.0);
        distancia=Math.sqrt(distancia);

        return distancia;
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
