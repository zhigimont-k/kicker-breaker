package kickerbreaker.controller;

import kickerbreaker.model.Const;
import kickerbreaker.model.Model;
import kickerbreaker.view.Board;
import kickerbreaker.view.EnemySprite;
import kickerbreaker.view.Window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import static kickerbreaker.model.Const.*;

/**
 * Created by karina on 04-10-2017.
 */
public class Controller {
    public Board board;
    private Model model;
    private Window window;

    private Timer timer;

    public Controller() {
        this.model = new Model();
        this.window = new Window();
        this.board = new Board();
        window.add(board);
        board.setPreferredSize(new Dimension(Const.WIDTH, Const.HEIGHT));
        window.setContentPane(board);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        model.generateEnemies();
        addEnemiesOnBoard();
        updateStat();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
        board.addKeyListener(new TAdapter());
        board.setFocusable(true);
        board.setDoubleBuffered(true);
    }

    private void stopGame() {

        board.ingame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if (model.getEnemyGoals() == MAX_GOALS) {
            board.combo.setVisible(false);
            board.scoreLabel.setVisible(false);
            board.level.setVisible(false);
            board.goals.setVisible(false);
            board.score = "Score: "+model.getScore();
            stopGame();
        }

        if ((board.playerGate.getRect()).intersects(board.ball.getRect()) && !model.playerGate.isHit) {
            model.addEnemyGoal();
            model.playerGate.isHit = true;
            updateStat();
        }

        if (!((board.playerGate.getRect()).intersects(board.ball.getRect()))) {

            model.playerGate.isHit = false;

        }


        if ((board.enemyGate.getRect()).intersects(board.ball.getRect()) && !model.enemyGate.isHit) {

            model.addPlayerGoal();
            model.enemyGate.isHit = true;
            updateStat();

        }

        if (!((board.enemyGate.getRect()).intersects(board.ball.getRect()))) {

            model.enemyGate.isHit = false;

        }

        for (int i = 0, j = 0; i < model.enemies.size(); i++) {

            if (model.enemies.get(i).isDestroyed()) {
                j++;
            }

            if (j == model.enemies.size() || model.getPlayerGoals() == MAX_GOALS) {

                if (model.getCurrentLevel() == Const.LEVELS){
                    board.message = "Victory";
                    updateStat();
                    stopGame();
                }

                model.clearEnemyList();
                board.clearEnemyList();
                model.nullifyGoals();
                model.breakCombo();
                model.levelUp();
                updateStat();

                model.generateEnemies();
                addEnemiesOnBoard();
                board.player.resetState();
                board.ball.resetState();
            }
        }

        if ((board.ball.getRect()).intersects(board.player.getRect())) {

            model.breakCombo();
            updateStat();
            int playerLPos = (int) board.player.getRect().getMinX();
            int ballLPos = (int) board.ball.getRect().getMinX();

            int first = playerLPos + 8;
            int second = playerLPos + 16;
            int third = playerLPos + 24;
            int fourth = playerLPos + 32;

            if (ballLPos < first) {
                board.ball.setXDir(-1);
                board.ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                board.ball.setXDir(-1);
                board.ball.setYDir(-1 * board.ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                board.ball.setXDir(0);
                board.ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                board.ball.setXDir(1);
                board.ball.setYDir(-1 * board.ball.getYDir());
            }

            if (ballLPos > fourth) {
                board.ball.setXDir(1);
                board.ball.setYDir(-1);
            }
        }

        for (int i = 0; i < model.enemies.size(); i++) {

            if ((board.ball.getRect()).intersects(board.enemies.get(i).getRect())) {

                int ballLeft = (int) board.ball.getRect().getMinX();
                int ballHeight = (int) board.ball.getRect().getHeight();
                int ballWidth = (int) board.ball.getRect().getWidth();
                int ballTop = (int) board.ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!model.enemies.get(i).isDestroyed()) {
                    if (board.enemies.get(i).getRect().contains(pointRight)) {
                        board.ball.setXDir(-1);
                    } else if (board.enemies.get(i).getRect().contains(pointLeft)) {
                        board.ball.setXDir(1);
                    }

                    if (board.enemies.get(i).getRect().contains(pointTop)) {
                        board.ball.setYDir(1);
                    } else if (board.enemies.get(i).getRect().contains(pointBottom)) {
                        board.ball.setYDir(-1);
                    }

                    model.enemies.get(i).setDestroyed(true);
                    board.enemies.get(i).setVisible(false);
                    model.comboIncrement();
                    model.destructionScoreIncrement();
                    updateStat();
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            board.player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            board.player.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {

            board.player.move();
            board.ball.move();
            checkCollision();
            board.repaint();
        }

    }

    public void addEnemiesOnBoard(){
        for (int index = 0; index < model.enemies.size(); index++){
            board.enemies.add(new EnemySprite(model.enemies.get(index).getX(), model.enemies.get(index).getY()));
        }
        System.out.println("board.enemies.size() = "+board.enemies.size());
    }

    public void updateStat(){
        board.combo.setText("x"+model.getCombo());
        board.level.setText("Level "+model.getCurrentLevel());
        board.goals.setText(model.getPlayerGoals()+" : "+model.getEnemyGoals());
        board.scoreLabel.setText("Score: "+model.getScore());
    }


}