package model.action.element

import javafx.collections.FXCollections
import javafx.collections.ObservableSet
import model.action.Action
import model.board.Coordinates
import model.board.factory.ConcreteBoardFactory
import model.element.BoardElement
import model.element.dynamicelement.Dalek
import model.element.dynamicelement.Doctor
import model.element.staticelement.Heart
import model.element.staticelement.ScrapPile
import model.element.staticelement.Teleporter
import model.game.Game
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ElementAdditionActionSpec extends Specification {

    @Shared
    private int boardWidth = 10
    @Shared
    private int boardHeight = 10

    @Unroll
    def "adds #element.class to a set already containing #elInMap"(BoardElement element, BoardElement elInMap) {
        given:
        ObservableSet<BoardElement> elements = FXCollections.observableSet()
        if (elInMap != null) {
            elements.add(elInMap)
        }
        ConcreteBoardFactory testBoardGenerator = new ConcreteBoardFactory(elements, Mock(Doctor), boardWidth,
                boardHeight)

        Game game = new Game(testBoardGenerator)

        Action action = new ElementAdditionAction(element)

        when:
        action.execute(game)

        then:
        game.board.elements.contains(element)

        where:
        element << [new Doctor(new Coordinates(3, 3, boardWidth, boardHeight)),
                new Teleporter(new Coordinates(3, 3, boardWidth, boardHeight)),
                new Heart(new Coordinates(3, 3, boardWidth, boardHeight))]
        elInMap << [null, new Dalek(new Coordinates(1, 1, boardWidth, boardHeight)),
                new ScrapPile(new Coordinates(3, 3, boardWidth, boardHeight))]
    }

}
