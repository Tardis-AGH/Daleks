package model

import spock.lang.Specification

class TeleportersChangeActionTest extends Specification {
    def "Execute"(int currentTeleporters, int change, Status expectedStatus, int expectedTeleporters) {
        given:
        Game game = new Game(new GameState(0, currentTeleporters, 0, 0, 0, 0), Mock(Board))
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
