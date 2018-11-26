package model

import spock.lang.Specification

class ElementAdditionActionTest extends Specification {
    def "Execute"(List<BoardElement> elements, BoardElement element) {
        given:
        Game game = new Game(Mock(GameState), new Board())
        HashMap<Coordinates, BoardElement> els = new HashMap<>()

        for (BoardElement bl in elements) els.put(bl.coordinates, bl)
        game.board.elements = els

        Action action = new ElementAdditionAction(element)


        when:
        action.execute(game)

        then:
        game.board.elements.get(element.coordinates) == element

        where:
        elements                       | element
        new LinkedList<BoardElement>() | new Doctor(new Coordinates(3, 3))
        new LinkedList<BoardElement>() {
            {
                add(new Dalek(new Coordinates(1, 1)))
            }
        }                              | new Teleporter(new Coordinates(3, 3))
        new LinkedList<BoardElement>() {
            {
                add(new ScrapPile(new Coordinates(3, 3)))
            }
        }                              | new Heart(new Coordinates(3, 3))
    }
}
