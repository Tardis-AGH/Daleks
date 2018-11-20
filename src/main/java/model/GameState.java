package model;

public class GameState {

    private int numberOfLives;
    private int numberOfTeleports;
    private int currentScore;
    private int highestScore;
    private int level;
    private int enemyCount;

    public GameState(int numberOfLives, int numberOfTeleports, int currentScore, int highestScore, int level, int enemyCount) {
        this.numberOfLives = numberOfLives;
        this.numberOfTeleports = numberOfTeleports;
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

    public int getNumberOfTeleports() {
        return numberOfTeleports;
    }

    public void setNumberOfTeleports(int numberOfTeleports) {
        this.numberOfTeleports = numberOfTeleports;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }
}
