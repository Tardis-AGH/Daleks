package model.action

import model.Board
import model.Coordinates
import model.Game
import model.GameState
import model.element.BoardElement
import model.element.dynamicelement.Dalek
import model.element.staticelement.ScrapPile
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ElementDeletionActionSpec extends Specification {
    @Shared
    private BoardElement e1 = new Dalek(new Coordinates(1, 1))

    @Unroll
    def "removes #element from a set already containing #elInMap"(BoardElement elInMap, BoardElement element) {
        given:
        Game game = new Game(Mock(GameState), new Board())
        if (elInMap != null) {
            game.board.elements.add(elInMap)
        }

        Action action = new ElementDeletionAction(element)

        when:
        action.execute(game)

        then:
        !game.board.elements.contains(element)

        where:
        elInMap                              | element
        null                                 | e1
        e1                                   | e1
        new ScrapPile(new Coordinates(3, 3)) | e1
    }
}
