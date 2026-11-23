package breakout.view;

import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import breakout.model.Brick;
import breakout.model.Paddle;
import breakout.util.Constants;
import breakout.model.Ball;

import java.awt.Color;

public class BreakoutCanvas {

    private static final Color BACKGROUND_COLOR = new Color(18, 22, 33);
    private static final Color PADDLE_COLOR = new Color(77, 208, 225);
    private static final Color BALL_COLOR = new Color(250, 3, 172);

    private static final Color[] BRICKS_COLOR = {new Color(28, 243, 153), new Color(89, 228, 19),new Color(227, 234, 13),new Color(237, 114, 12),new Color(239, 14, 14)};

    private final GCanvas canvas;
    private final GRect paddleRect;
    private final GOval ballGOval;

    private GRect[] bricksRect = new GRect[Constants.BRICK_ROWS * Constants.BRICK_COLS];

    public BreakoutCanvas(GCanvas canvas) {
        this.canvas = canvas;
        this.canvas.setBackground(BACKGROUND_COLOR);

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
        }
    }

    public void render(Paddle paddle, Ball ball, Brick[] brick) {
        paddleRect.setLocation(paddle.getX(), paddle.getY());
        ballGOval.setLocation(ball.getX(), ball.getY());
        for (int i = 0; i < Constants.BRICK_ROWS * Constants.BRICK_COLS; i++){
            bricksRect[i].setVisible(!brick[i].isDestroyed());
        }
    }
}