package kickerbreaker.model;

/**
 * Created by karina on 22-10-2017.
 */
public class Ball {
    private int xdir;
    private int ydir;
    private int x;
    private int y;

    public Ball(){
        xdir = 1;
        ydir = -1;
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }
    public int getXDir() {
        return xdir;
    }
}
