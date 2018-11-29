package model

import spock.lang.Specification
import spock.lang.Unroll

class ElementAdditionActionSpec extends Specification {
    @Unroll
    def "#element is added to a list already containing #elInMap"(BoardElement elInMap, BoardElement element) {
        given:
        Game game = new Game(Mock(GameState), new Board())
        HashMap<Coordinates, BoardElement> els = new HashMap<>()
        if (elInMap != null) els.put(elInMap.coordinates, elInMap)
        game.board.elements = els

        Action action = new ElementAdditionAction(element)

        when:
        action.execute(game)

        then:
        game.board.elements.get(element.coordinates) == element

        where:
        elInMap                              | element
        null                                 | new Doctor(new Coordinates(3, 3))
        new Dalek(new Coordinates(1, 1))     | new Teleporter(new Coordinates(3, 3))
        new ScrapPile(new Coordinates(3, 3)) | new Heart(new Coordinates(3, 3))
    }
}
