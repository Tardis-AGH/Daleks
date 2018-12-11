package model.action

import javafx.collections.FXCollections
import model.board.generator.TestBoardGenerator
import model.element.dynamicelement.Doctor
import model.game.Game
import model.game.Status
import spock.lang.Specification
import spock.lang.Unroll

class TeleportersChangeActionSpec extends Specification {

    @Unroll
    def "changes teleporter count from #currentTeleporters to #expectedTeleporters using #change"(
            int currentTeleporters, int change, Status expectedStatus, int expectedTeleporters) {
        given:
        Game game = new Game(new TestBoardGenerator(FXCollections.observableSet(), Mock(Doctor)))
        game.getGameState().setNumberOfTeleporters(currentTeleporters)
        Action action = new TeleportersChangeAction(change)

        when:
        Status status = action.execute(game)

        then:
        game.gameState.numberOfTeleporters == expectedTeleporters
        status == expectedStatus

        where:
        currentTeleporters | change | expectedStatus       | expectedTeleporters
        3                  | 1      | Status.CONTINUE_GAME | 4
        3                  | -1     | Status.CONTINUE_GAME | 2
        3                  | -3     | Status.CONTINUE_GAME | 0
        3                  | -5     | Status.CONTINUE_GAME | 0
    }
}
