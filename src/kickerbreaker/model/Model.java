package kickerbreaker.model;

import kickerbreaker.view.Enemy;

import java.util.ArrayList;

import static kickerbreaker.model.Const.*;

/**
 * Created by karina on 01-10-2017.
 */
public class Model {

    private int score;
    private int goals;
    private int currentLevel;
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();


    public void levelUp(){
        currentLevel++;
    }

    public Model(){
        score = 0;
        goals = 0;
        currentLevel = 1;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public void generateEnemies(){
        int k = 0;
        switch(currentLevel){
            case 1:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        enemies.add(new Enemy(j * 40 + 30, i * 30 + Const.SPRITE_HEIGHT * 3));
                        k++;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        enemies.add(new Enemy(j * 40 + 30, i * 30 + Const.SPRITE_HEIGHT * 3));
                        k++;
                    }
                }
                break;
            default:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        enemies.add(new Enemy(j * 40 + 30, i * 10 + 50));
                        k++;
                    }
                }
                break;
        }

    }

    public int getEnemyNumber(){
        int number;
        switch(currentLevel){
            case 1:
                number = 30;
                break;
            case 2:
                number = 36;
                break;
            default:
                number = 30;
                break;

        }
        return number;
    }

    public int getScore(){
        return score;
    }

    public void destructionIncrement(){
        score+=5;
    }

    public void goalIncrement(){
        score+=10;
    }

    public void clearEnemyList(){
        for (int index = enemies.size() - 1; index <= 0; index--){
            enemies.remove(index);
        }
    }

}
