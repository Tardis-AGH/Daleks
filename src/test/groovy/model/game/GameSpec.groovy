package model.game

import javafx.collections.FXCollections
import javafx.collections.ObservableSet
import model.board.Board
import model.board.Coordinates
import model.board.Move
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

class GameSpec extends Specification {

    @Shared
    private int boardWidth = 10
    @Shared
    private int boardHeight = 10

    @Unroll
    def "executes move #move of the Doctor and moves the Daleks accordingly without any collisions occurring"(
            List<Integer> doctor, List<List<Integer>> daleks,
            Move move, List<Integer> expectedDoctor, List<List<Integer>> expectedDaleks) {
        given:
        ObservableSet<BoardElement> elementSet = FXCollections.observableSet()
        daleks.forEach {
            elementSet.add(new Dalek(new Coordinates(it[0], it[1], boardWidth, boardHeight)))
        }
        Doctor theDoctor = new Doctor(new Coordinates(doctor[0], doctor[1], boardWidth, boardHeight),
                new CoordinatesGenerator(new Random(), elementSet, boardWidth, boardHeight))
        Game game = new Game(Mock(GameState), new Board(elementSet, theDoctor))

        when:
        Status status = game.makeMoves(move)
        List<List<Integer>> daleksAfter = []
        game.board.elements.forEach { daleksAfter.add([it.coordinates.x, it.coordinates.y]) }

        then:
        status == Status.CONTINUE_GAME
        game.board.doctor.coordinates.x == expectedDoctor[0]
        game.board.doctor.coordinates.y == expectedDoctor[1]
        game.board.elements.size() == expectedDaleks.size()
        daleksAfter.sort() == expectedDaleks.sort()

        where:
        doctor | daleks           | move             | expectedDoctor | expectedDaleks
        [0, 0] | [[0, 2], [3, 0]] | Move.RIGHT       | [1, 0]         | [[1, 1], [2, 0]]
        [3, 3] | [[0, 0], [3, 0]] | Move.UPPER_LEFT  | [2, 2]         | [[1, 1], [2, 1]]
        [0, 0] | [[3, 0], [0, 3]] | Move.LEFT        | [0, 0]         | [[2, 0], [0, 2]]
        [3, 0] | [[0, 0], [0, 3]] | Move.DOWN        | [3, 1]         | [[1, 1], [1, 2]]
        [0, 0] | []               | Move.UPPER_LEFT  | [0, 0]         | []
        [0, 0] | []               | Move.LOWER_RIGHT | [1, 1]         | []
    }

