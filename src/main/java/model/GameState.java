package model;

public class GameState {

    private int numberOfLives;
    private int numberOfTeleporters;
    private int currentScore;
    private int highestScore;
    private int level;
    private int enemyCount;

    public GameState(int numberOfLives, int numberOfTeleporters, int currentScore, int highestScore, int level, int enemyCount) {
        this.numberOfLives = numberOfLives;
        this.numberOfTeleporters = numberOfTeleporters;
        this.currentScore = currentScore;
        this.highestScore = highestScore;
        this.level = level;
        this.enemyCount = enemyCount;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getNumberOfTeleporters() {
        return numberOfTeleporters;
    }

    public void setNumberOfTeleporters(int numberOfTeleporters) {
        this.numberOfTeleporters = numberOfTeleporters;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }
}
