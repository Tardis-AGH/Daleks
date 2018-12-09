package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.image.Image;
import model.element.BoardElement;
import model.element.StaticBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Board.
 */
public class Board {

    private static int BOARD_WIDTH = 15;
    private static int BOARD_HEIGHT = 15;
    private static double HEART_RATIO = 0.01;
    private static double TELEPORTER_RATIO = 0.01;
    private static double SCRAP_PILE_RATIO = 0.00;

    private final ObservableSet<BoardElement> elements = FXCollections.observableSet();
    private Doctor doctor;

    /**
     * Instantiates a new Board.
     */
    public Board(int level) {
        this.setBoardDimensions(level);

        Stream.iterate(1L, n -> n + 1)
                .limit(this.getDaleksNumber(level))
                .forEach(e -> {
                    Image dalekImage = new Image((getClass().getClassLoader().getResource("images/dalek/dalek5.png")).toExternalForm());
                    this.elements.add(new Dalek(this.getNewCoordinates(), dalekImage));
                });

        //Insert Hearts
        Stream.iterate(0, n -> n + 1)
                .limit((int) (BOARD_HEIGHT * BOARD_WIDTH * HEART_RATIO))
                .forEach(e -> {
                    Image heartImage = new Image((getClass().getClassLoader().getResource("images/powerup/heart.png")).toExternalForm());
                    this.elements.add(new Heart(this.getNewCoordinates(), heartImage));
                });

        //Insert Teleporters
        Stream.iterate(0, n -> n + 1)
                .limit((int) (BOARD_HEIGHT * BOARD_WIDTH * TELEPORTER_RATIO))
                .forEach(e -> {
                    Image teleportImage = new Image((getClass().getClassLoader().getResource("images/powerup/tardis.png")).toExternalForm());
                    this.elements.add(new Teleporter(this.getNewCoordinates(), teleportImage));
                });

        Random generator = new Random();
        double pileRatio = generator.nextDouble() * SCRAP_PILE_RATIO;

        //Insert scrap piles (randomized)
        Stream.iterate(0, n -> n + 1)
                .limit((int) (BOARD_HEIGHT * BOARD_WIDTH * pileRatio))
                .forEach(e -> {
                    Image scrapImage = new Image((getClass().getClassLoader().getResource("images/misc/scrap.png")).toExternalForm());
                    this.elements.add(new ScrapPile(this.getNewCoordinates(), scrapImage));
                });

        // Image doctorImage = new Image("/images/dalek/dalek1.png");
        // this.doctor = new Doctor(new Coordinates(BOARD_WIDTH/2+1, BOARD_HEIGHT/2+1 ), doctorImage);
        Image doctorImage = new Image((getClass().getClassLoader().getResource("images/doctor/doctor.png")).toExternalForm());
        this.doctor = new Doctor(new Coordinates(BOARD_WIDTH / 2, BOARD_HEIGHT / 2), doctorImage);
        this.elements.add(doctor);
    }

    /**
     * Gets board height.
     *
     * @return the board height
     */
    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    /**
     * Sets board height.
     *
     * @param boardHeight the board height
     */
    public static void setBoardHeight(int boardHeight) {
        BOARD_HEIGHT = boardHeight;
    }

    /**
     * Gets board width.
     *
     * @return the board width
     */
    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    /**
     * Sets board width.
     *
     * @param boardWidth the board width
     */
    public static void setBoardWidth(int boardWidth) {
        BOARD_WIDTH = boardWidth;
    }

    /**
     * Gets daleks.
     *
     * @return the daleks
     */
    public List<Dalek> getDaleks() {
        return elements.stream().filter(e -> e instanceof Dalek).map(e -> (Dalek) e).collect(Collectors.toList());
    }

    /**
     * Gets static board elements.
     *
     * @return the static board elements
     */
    public List<StaticBoardElement> getStaticBoardElements() {
        return elements.stream()
                .filter(e -> e instanceof StaticBoardElement)
                .map(e -> (StaticBoardElement) e)
                .collect(Collectors.toList());
    }

    /**
     * Gets doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets elements.
     *
     * @return the elements
     */
    public ObservableSet<BoardElement> getElements() {
        return elements;
    }

    /**
     * @param level parameter to calculate number of daleks
     * @return number of daleks to place
     */
    private int getDaleksNumber(int level) {
        return (int) (1.5 * Math.sqrt(level - 1) + 5);
    }

    /**
     * Get new valid (not occupied) coordinates.
     *
     * @return new coordinates
     */
    private Coordinates getNewCoordinates() {
        Random generator = new Random();
        Coordinates newCoordinates;
        do {
            newCoordinates = new Coordinates(generator.nextInt(Board.getBoardWidth()),
                    generator.nextInt(Board.getBoardHeight()));
        } while (!isFieldEmpty(newCoordinates));
        return newCoordinates;
    }

    /**
     * @return new coordinates for new element
     */
    private boolean isFieldEmpty(Coordinates coordinates) {
        for (BoardElement boardElement : elements) {
            if (boardElement.getCoordinates().equals(coordinates)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param level parameter for board expansion
     */
    private void setBoardDimensions(int level) {
        int factor = level / 2 - 1;
        BOARD_WIDTH += factor * 2;
        BOARD_HEIGHT += factor * 2;
    }
}