    @Unroll
    def "handles collisions of Daleks at #daleks and scrap piles at #piles when Doctor at [3, 4] ending with #status"(
            List<List<Integer>> daleks, List<List<Integer>> piles, List<List<Integer>> expectedDaleks,
            List<List<Integer>> expectedPiles, Status status) {
        given:
        ObservableSet<BoardElement> elementSet = FXCollections.observableSet()
        daleks.forEach {
            elementSet.add(new Dalek(new Coordinates(it[0], it[1], boardWidth, boardHeight)))
        }
        piles.forEach {
            elementSet.add(new ScrapPile(new Coordinates(it[0], it[1], boardWidth, boardHeight)))
        }
        Doctor theDoctor = new Doctor(new Coordinates(4, 4, boardWidth, boardHeight),
                new CoordinatesGenerator(new Random(), elementSet, boardWidth, boardHeight))
        Game game = new Game(new GameState(0, 0, 0, 0, 0, daleks.size()),
                new Board(elementSet, theDoctor))

        when:
        Status moveStatus = game.makeMoves(Move.UPPER_LEFT)
        List<List<Integer>> daleksAfter = []
        game.board.daleks.forEach { daleksAfter.add([it.coordinates.x, it.coordinates.y]) }
        List<List<Integer>> scrapPilesAfter = []
        game.board.staticBoardElements.forEach { scrapPilesAfter.add([it.coordinates.x, it.coordinates.y]) }

        then:
        moveStatus == status
        daleksAfter.size() == expectedDaleks.size()
        daleksAfter.sort() == expectedDaleks.sort()
        expectedPiles.size() == scrapPilesAfter.size()
        expectedPiles.sort() == scrapPilesAfter.sort()

        where:
        daleks                   | piles    | expectedDaleks | expectedPiles    | status
        [[1, 1]]                 | [[2, 2]] | []             | [[2, 2]]         | Status.LEVEL_UP
        [[1, 1], [1, 2]]         | [[2, 2]] | [[2, 3]]       | [[2, 2]]         | Status.CONTINUE_GAME
        [[3, 0], [2, 0]]         | []       | []             | [[3, 1]]         | Status.LEVEL_UP
        [[3, 0], [2, 0], [1, 1]] | []       | [[2, 2]]       | [[3, 1]]         | Status.CONTINUE_GAME
        [[3, 0], [2, 0]]         | [[7, 7]] | []             | [[3, 1], [7, 7]] | Status.LEVEL_UP
        [[3, 0], [2, 0], [1, 1]] | [[7, 7]] | [[2, 2]]       | [[3, 1], [7, 7]] | Status.CONTINUE_GAME
        [[2, 1], [3, 1], [4, 1]] | []       | []             | [[3, 2]]         | Status.LEVEL_UP
        [[2, 1], [3, 1], [4, 1]] | [[3, 2]] | []             | [[3, 2]]         | Status.LEVEL_UP
        [[2, 1], [3, 1], [4, 1]] | [[7, 7]] | []             | [[3, 2], [7, 7]] | Status.LEVEL_UP
    }

    @Unroll
    def "handles collisions of Daleks at #expectedDaleks with hearts at #staticEl.h and teleporters at #staticEl.t"(
            List<List<Integer>> daleks, Map staticEl, List<List<Integer>> expectedDaleks, Map expectedStaticEl) {
        given:
        ObservableSet<BoardElement> elementSet = FXCollections.observableSet()
        daleks.forEach {
            elementSet.add(new Dalek(new Coordinates(it[0], it[1], boardWidth, boardHeight)))
        }
        staticEl.h.forEach {
            elementSet.
                    add(new Heart(new Coordinates(it[0] as int, it[1] as int, boardWidth, boardHeight)))
        }
        staticEl.t.forEach {
            elementSet.
                    add(new Teleporter(new Coordinates(it[0] as int, it[1] as int, boardWidth, boardHeight)))
        }
        Doctor theDoctor = new Doctor(new Coordinates(5, 5, boardWidth, boardHeight),
                new CoordinatesGenerator(new Random(), elementSet, boardWidth, boardHeight))
        Game game = new Game(Mock(GameState), new Board(elementSet as ObservableSet<BoardElement>, theDoctor))

        when:
        Status status = game.makeMoves(Move.UPPER_LEFT)
        List<List<Integer>> heartsAfter = []
        List<List<Integer>> teleportersAfter = []
        game.board.staticBoardElements.forEach {
            if (it instanceof Heart) {
                heartsAfter.add([it.coordinates.x, it.coordinates.y])
            } else {
                teleportersAfter.add([it.coordinates.x, it.coordinates.y])
            }
        }
        List<List<Integer>> daleksAfter = []
        game.board.daleks.forEach { daleksAfter.add([it.coordinates.x, it.coordinates.y]) }

        then:
        status == Status.CONTINUE_GAME
        expectedStaticEl.t.size() == teleportersAfter.size()
        expectedStaticEl.t.sort() == teleportersAfter.sort()
        expectedStaticEl.h.size() == heartsAfter.size()
        expectedStaticEl.h.sort() == heartsAfter.sort()
        expectedDaleks.size() == daleksAfter.size()
        expectedDaleks.sort() == daleksAfter.sort()

        where:
        daleks           | staticEl                   | expectedDaleks   | expectedStaticEl
        [[1, 1]]         | [h: [[2, 2]], t: []]       | [[2, 2]]         | [h: [], t: []]
        [[1, 1]]         | [h: [[3, 3]], t: []]       | [[2, 2]]         | [h: [[3, 3]], t: []]
        [[1, 1]]         | [h: [[3, 3]], t: [[7, 7]]] | [[2, 2]]         | [h: [[3, 3]], t: [[7, 7]]]
        [[1, 1], [9, 9]] | [h: [[3, 3]], t: [[7, 7]]] | [[2, 2], [8, 8]] | [h: [[3, 3]], t: [[7, 7]]]
        [[1, 1]]         | [h: [], t: [[2, 2]]]       | [[2, 2]]         | [h: [], t: []]
        [[1, 1]]         | [h: [], t: [[3, 3]]]       | [[2, 2]]         | [h: [], t: [[3, 3]]]
        [[1, 1]]         | [h: [[7, 7]], t: [[3, 3]]] | [[2, 2]]         | [h: [[7, 7]], t: [[3, 3]]]
        [[1, 1], [9, 9]] | [h: [[7, 7]], t: [[3, 3]]] | [[2, 2], [8, 8]] | [h: [[7, 7]], t: [[3, 3]]]
    }

