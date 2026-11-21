package breakout.view;

import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import breakout.model.Paddle;
import breakout.util.Constants;
import breakout.model.Ball;

import java.awt.Color;

public class BreakoutCanvas {

    private static final Color BACKGROUND_COLOR = new Color(18, 22, 33);
    private static final Color PADDLE_COLOR = new Color(77, 208, 225);
    private static final Color BALL_COLOR = new Color(210, 20, 140);

    private final GCanvas canvas;
    private final GRect paddleRect;
    private final GOval ballGOval;

    public BreakoutCanvas(GCanvas canvas) {
        this.canvas = canvas;
        this.canvas.setBackground(BACKGROUND_COLOR);

        this.paddleRect = new GRect(Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        this.paddleRect.setFilled(true);
        this.paddleRect.setFillColor(PADDLE_COLOR);
        this.paddleRect.setColor(PADDLE_COLOR);
        this.canvas.add(paddleRect);

        this.ballGOval = new GOval(Constants.BALL_DIAMETER * 2, Constants.BALL_DIAMETER * 2);
        this.ballGOval.setFilled(true);
        this.ballGOval.setFillColor(BALL_COLOR);
        this.ballGOval.setColor(BALL_COLOR);
        this.canvas.add(ballGOval);
    }

    public void render(Paddle paddle, Ball ball) {
        paddleRect.setLocation(paddle.getX(), paddle.getY());
    }
}