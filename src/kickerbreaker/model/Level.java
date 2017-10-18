package kickerbreaker.model;

import java.util.ArrayList;

/**
 * Created by karina on 18-10-2017.
 */
public class Level {
    public int number;
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    public Level(int value){
        this.number = value;
    }

    public Level(){}
}
