package kickerbreaker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karina on 18-10-2017.
 */
public class Level {
    public List<Enemy> enemyList = new ArrayList<Enemy>();
    public int number;

    public Level(List<Enemy> enemyList, int number) {
        for (Enemy enemy : enemyList){
            Enemy addedEnemy = new Enemy(enemy.getX(), enemy.getY(), enemy.getHP());
            this.enemyList.add(addedEnemy);
            this.number = number;
        }
    }

    public Level(){}

    public void levelUp() {
    }

    public Level getCurrentLevel() {
        return this;
    }

    public void generateLevel(List<Level> levelList, int number){
        for (Enemy enemy : levelList.get(number).enemyList){
            Enemy addedEnemy = new Enemy(enemy.getX(), enemy.getY(), enemy.getHP());
            this.enemyList.add(addedEnemy);
            this.number = number;
        }
    }

    public int getNumber(){
        return number;
    }


    public void clearEnemyList() {
        enemyList.clear();
    }
}
