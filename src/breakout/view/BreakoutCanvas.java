package breakout.view;

import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import breakout.controller.GameEngine;
import breakout.model.Brick;
import breakout.model.Paddle;
import breakout.util.Constants;
import breakout.model.Ball;
import breakout.model.GameState;

import java.awt.Color;

public class BreakoutCanvas {

    private static final Color BACKGROUND_COLOR = new Color(18, 22, 33);
    private static final Color HUD_COLOR = new Color(35, 50, 60);
    private static final Color PADDLE_COLOR = new Color(77, 208, 225);
    private static final Color BALL_COLOR = new Color(250, 3, 172);

    private static final Color[] BRICKS_COLOR = {new Color(28, 243, 153), new Color(89, 228, 19),new Color(227, 234, 13),new Color(237, 114, 12),new Color(239, 14, 14)};

    private GameState state;

    private final GCanvas canvas;
    private final GRect hud;
    private final GLabel scoreLabel;
    private final GLabel highScoreLabel;
    private final GLabel livesLabel;
    private final GLabel messageLabel;
    private final GRect paddleRect;
    private final GOval ballGOval;

    private GRect[] bricksRect = new GRect[Constants.BRICK_ROWS * Constants.BRICK_COLS];

    public BreakoutCanvas(GCanvas canvas) {
        this.canvas = canvas;
        this.canvas.setBackground(BACKGROUND_COLOR);

        this.hud = new GRect(Constants.CANVAS_WIDTH, Constants.HUD_HEIGHT);
        this.hud.setFilled(true);
        this.hud.setFillColor(HUD_COLOR);
        this.hud.setColor(HUD_COLOR);
        this.canvas.add(hud);

        this.scoreLabel = new GLabel("Score: 0");
        this.scoreLabel.setFont("SansSerif-BOLD-16");
        this.scoreLabel.setColor(Color.WHITE);
        this.canvas.add(scoreLabel, 20, 24);

        this.highScoreLabel = new GLabel("High Score: 0");
        this.highScoreLabel.setFont("SansSerif-BOLD-16");
        this.highScoreLabel.setColor(Color.WHITE);
        this.canvas.add(highScoreLabel, 320, 24);

        this.livesLabel = new GLabel("Lives: 3");
        this.livesLabel.setFont("SansSerif-BOLD-16");
        this.livesLabel.setColor(Color.RED);
        this.canvas.add(livesLabel, 690, 24);

        this.messageLabel = new GLabel("");
        this.messageLabel.setFont("SansSerif-BOLD-26");
        this.messageLabel.setColor(new Color(255, 255, 255));
        this.canvas.add(messageLabel);

        this.paddleRect = new GRect(Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        this.paddleRect.setFilled(true);
        this.paddleRect.setFillColor(PADDLE_COLOR);
        this.paddleRect.setColor(PADDLE_COLOR);
        this.canvas.add(paddleRect);

        this.ballGOval = new GOval(Constants.BALL_DIAMETER , Constants.BALL_DIAMETER);
        this.ballGOval.setFilled(true);
        this.ballGOval.setFillColor(BALL_COLOR);
        this.ballGOval.setColor(BALL_COLOR);
        this.canvas.add(ballGOval);
    }

    public void setupBricks(Brick[] bricks){
        for (int i = 0; i < Constants.BRICK_ROWS * Constants.BRICK_COLS; i++){
            int row = i / Constants.BRICK_COLS;
            this.bricksRect[i] = new GRect(Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            this.bricksRect[i].setFilled(true);
            this.bricksRect[i].setFillColor(BRICKS_COLOR[row]);
            this.bricksRect[i].setColor(BRICKS_COLOR[row]);
            this.bricksRect[i].setLocation(bricks[i].getX(), bricks[i].getY());
            this.canvas.add(bricksRect[i]);
            messageLabel.sendToFront();
        }
    }

    public void render(Paddle paddle, Ball ball, Brick[] brick, int score, int lives, int highScore, GameState state) {
        paddleRect.setLocation(paddle.getX(), paddle.getY());
        ballGOval.setLocation(ball.getX(), ball.getY());
        for (int i = 0; i < Constants.BRICK_ROWS * Constants.BRICK_COLS; i++){
            bricksRect[i].setVisible(!brick[i].isDestroyed());
        }
        scoreLabel.setLabel("score: " + score);
        livesLabel.setLabel("lives: " + lives);
        highScoreLabel.setLabel("Highscore: " + highScore);
        switch (state){
            case RUNNING:
                messageLabel.setVisible(false);
                break;
            case READY:
                messageLabel.setLabel("press Space to start");
                messageLabel.setLocation((Constants.CANVAS_WIDTH - messageLabel.getWidth()) / 2.0, Constants.CANVAS_HEIGHT / 2.0);
                messageLabel.setVisible(true);
                break;
            case PAUSED:
                messageLabel.setLabel("Paused");
                messageLabel.setLocation((Constants.CANVAS_WIDTH - messageLabel.getWidth()) / 2.0, Constants.CANVAS_HEIGHT / 2.0);
                messageLabel.setVisible(true);
                break;
            case VICTORY:
                messageLabel.setLabel("YOU WON!");
                messageLabel.setLocation((Constants.CANVAS_WIDTH - messageLabel.getWidth()) / 2.0, Constants.CANVAS_HEIGHT / 2.0);
                messageLabel.setVisible(true);
                break;
            case GAME_OVER:
                messageLabel.setLabel("GAME OVER!");
                messageLabel.setLocation((Constants.CANVAS_WIDTH - messageLabel.getWidth()) / 2.0, Constants.CANVAS_HEIGHT / 2.0);
                messageLabel.setVisible(true);
                break;
        }
    }
}