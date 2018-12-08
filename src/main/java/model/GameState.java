package model;

/**
 * The type Game state.
 */
public class GameState {

    private int numberOfLives;
    private int numberOfTeleporters;
    private int currentScore;
    private int highestScore;
    private int level;
    private int enemyCount;

    /**
     * Instantiates a new Game state.
     *
     * @param numberOfLives the number of lives
     * @param numberOfTeleporters the number of teleporters
     * @param currentScore the current score
     * @param highestScore the highest score
     * @param level the level
     * @param enemyCount the enemy count
     */
    public GameState(int numberOfLives, int numberOfTeleporters, int currentScore, int highestScore, int level,
            int enemyCount) {
        this.numberOfLives = numberOfLives;
        this.numberOfTeleporters = numberOfTeleporters;
        this.currentScore = currentScore;
        this.highestScore = highestScore;
        this.level = level;
        this.enemyCount = enemyCount;
    }

    /**
     * Gets enemy count.
     *
     * @return the enemy count
     */
    public int getEnemyCount() {
        return enemyCount;
    }

    /**
     * Sets enemy count.
     *
     * @param enemyCount the enemy count
     */
    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets highest score.
     *
     * @return the highest score
     */
    public int getHighestScore() {
        return highestScore;
    }

    /**
     * Sets highest score.
     *
     * @param highestScore the highest score
     */
    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Sets current score.
     *
     * @param currentScore the current score
     */
    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * Gets number of teleporters.
     *
     * @return the number of teleporters
     */
    public int getNumberOfTeleporters() {
        return numberOfTeleporters;
    }

    /**
     * Sets number of teleporters.
     *
     * @param numberOfTeleporters the number of teleporters
     */
    public void setNumberOfTeleporters(int numberOfTeleporters) {
        this.numberOfTeleporters = numberOfTeleporters;
    }

    /**
     * Gets number of lives.
     *
     * @return the number of lives
     */
    public int getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * Sets number of lives.
     *
     * @param numberOfLives the number of lives
     */
    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }
}
