package model.element.dynamicelement

import model.board.Coordinates
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class DalekSpec extends Specification {

    @Shared
    private int boardWidth = 10
    @Shared
    private int boardHeight = 10

    @Unroll
    def "moves a Dalek from (#dalekX, #dalekY) to (#expectedX, #expectedY) towards the Doctor"(int doctorX, int doctorY,
            int dalekX, int dalekY, int expectedX, int expectedY) {
        given:
        Dalek dalek = new Dalek(new Coordinates( dalekX, dalekY, boardWidth, boardHeight,))

        and:
        Coordinates newDoctorCoordinates = new Coordinates(doctorX, doctorY, boardWidth, boardHeight)

        when:
        dalek.makeMove(newDoctorCoordinates)

        then:
        dalek.coordinates.x == expectedX
        dalek.coordinates.y == expectedY

        where:
        doctorX | doctorY | dalekX | dalekY | expectedX | expectedY
        0       | 0       | 1      | 1      | 0         | 0
        0       | 0       | 2      | 1      | 1         | 0
        0       | 0       | 1      | 0      | 0         | 0
        0       | 0       | 1      | 2      | 0         | 1
        0       | 0       | 0      | 1      | 0         | 0
        3       | 3       | 0      | 0      | 1         | 1
        3       | 0       | 0      | 3      | 1         | 2
        0       | 0       | 0      | 0      | 0         | 0
        0       | 3       | 1      | 2      | 0         | 3
        1       | 1       | 0      | 0      | 1         | 1
        1       | 1       | 0      | 2      | 1         | 1
        1       | 1       | 2      | 0      | 1         | 1
        1       | 1       | 2      | 2      | 1         | 1
        1       | 1       | 1      | 0      | 1         | 1
        1       | 1       | 0      | 1      | 1         | 1
        1       | 1       | 1      | 2      | 1         | 1
        1       | 1       | 2      | 1      | 1         | 1
    }
}
