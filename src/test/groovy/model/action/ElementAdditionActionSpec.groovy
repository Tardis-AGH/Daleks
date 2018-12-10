package model.action

import model.Game
import model.GameState
import model.board.Board
import model.board.Coordinates
import model.board.generator.CoordinatesGenerator
import model.element.BoardElement
import model.element.dynamicelement.Dalek
import model.element.dynamicelement.Doctor
import model.element.staticelement.Heart
import model.element.staticelement.ScrapPile
import model.element.staticelement.Teleporter
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ElementAdditionActionSpec extends Specification {

    @Shared
    private int boardWidth = 10
    @Shared
    private int boardHeight = 10

    @Unroll
    def "adds #element to a set already containing #elInMap"(BoardElement elInMap, BoardElement element) {
        given:
        Game game = new Game(Mock(GameState), new Board(Mock(Doctor)))
        if (elInMap != null) {
            game.board.elements.add(elInMap)
        }

        Action action = new ElementAdditionAction(element)

        when:
        action.execute(game)

        then:
        game.board.elements.contains(element)

        where:
        elInMap << [null, new Dalek(new Coordinates(1, 1, boardWidth, boardHeight)),
                new ScrapPile(new Coordinates(3, 3, boardWidth, boardHeight))]
        element << [new Doctor(new Coordinates(3, 3, boardWidth, boardHeight), Mock(CoordinatesGenerator)),
                new Teleporter(new Coordinates(3, 3, boardWidth, boardHeight)),
                new Heart(new Coordinates(3, 3, boardWidth, boardHeight))]
    }
}
