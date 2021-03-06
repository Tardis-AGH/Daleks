package model.action.element

import javafx.collections.FXCollections
import javafx.collections.ObservableSet
import model.action.Action
import model.board.coordinates.Coordinates
import model.board.factory.ConcreteBoardFactory
import model.element.BoardElement
import model.element.dynamicelement.Dalek
import model.element.dynamicelement.Doctor
import model.element.staticelement.ScrapPile
import model.game.Game
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ElementDeletionActionSpec extends Specification {

    @Shared
    private int boardWidth = 10
    @Shared
    private int boardHeight = 10
    @Shared
    private BoardElement e1 = new Dalek(new Coordinates(1, 1, boardWidth, boardHeight))

    @Unroll
    def "removes a Dalek from a set already containing #elInMap"(BoardElement elInMap) {
        given:
        ObservableSet<BoardElement> elements = FXCollections.observableSet()
        if (elInMap != null) {
            elements.add(elInMap)
        }
        ConcreteBoardFactory testBoardGenerator = new ConcreteBoardFactory(elements, Mock(Doctor), boardWidth,
                boardHeight)
        Game game = new Game(testBoardGenerator)

        Action action = new ElementDeletionAction(e1)

        when:
        action.execute(game)

        then:
        !game.board.elements.contains(e1)

        where:
        elInMap << [null, e1, new ScrapPile(new Coordinates(3, 3, boardWidth, boardHeight))]
    }
}
