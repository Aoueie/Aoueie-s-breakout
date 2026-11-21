package breakout.controller;

import acm.program.GraphicsProgram;
import breakout.util.Constants;
import breakout.view.BreakoutCanvas;

import java.awt.event.KeyEvent;

public class BreakoutApp extends GraphicsProgram {

    public static final int APPLICATION_WIDTH = Constants.CANVAS_WIDTH;
    public static final int APPLICATION_HEIGHT = Constants.CANVAS_HEIGHT;

    private GameEngine engine;

    public static void main(String[] args) {
        new BreakoutApp().start(args);
    }

    @Override
    public void init() {
        BreakoutCanvas breakoutCanvas = new BreakoutCanvas(getGCanvas());
        engine = new GameEngine(breakoutCanvas);
        addKeyListeners();
    }

    @Override
    public void run() {
        getGCanvas().requestFocusInWindow();
        while (true) {
            long frameStart = System.nanoTime();
            engine.tick();
            double elapsedMs = (System.nanoTime() - frameStart) / 1_000_000.0;
            double sleepMs = Math.max(1.0, Constants.TICK_MS - elapsedMs);
            pause(sleepMs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        engine.onKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        engine.onKeyReleased(e.getKeyCode());
    }
}