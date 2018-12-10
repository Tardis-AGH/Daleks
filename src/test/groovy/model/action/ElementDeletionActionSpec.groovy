package model.action

import javafx.collections.ObservableSet
import model.Game
import model.GameState
import model.board.Board
import model.board.Coordinates
import model.element.BoardElement
import model.element.dynamicelement.Dalek
import model.element.dynamicelement.Doctor
import model.element.staticelement.ScrapPile
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
    def "removes #element from a set already containing #elInMap"(BoardElement elInMap) {
        given:
        Game game = new Game(Mock(GameState), new Board(Mock(ObservableSet), Mock(Doctor)))
        if (elInMap != null) {
            game.board.elements.add(elInMap)
        }

        Action action = new ElementDeletionAction(e1)

        when:
        action.execute(game)

        then:
        !game.board.elements.contains(e1)

        where:
        elInMap << [null, e1, new ScrapPile(new Coordinates(3, 3, boardWidth, boardHeight))]
    }
}
