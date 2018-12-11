package model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The type Game state.
 */
public class GameState {

    private final IntegerProperty numberOfLives;
    private final IntegerProperty numberOfTeleporters;
    private final IntegerProperty currentScore;
    private final IntegerProperty highestScore;
    private final IntegerProperty level;

    /**
     * Instantiates a new Game state.
     *
     * @param numberOfLives the number of lives
     * @param numberOfTeleporters the number of teleporters
     * @param currentScore the current score
     * @param highestScore the highest score
     * @param level the level
     */
    public GameState(int numberOfLives, int numberOfTeleporters, int currentScore, int highestScore, int level) {
        this.numberOfLives = new SimpleIntegerProperty(numberOfLives);
        this.numberOfTeleporters = new SimpleIntegerProperty(numberOfTeleporters);
        this.currentScore = new SimpleIntegerProperty(currentScore);
        this.highestScore = new SimpleIntegerProperty(highestScore);
        this.level = new SimpleIntegerProperty(level);
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level.getValue();
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level.setValue(level);
    }

    /**
     * Gets highest score.
     *
     * @return the highest score
     */
    public int getHighestScore() {
        return highestScore.getValue();
    }

    /**
     * Sets highest score.
     *
     * @param highestScore the highest score
     */
    public void setHighestScore(int highestScore) {
        this.highestScore.setValue(highestScore);
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public int getCurrentScore() {
        return currentScore.getValue();
    }

    /**
     * Sets current score.
     *
     * @param currentScore the current score
     */
    public void setCurrentScore(int currentScore) {
        this.currentScore.setValue(currentScore);
    }

    /**
     * Gets number of teleporters.
     *
     * @return the number of teleporters
     */
    public int getNumberOfTeleporters() {
        return numberOfTeleporters.getValue();
    }

    /**
     * Sets number of teleporters.
     *
     * @param numberOfTeleporters the number of teleporters
     */
    public void setNumberOfTeleporters(int numberOfTeleporters) {
        this.numberOfTeleporters.setValue(numberOfTeleporters);
    }

    /**
     * Gets number of lives.
     *
     * @return the number of lives
     */
    public int getNumberOfLives() {
        return numberOfLives.getValue();
    }

    /**
     * Sets number of lives.
     *
     * @param numberOfLives the number of lives
     */
    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives.setValue(numberOfLives);
    }

    /**
     * Gets level property.
     *
     * @return level property
     */
    public IntegerProperty getLevelProperty() {
        return level;
    }

    /**
     * Gets highest score property.
     *
     * @return the highest score property
     */
    public IntegerProperty getHighestScoreProperty() {
        return highestScore;
    }

    /**
     * Gets current score property.
     *
     * @return the current score property
     */
    public IntegerProperty getCurrentScoreProperty() {
        return currentScore;
    }

    /**
     * Gets number of teleporters property.
     *
     * @return the number of teleporters property
     */
    public IntegerProperty getNumberOfTeleportersProperty() {
        return numberOfTeleporters;
    }

    /**
     * Gets number of lives property.
     *
     * @return the number of lives property
     */
    public IntegerProperty getNumberOfLivesProperty() {
        return numberOfLives;
    }
}