    @Unroll
    def "handles move #move of the Doctor at #doctor when hearts at #staticEl.h and teleporters at #staticEl.t"(
            List<Integer> doctor, Move move, Map staticEl, Map expectedStaticEl) {
        given:
        ObservableSet<BoardElement> elementSet = FXCollections.observableSet()
        staticEl.h.forEach {
            elementSet.
                    add(new Heart(new Coordinates(it[0] as int, it[1] as int, boardWidth, boardHeight)))
        }
        staticEl.t.forEach {
            elementSet.
                    add(new Teleporter(new Coordinates(it[0] as int, it[1] as int, boardWidth, boardHeight)))
        }
        Doctor theDoctor = new Doctor(new Coordinates(doctor[0], doctor[1], boardWidth, boardHeight),
                new CoordinatesGenerator(new Random(), elementSet, boardWidth, boardHeight))
        int numberOfTeleporters = 0
        int numberOfLives = 1
        GameState gameState = new GameState(numberOfLives, numberOfTeleporters, 0, 0, 0, 1)
        Game game = new Game(gameState, new Board(elementSet, theDoctor))

        when:
        Status status = game.makeMoves(move)
        List<List<Integer>> heartsAfter = []
        List<List<Integer>> teleportersAfter = []
        game.board.staticBoardElements.forEach {
            if (it instanceof Heart) {
                heartsAfter.add([it.coordinates.x, it.coordinates.y])
            } else {
                teleportersAfter.add([it.coordinates.x, it.coordinates.y])
            }
        }

        then:
        status == Status.CONTINUE_GAME
        expectedStaticEl.t.size() == teleportersAfter.size()
        expectedStaticEl.t.sort() == teleportersAfter.sort()
        expectedStaticEl.h.size() == heartsAfter.size()
        expectedStaticEl.h.sort() == heartsAfter.sort()
        game.gameState.numberOfLives == numberOfLives + (staticEl.h.size() - heartsAfter.size())
        game.gameState.numberOfTeleporters == numberOfTeleporters + (staticEl.t.size() - teleportersAfter.size())

        where:
        doctor | move             | staticEl                   | expectedStaticEl
        [1, 1] | Move.UPPER_LEFT  | [h: [[2, 2]], t: []]       | [h: [[2, 2]], t: []]
        [1, 1] | Move.UPPER_LEFT  | [h: [[2, 2]], t: [[3, 3]]] | [h: [[2, 2]], t: [[3, 3]]]
        [1, 1] | Move.LOWER_RIGHT | [h: [[2, 2]], t: []]       | [h: [], t: []]
        [1, 1] | Move.LOWER_RIGHT | [h: [[2, 2]], t: [[3, 3]]] | [h: [], t: [[3, 3]]]
        [1, 1] | Move.UPPER_LEFT  | [t: [[2, 2]], h: []]       | [t: [[2, 2]], h: []]
        [1, 1] | Move.UPPER_LEFT  | [t: [[2, 2]], h: [[3, 3]]] | [t: [[2, 2]], h: [[3, 3]]]
        [1, 1] | Move.LOWER_RIGHT | [t: [[2, 2]], h: []]       | [t: [], h: []]
        [1, 1] | Move.LOWER_RIGHT | [t: [[2, 2]], h: [[3, 3]]] | [t: [], h: [[3, 3]]]
    }

