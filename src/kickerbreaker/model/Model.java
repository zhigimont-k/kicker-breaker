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
    public ArrayList<Level> levels = new ArrayList<Level>();
    public Gate playerGate;
    public Gate enemyGate;


    public void levelUp() {
        currentLevel++;
    }

    public void addPlayerGoal() {
        playerGoals++;
        goalScoreIncrement();
    }

    public void nullifyGoals() {
        enemyGoals = 0;
        playerGoals = 0;
    }

    public void breakCombo() {
        comboCounter = 0;
    }

    public int getCombo() {
        return comboCounter;
    }

    public void comboIncrement() {
        comboCounter++;
    }

    public void addEnemyGoal() {
        enemyGoals++;
    }

    public int getEnemyGoals() {
        return enemyGoals;
    }

    public int getPlayerGoals() {
        return playerGoals;
    }

    public Model() {
        playerGate = new Gate();
        enemyGate = new Gate();
        score = 0;
        enemyGoals = 0;
        playerGoals = 0;
        currentLevel = 0;
        comboCounter = 0;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void generateEnemies() {
        int bound = levels.get(currentLevel).enemyList.size();
        for (int index = 0; index < bound; index++) {
            Enemy addedEnemy = new Enemy(levels.get(currentLevel).enemyList.get(index).getX(),
                    levels.get(currentLevel).enemyList.get(index).getY(),
                    levels.get(currentLevel).enemyList.get(index).getHP());
            enemies.add(addedEnemy);
        }
    }

    public int getNumberOfLevels() {
        return levels.size();
    }

    public int getScore() {
        return score;
    }

    public void destructionScoreIncrement() {
        score += 5 * comboCounter;
    }

    public void goalScoreIncrement() {
        score += 10;
    }

    public void clearEnemyList() {
        enemies.clear();
    }

}
