package kickerbreaker.model;

/**
 * Created by karina on 17-10-2017.
 */
public class Enemy {
    private boolean destroyed;
    private boolean hit;
    private int x;
    private int y;
    private int hp;

    public Enemy() {
        destroyed = false;
        hit = false;
    }

    public void setHit(boolean value) {
        this.hit = value;
    }

    public boolean isHit() {
        return hit;
    }

    public Enemy(int x, int y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        destroyed = false;
    }

    public void enemyHit() {
        hp--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHP() {
        return hp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean value) {
        destroyed = value;
    }
}
