package breakout.util;

public final class Constants {

    private Constants() {
    }

    // canvas
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final int HUD_HEIGHT = 35;
    public static final int TARGET_FPS = 60;
    public static final double TICK_MS = 1000.0 / TARGET_FPS;

    // paddle
    public static final double PADDLE_WIDTH = 100.0;
    public static final double PADDLE_HEIGHT = 15.0;
    public static final double PADDLE_Y = 545.0;
    public static final double PADDLE_MIN_X = 0.0;
    public static final double PADDLE_MAX_X = CANVAS_WIDTH - PADDLE_WIDTH;
    public static final double PADDLE_SPEED = 8.0;

    // ball
    public static final double BALL_RADIUS = 8.0;
    public static final double BALL_DIAMETER = BALL_RADIUS * 2.0;
    public static final double BALL_BASE_SPEED = 6.0;
    public static final double MAX_BOUNCE_ANGLE_DEG = 60.0;

    // bricks
    public static final int BRICK_ROWS = 5;
    public static final int BRICK_COLS = 8;
    public static final double BRICK_WIDTH = 85.0;
    public static final double BRICK_HEIGHT = 25.0;
    public static final double BRICK_GAP = 6.0;
    public static final double BRICK_OFFSET_X = 39.0;
    public static final double BRICK_OFFSET_Y = 60.0;
    public static final double BRICK_STEP_X = BRICK_WIDTH + BRICK_GAP;
    public static final double BRICK_STEP_Y = BRICK_HEIGHT + BRICK_GAP;
    public static final int STRONG_BRICK_HP = 2;
    public static final int NORMAL_BRICK_HP = 1;

    // hud data
    public static final int BRICK_SCORE = 100;
    public static final int INITIAL_LIVES = 3;
    public static final String HIGH_SCORE_FILE = "highscore.txt";
}