package model

import javafx.scene.image.Image
import spock.lang.Specification
import spock.lang.Unroll


class DalekSpec extends Specification {

    @Unroll
    def "Dalek moves from (#dalekX, #dalekY) to (#expectedX, #expectedY) towards the Doctor"(int doctorX, int doctorY, int dalekX, int dalekY, int expectedX, int expectedY) {

        given:
        Dalek dalek = new Dalek(new Coordinates(dalekX, dalekY, 10), Mock(Image))

        and:
        Coordinates newDoctorCoordinates = new Coordinates(doctorX, doctorY, 10)

        when:
        dalek.makeMove(newDoctorCoordinates)

        then:
        dalek.getCoordinates().getX() == expectedX
        dalek.getCoordinates().getY() == expectedY

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