    @Unroll
    def "handles collisions of the Doctor at #doctor with Daleks at #waysToDie.d and scrap piles at #waysToDie.s"(
            List<Integer> doctor, int lives, Move move, Map waysToDie) {
        given:
        ObservableSet<BoardElement> elementSet = FXCollections.observableSet()
        waysToDie.d.forEach {
            elementSet.
                    add(new Dalek(new Coordinates(it[0] as int, it[1] as int, boardWidth, boardHeight)))
        }
        waysToDie.s.forEach {
            elementSet.add(new ScrapPile(new Coordinates(it[0] as int, it[1] as int, boardWidth, boardHeight)))
        }
        Doctor theDoctor = new Doctor(new Coordinates(doctor[0], doctor[1], boardWidth, boardHeight),
                new CoordinatesGenerator(new Random(), elementSet, boardWidth, boardHeight))
        GameState gameState = new GameState(lives, 0, 0, 0, 0, 1)
        Game game = new Game(gameState, new Board(elementSet, theDoctor))

        Status expectedStatus = Status.RESTART_LEVEL
        if (lives == 0) {
            expectedStatus = Status.GAME_OVER
        }

        when:
        Status moveStatus = game.makeMoves(move)

        then:
        expectedStatus == moveStatus
        game.gameState.numberOfLives == lives - 1

        where:
        doctor | lives | move             | waysToDie
        [0, 0] | 1     | Move.UPPER_LEFT  | [d: [[1, 1]], s: []]
        [0, 0] | 1     | Move.LOWER_RIGHT | [d: [[1, 0], [0, 1]], s: []]
        [0, 0] | 1     | Move.LOWER_RIGHT | [d: [[1, 0]], s: []]
        [0, 0] | 1     | Move.DOWN        | [d: [[1, 0]], s: []]
        [0, 0] | 1     | Move.DOWN        | [d: [], s: [[0, 1]]]
        [0, 0] | 1     | Move.DOWN        | [d: [[1, 0]], s: [[0, 1]]]
        [0, 0] | 1     | Move.DOWN        | [d: [[2, 0], [1, 0]], s: [[0, 1]]]
        [0, 0] | 1     | Move.DOWN        | [d: [[2, 0], [1, 0]], s: []]
        [0, 0] | 0     | Move.UPPER_LEFT  | [d: [[1, 1]], s: []]
        [0, 0] | 0     | Move.LOWER_RIGHT | [d: [[1, 0], [0, 1]], s: []]
        [0, 0] | 0     | Move.LOWER_RIGHT | [d: [[1, 0]], s: []]
        [0, 0] | 0     | Move.DOWN        | [d: [[1, 0]], s: []]
        [0, 0] | 0     | Move.DOWN        | [d: [], s: [[0, 1]]]
        [0, 0] | 0     | Move.DOWN        | [d: [[1, 0]], s: [[0, 1]]]
        [0, 0] | 0     | Move.DOWN        | [d: [[2, 0], [1, 0]], s: [[0, 1]]]
        [0, 0] | 0     | Move.DOWN        | [d: [[2, 0], [1, 0]], s: []]
    }
}
