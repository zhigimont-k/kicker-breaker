package kickerbreaker.model;

import java.util.ArrayList;

/**
 * Created by karina on 01-10-2017.
 */
public class Model {

    private int score;
    private int playerGoals;
    private int enemyGoals;
    private int currentLevel;
    private int comboCounter;
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public Gate playerGate;
    public Gate enemyGate;


    public void levelUp(){
        currentLevel++;
    }

    public void addPlayerGoal(){
        playerGoals++;
        goalScoreIncrement();
    }

    public void nullifyGoals(){
        enemyGoals = 0;
        playerGoals = 0;
    }

    public void breakCombo(){
        comboCounter = 0;
    }

    public int getCombo(){
        return comboCounter;
    }

    public void comboIncrement(){
        comboCounter++;
    }
    public void addEnemyGoal(){
        enemyGoals++;
    }

    public int getEnemyGoals(){return enemyGoals;}

    public int getPlayerGoals(){return playerGoals;}

    public Model(){
        playerGate = new Gate();
        enemyGate = new Gate();
        score = 0;
        enemyGoals = 0;
        playerGoals = 0;
        currentLevel = 1;
        comboCounter = 0;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public void generateEnemies(){
        switch(currentLevel){
            case 1:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        enemies.add(new Enemy(j * 40 + 30, i * 30 + Const.SPRITE_HEIGHT * 3));
                    }
                }

                System.out.println("model.enemies.size() = "+enemies.size());
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        enemies.add(new Enemy(j * 40 + 30, i * 30 + Const.SPRITE_HEIGHT * 3));
                    }
                }

                System.out.println("model.enemies.size() = "+enemies.size());
                break;
            case 3:
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 5; j++) {
                        enemies.add(new Enemy(j * 40 + 40, i * 30 + Const.SPRITE_HEIGHT * 3));
                    }
                }
                break;
            default:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        enemies.add(new Enemy(j * 40 + 30, i * 10 + 50));
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
            case 3:
                number = 35;
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

    public void destructionScoreIncrement(){
        score+=5 * comboCounter;
    }

    public void goalScoreIncrement(){
        score+=10;
    }

    public void clearEnemyList(){
        enemies.clear();
    }

}
