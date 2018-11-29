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

//        3         | 3          | Move.LOWER_LEFT  | 2         | 4
//        3         | 3          | Move.LOWER_RIGHT | 4         | 4
//        3         | 3          | Move.UPPER_LEFT  | 2         | 2
//        3         | 3          | Move.UPPER_RIGHT | 4         | 2

    }
}