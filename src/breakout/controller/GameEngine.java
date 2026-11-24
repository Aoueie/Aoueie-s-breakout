package breakout.controller;

import breakout.model.GameState;
import breakout.model.Ball;
import breakout.model.Brick;
import breakout.model.Paddle;
import breakout.util.Constants;
import breakout.view.BreakoutCanvas;
import breakout.model.storage.HighScoreStorage;
import breakout.model.storage.InMemoryScoreStorage;

import java.awt.event.KeyEvent;

public class GameEngine {

    private HighScoreStorage scoreStorage;

    private final Brick[] bricks;
    private final Ball ball;
    private final Paddle paddle;
    private final BreakoutCanvas canvas;

    private int remainingBricks = Constants.BRICK_COLS * Constants.BRICK_ROWS;
    private int score = 0;
    private int lives = Constants.INITIAL_LIVES;
    private GameState state;
    private boolean leftPressed;
    private boolean rightPressed;

    public GameEngine(BreakoutCanvas canvas) {
        this.canvas = canvas;
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.bricks = new Brick[Constants.BRICK_ROWS * Constants.BRICK_COLS];
        createBricks();
        canvas.setupBricks(bricks);
        this.state = GameState.READY;
        this.scoreStorage = new InMemoryScoreStorage();
        ball.placeOnPaddle(paddle);
        render();
    }

    public void tick() {
        update();
        render();
    }

    private void update() {
        switch (state) {
            case READY:
                movePaddle();
                ball.placeOnPaddle(paddle);
                break;
            case RUNNING:
                ball.move();
                movePaddle();
                handleWallCollisions();
                handlePaddleCollision();
                handleBrickCollisions();
                handleBottomBoundary();
                break;
        }
    }

    private void resetGame(){
        paddle.reset();
        score = 0;
        lives = Constants.INITIAL_LIVES;
        remainingBricks = Constants.BRICK_COLS * Constants.BRICK_ROWS;
        for (int i = 0; i < Constants.BRICK_COLS * Constants.BRICK_ROWS; i++){
            bricks[i].reset();
        }
        state = GameState.READY;
    }

    private void movePaddle(){
        if (leftPressed) {
            paddle.moveLeft();
        }
        if (rightPressed) {
            paddle.moveRight();
        }
    }

    private void createBricks(){
        for (int i = 0; i < Constants.BRICK_ROWS * Constants.BRICK_COLS; i++){
            int row = i / Constants.BRICK_COLS;
            int col = i % Constants.BRICK_COLS;
            if (i / Constants.BRICK_COLS < 2){
                bricks[i] = new Brick(Constants.BRICK_OFFSET_X + col * Constants.BRICK_STEP_X, Constants.BRICK_OFFSET_Y + row * Constants.BRICK_STEP_Y, row, Constants.STRONG_BRICK_HP
                );
            }else {
                bricks[i] = new Brick(Constants.BRICK_OFFSET_X + col * Constants.BRICK_STEP_X, Constants.BRICK_OFFSET_Y + row * Constants.BRICK_STEP_Y, row, Constants.NORMAL_BRICK_HP
                );
            }
        }
    }

    private void handleBrickCollisions(){
        for (int i = 0; i < Constants.BRICK_ROWS * Constants.BRICK_COLS; i++) {
            if (bricks[i].isDestroyed()) {
                continue;
            }
            double overlapLeft = ball.getX() + ball.getDiameter() - bricks[i].getX();
            double overlapRight = bricks[i].getX() + bricks[i].getWidth() - ball.getX();
            double overlapTop = ball.getY() + ball.getDiameter() - bricks[i].getY();
            double overlapBottom = bricks[i].getY() + bricks[i].getHeight() - ball.getY();
            double minOverlapX = Math.min(overlapLeft, overlapRight);
            double minOverlapY = Math.min(overlapTop, overlapBottom);
            if (overlapBottom > 0 && overlapLeft > 0 && overlapRight > 0 && overlapTop > 0) {
                if (bricks[i].hit()){
                    score += Constants.BRICK_SCORE;
                    remainingBricks--;
                }
                if (minOverlapX < minOverlapY) {
                    ball.reverseX();
                    return;
                } else {
                    ball.reverseY();
                    return;
                }
            }
        }
        if (remainingBricks == 0){
            scoreStorage.saveHighScore(score);
            state = GameState.VICTORY;
        }
    }

    private void handleBottomBoundary(){
        if (ball.getY() + ball.getDiameter() >= Constants.CANVAS_HEIGHT){
            --lives;
            if (lives > 0){
                state = GameState.READY;
            }else{
                scoreStorage.saveHighScore(score);
                System.out.println("GAME OVER!");
                state = GameState.GAME_OVER;
            }
        }
    }

    private void handlePaddleCollision(){
        if ((ball.getY() + ball.getDiameter() >= paddle.getY() && ball.getY() < paddle.getY() ) && (ball.getX() +ball.getDiameter() > paddle.getX() && ball.getX() < paddle.getX() + paddle.getWidth()) && ball.getVy() > 0){
            ball.bounceOffPaddle(paddle);
        }
    }

    private void handleWallCollisions(){
        if (ball.getX() < 0 || ball.getX() + ball.getDiameter() > Constants.CANVAS_WIDTH){
            ball.reverseX();
            ball.getOnCanvas();
        }
        if (ball.getY() < Constants.HUD_HEIGHT){
            ball.reverseY();
        }
    }

    private void render() {
        canvas.render(paddle, ball, bricks, score, lives, scoreStorage.loadHighScore(), state);
    }

    public void onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (keyCode == KeyEvent.VK_SPACE && state == GameState.READY){
            ball.launch();
            state = GameState.RUNNING;
        } else if (keyCode == KeyEvent.VK_P){
            if (state == GameState.RUNNING){
                state = GameState.PAUSED;
            } else if (state == GameState.PAUSED){
                state = GameState.RUNNING;
            }
        } else if (keyCode == KeyEvent.VK_R){
            resetGame();
        }
    }

    public void onKeyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}