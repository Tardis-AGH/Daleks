package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.element.BoardElement;
import model.element.StaticBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;

/**
 * The type Board.
 */
public class Board {

    private static int BOARD_WIDTH = 21;
    private static int BOARD_HEIGHT = 21;
    private static double HEART_RATIO = 0.1;
    private static double TELEPORTER_RATIO = 0.05;

    private HashMap<Coordinates, BoardElement> elements;
    private Doctor doctor;

    /**
     * Instantiates a new Board.
     */
    public Board(int level) {
        this.setBoardDimensions(level);

        //Insert Daleks
        Stream.iterate(0, n->n+1)
                .peek(e->{
                    Coordinates coordinates = this.getNewCoordinates();
                    this.elements.put(coordinates, new Dalek(coordinates) );
                })
                .limit(this.getDaleksNumber(level))
                .close();

        //Insert Hearts
        Stream.iterate(0, n->n+1)
                .peek(e->{
                    Coordinates coordinates = this.getNewCoordinates();
                    this.elements.put(coordinates, new Heart(coordinates) );
                })
                .limit((int)(BOARD_HEIGHT*BOARD_WIDTH*HEART_RATIO))
                .close();

        //Insert Teleporters
        Stream.iterate(0, n->n+1)
                .peek(e->{
                    Coordinates coordinates = this.getNewCoordinates();
                    this.elements.put(coordinates, new Teleporter(coordinates) );
                })
                .limit((int)(BOARD_HEIGHT*BOARD_WIDTH*TELEPORTER_RATIO))
                .close();

        Random generator = new Random();
        double pileRatio = generator.nextDouble() * .05;

        //Insert scrap piles (randomized)
        Stream.iterate(0, n->n+1)
                .peek(e->{
                    Coordinates coordinates = this.getNewCoordinates();
                    this.elements.put(coordinates, new ScrapPile(coordinates) );
                })
                .limit((int)(BOARD_HEIGHT*BOARD_WIDTH*pileRatio))
                .close();

        this.doctor = new Doctor(new Coordinates(BOARD_WIDTH/2+1, BOARD_HEIGHT/2+1 ));
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
     * @param elements the elements
     *
     * @return the daleks
     */
    public List<Dalek> getDaleks(Collection<BoardElement> elements) {
        return elements.stream().filter(e -> e instanceof Dalek).map(e -> (Dalek) e).collect(Collectors.toList());
    }

    /**
     * Gets static board elements.
     *
     * @param elements the elements
     *
     * @return the static board elements
     */
    public List<StaticBoardElement> getStaticBoardElements(Collection<BoardElement> elements) {
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
    public HashMap<Coordinates, BoardElement> getElements() {
        return elements;
    }

    /**
     * Sets elements.
     *
     * @param elements the elements
     */
    public void setElements(HashMap<Coordinates, BoardElement> elements) {
        this.elements = elements;
    }


    /**
     * @param level parameter to calculate number of daleks
     * @return number of daleks to place
     */
    private int getDaleksNumber(int level){
        return (int)(1.5*Math.sqrt(level-1) + 5);
    }

    /**
     * @return new coordinates for new element
     */
    private Coordinates getNewCoordinates() {
        Random generator = new Random();
        final Coordinates newCoordinates = new Coordinates(0, 0);
        do {
            newCoordinates.setX(generator.nextInt(Board.getBoardWidth()));
            newCoordinates.setY(generator.nextInt(Board.getBoardHeight()));
        } while (elements.containsKey(newCoordinates));
        return newCoordinates;
    }

    /**
     * @param level parameter for board expansion
     */
    private void setBoardDimensions(int level){
        int factor = level/2-1;
        BOARD_WIDTH+=factor*2;
        BOARD_HEIGHT+=factor*2;
    }
}
