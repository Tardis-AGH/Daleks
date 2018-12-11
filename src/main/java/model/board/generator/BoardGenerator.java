package model.board.generator;

import model.board.Board;

/**
 * The type Board generator.
 */
public interface BoardGenerator {

    /**
     * Generate new board board.
     *
     * @param level the level
     *
     * @return the board
     */
    Board generateNewBoard(int level);

    /**
     * Gets board height.
     *
     * @return the board height
     */
    int getBoardHeight();

    /**
     * Gets board width.
     *
     * @return the board width
     */
    int getBoardWidth();
}
