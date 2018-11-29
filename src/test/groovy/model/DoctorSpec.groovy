package model

import javafx.scene.image.Image
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class DoctorSpec extends Specification {

    @Shared
    def width = Board.boardWidth

    @Shared
    def height = Board.boardHeight

    @Unroll
    def "Doctor moves #move from (#oldX, #oldY) to (#newX, #newY)"(int oldX, int oldY, Move move, int newX, int newY) {

        given:
        Coordinates doctorCoordinates = new Coordinates(oldX, oldY)
        Doctor doctor = new Doctor(doctorCoordinates, Mock(Image))
        List<BoardElement> elementList = new LinkedList<>()

        when:
        doctor.makeMove(move, elementList)

        then:
        doctor.getCoordinates().getX() == newX
        doctor.getCoordinates().getY() == newY

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

    @Unroll
    "Doctor teleports correctly to free space"(int[] coordinates) {

        given:
        Coordinates doctorCoordinates = new Coordinates(0, 0)
        Doctor doctor = new Doctor(doctorCoordinates, Mock(Image))
        Board.setBoardHeight(4)
        Board.setBoardWidth(4)

        when:
        List<BoardElement> elementList = new ArrayList<>()
        for (int i = 0; i < coordinates.length; i += 2) {
            elementList.add(new Heart(new Coordinates(coordinates[i], coordinates[i + 1]), Mock(Image)))
        }
        doctor.makeMove(Move.TELEPORT, elementList)

        then:
        doctor.getCoordinates().getX() >= 0 && doctor.getCoordinates().getX() < width
        doctor.getCoordinates().getY() >= 0 && doctor.getCoordinates().getY() < height
        for (BoardElement boardElement : elementList) {
            !doctor.getCoordinates().equals(boardElement.getCoordinates())
        }

        where:
        coordinates                                                              | _
        [1, 1, 2, 2, 3, 3, 1, 2, 1, 3, 2, 1, 2, 2, 2, 3, 3, 1, 3, 2, 0, 1, 0, 2] | _

    }
}