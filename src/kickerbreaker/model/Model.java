package kickerbreaker.model;

import kickerbreaker.view.Enemy;

import static kickerbreaker.model.Const.N_OF_BRICKS;

/**
 * Created by karina on 10-10-2017.
 */
public class Model {

    public Enemy enemies[];
    private int score;
    private int goals;

    public Model(){
        score = 0;
        goals = 0;
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

}
