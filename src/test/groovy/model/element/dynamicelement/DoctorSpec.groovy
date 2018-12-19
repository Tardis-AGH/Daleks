package model.element.dynamicelement

import model.board.coordinates.Coordinates
import model.board.Move
import model.element.BoardElement
import model.element.staticelement.Heart
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class DoctorSpec extends Specification {

    @Shared
    private int boardWidth = 10
    @Shared
    private int boardHeight = 10

    @Unroll
    def "moves the Doctor from (#oldX, #oldY) to (#newX, #newY) using #move"(int oldX, int oldY, Move move, int newX,
            int newY) {
        given:
        Coordinates doctorCoordinates = new Coordinates(oldX, oldY, boardWidth, boardHeight)
        Doctor doctor = new Doctor(doctorCoordinates)

        when:
        doctor.makeMove(move)

        then:
        doctor.coordinates.x == newX
        doctor.coordinates.y == newY

        where:
        oldX           | oldY            | move             | newX           | newY
        3              | 3               | Move.RIGHT       | 4              | 3
        3              | 3               | Move.LEFT        | 2              | 3
        3              | 3               | Move.DOWN        | 3              | 4
        3              | 3               | Move.UP          | 3              | 2
        3              | 3               | Move.LOWER_LEFT  | 2              | 4
        3              | 3               | Move.LOWER_RIGHT | 4              | 4
        3              | 3               | Move.UPPER_LEFT  | 2              | 2
        3              | 3               | Move.UPPER_RIGHT | 4              | 2
        boardWidth - 1 | 3               | Move.RIGHT       | boardWidth - 1 | 3
        0              | 3               | Move.LEFT        | 0              | 3
        3              | boardHeight - 1 | Move.DOWN        | 3              | boardHeight - 1
        0              | 0               | Move.UP          | 0              | 0
        0              | boardHeight - 1 | Move.LOWER_LEFT  | 0              | boardHeight - 1
        boardWidth - 1 | boardHeight - 1 | Move.LOWER_RIGHT | boardWidth - 1 | boardHeight - 1
        0              | 0               | Move.UPPER_LEFT  | 0              | 0
        boardWidth - 1 | 0               | Move.UPPER_RIGHT | boardWidth - 1 | 0
    }

    def "teleports the Doctor correctly to free space"() {
        given:
        Coordinates doctorCoordinates = new Coordinates(0, 0, boardWidth, boardHeight)

        and:
        Set<BoardElement> elementSet = [] as Set
        List<List<Integer>> occupiedCoordinates = [[1, 1], [2, 2], [3, 3], [1, 2], [1, 3], [2, 1],
                [2, 2], [2, 3], [3, 1], [3, 2], [0, 1], [0, 2]]

        for (List<Integer> list in occupiedCoordinates) {
            elementSet.add(new Heart(new Coordinates(list.get(0), list.get(1), boardWidth, boardHeight)))
        }

        and:
        Doctor doctor = new Doctor(doctorCoordinates)

        and:

        when:
        doctor.makeMove(Move.TELEPORT)

        then:
        doctor.coordinates.x >= 0 && doctor.coordinates.x < boardWidth
        doctor.coordinates.y >= 0 && doctor.coordinates.y < boardHeight
        occupiedCoordinates.every {
            doctor.coordinates.x != it[0] || doctor.coordinates.y != it[1]
        }
    }

    @Unroll
    def "throws RuntimeException when the Doctor encounters a #otherElement.class.name element out of order"(
            BoardElement otherElement) {
        given:
        Coordinates doctorCoordinates = new Coordinates(1, 1, boardWidth, boardHeight)
        Doctor doctor = new Doctor(doctorCoordinates)

        when:
        otherElement.accept(doctor)

        then:
        thrown RuntimeException

        where:
        otherElement << [new Dalek(new Coordinates(1, 1, boardWidth, boardHeight)),
                new Dalek(new Coordinates(5, 5, boardWidth, boardHeight)),
                new Doctor(new Coordinates(1, 1, boardWidth, boardHeight))]
    }
}
