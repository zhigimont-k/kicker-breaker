package kickerbreaker.model;

/**
 * Created by karina on 17-10-2017.
 */
public class Enemy {
    private boolean destroyed;
    private int x;
    private int y;
    //private int hp;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        destroyed = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public boolean isDestroyed() {

        return destroyed;
    }

    public void setDestroyed(boolean value) {

        destroyed = value;
    }
}
