package kickerbreaker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karina on 01-10-2017.
 */
public class Model {

    private int score;
    private int playerGoals;
    private int enemyGoals;
    private int levelCounter;
    private Level currentLevel = new Level();
    private int currentLevelNumber;
    private int comboCounter;
    public List<Enemy> enemies = new ArrayList<Enemy>();
    public List<Level> levels = new ArrayList<Level>();
    public Gate playerGate;
    public Gate enemyGate;
    public Ball ball;
    public Player player;

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
        ball = new Ball();
        player = new Player();
        score = 0;
        enemyGoals = 0;
        playerGoals = 0;
        levelCounter = 0;
        currentLevelNumber = 0;
        //currentLevel = levels.get(levelCounter);
        comboCounter = 0;
    }

    public int getCurrentLevelNumber(){return currentLevel.getNumber();}

    public Level getCurrentLevel(){return currentLevel;}

    public void levelUp() {
        currentLevelNumber++;
        //levelCounter++;
        currentLevel.generateLevel(levels, currentLevelNumber);
    }

    public void generateCurrentLevel(){
        currentLevel.generateLevel(levels, currentLevelNumber);
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


}
