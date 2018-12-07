package model.element.dynamicelement

import javafx.scene.image.Image
import model.Board
import model.Coordinates
import model.Move
import model.element.BoardElement
import model.element.staticelement.Heart
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class DoctorSpec extends Specification {

    @Shared
    private int width = Board.boardWidth

    @Shared
    private int height = Board.boardHeight

    @Unroll
    def "moves the Doctor from (#oldX, #oldY) to (#newX, #newY) using #move"(int oldX, int oldY, Move move, int newX,
            int newY) {
        given:
        Coordinates doctorCoordinates = new Coordinates(oldX, oldY)
        Doctor doctor = new Doctor(doctorCoordinates, Mock(Image))
        Set<BoardElement> elementSet = [] as Set

        when:
        doctor.makeMove(move, elementSet)

        then:
        doctor.coordinates.x == newX
        doctor.coordinates.y == newY

        where:
        oldX      | oldY       | move             | newX      | newY
        3         | 3          | Move.RIGHT       | 4         | 3
        3         | 3          | Move.LEFT        | 2         | 3
        3         | 3          | Move.DOWN        | 3         | 4
        3         | 3          | Move.UP          | 3         | 2
        3         | 3          | Move.LOWER_LEFT  | 2         | 4
        3         | 3          | Move.LOWER_RIGHT | 4         | 4
        3         | 3          | Move.UPPER_LEFT  | 2         | 2
        3         | 3          | Move.UPPER_RIGHT | 4         | 2
        width - 1 | 3          | Move.RIGHT       | width - 1 | 3
        0         | 3          | Move.LEFT        | 0         | 3
        3         | height - 1 | Move.DOWN        | 3         | height - 1
        0         | 0          | Move.UP          | 0         | 0
        0         | height - 1 | Move.LOWER_LEFT  | 0         | height - 1
        width - 1 | height - 1 | Move.LOWER_RIGHT | width - 1 | height - 1
        0         | 0          | Move.UPPER_LEFT  | 0         | 0
        width - 1 | 0          | Move.UPPER_RIGHT | width - 1 | 0
    }

    def "teleports the Doctor correctly to free space"() {
        given:
        Coordinates doctorCoordinates = new Coordinates(0, 0)
        Doctor doctor = new Doctor(doctorCoordinates, Mock(Image))
        Board.boardHeight = 4
        Board.boardWidth = 4

        and:
        Set<BoardElement> elementSet = [] as Set
        List<List<Integer>> occupiedCoordinates = [[1, 1], [2, 2], [3, 3], [1, 2], [1, 3], [2, 1],
                [2, 2], [2, 3], [3, 1], [3, 2], [0, 1], [0, 2]]

        for (List<Integer> list in occupiedCoordinates) {
            elementSet.add(new Heart(new Coordinates(list.get(0), list.get(1))))
        }

        when:
        doctor.makeMove(Move.TELEPORT, elementSet)

        then:
        doctor.coordinates.x >= 0 && doctor.coordinates.x < width
        doctor.coordinates.y >= 0 && doctor.coordinates.y < height
    }
}
