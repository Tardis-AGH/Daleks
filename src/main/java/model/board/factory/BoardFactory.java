package model.board.factory;

import model.board.Board;

/**
 * The interface Board factory.
 */
public interface BoardFactory {

    /**
     * Generate new board based on the current level.
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
