package model.action


import model.Board
import model.Coordinates
import model.Game
import model.GameState
import model.element.BoardElement
import model.element.dynamicelement.Dalek
import model.element.dynamicelement.Doctor
import model.element.staticelement.Heart
import model.element.staticelement.ScrapPile
import model.element.staticelement.Teleporter
import spock.lang.Specification
import spock.lang.Unroll

class ElementAdditionActionSpec extends Specification {

    @Unroll
    def "adds #element to a set already containing #elInMap"(BoardElement elInMap, BoardElement element) {
        given:
        Game game = new Game(Mock(GameState), new Board())
        if (elInMap != null) {
            game.board.elements.add(elInMap)
        }

        Action action = new ElementAdditionAction(element)

        when:
        action.execute(game)

        then:
        game.board.elements.contains(element)

        where:
        elInMap                              | element
        null                                 | new Doctor(new Coordinates(3, 3))
        new Dalek(new Coordinates(1, 1))     | new Teleporter(new Coordinates(3, 3))
        new ScrapPile(new Coordinates(3, 3)) | new Heart(new Coordinates(3, 3))
    }
}