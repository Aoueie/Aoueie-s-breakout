package breakout.model.storage;

public class InMemoryScoreStorage implements HighScoreStorage {

    private int highScore;

    public InMemoryScoreStorage() {
        this.highScore = 0;
    }

    @Override
    public int loadHighScore() {
        return highScore;
    }

    @Override
    public void saveHighScore(int score) {
        if (score > highScore) {
            highScore = score;
        }
    }
}