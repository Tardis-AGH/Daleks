package model.action.move

import javafx.collections.FXCollections
import model.action.Action
import model.board.Coordinates
import model.board.Move
import model.board.factory.ConcreteBoardFactory
import model.element.dynamicelement.Doctor
import model.game.Game
import model.game.Status
import spock.lang.Specification
import spock.lang.Unroll

class TeleportationActionSpec extends Specification {

    @Unroll
    def "teleports the Doctor and continues the move when #numberOfTeleportersBefore is greater than zero"(
            int numberOfTeleportersBefore,
            int numberOfTeleportersAfter, Status expectedStatus) {
        given:
        Game game = new Game(
                new ConcreteBoardFactory(FXCollections.observableSet(), new Doctor(Mock(Coordinates)), 10, 10))
        game.gameState.numberOfTeleporters = numberOfTeleportersBefore

        when:
        List<Action> actions = game.board.doctor.makeMove(Move.TELEPORT)

        then:
        actions.size() == 1
        Status status = actions.get(0).execute(game)
        status == expectedStatus
        game.gameState.numberOfTeleporters == numberOfTeleportersAfter

        where:
        numberOfTeleportersBefore | numberOfTeleportersAfter | expectedStatus
        0                         | 0                        | Status.SKIP_MOVE
        1                         | 0                        | Status.CONTINUE_GAME
        2                         | 1                        | Status.CONTINUE_GAME
    }
}
