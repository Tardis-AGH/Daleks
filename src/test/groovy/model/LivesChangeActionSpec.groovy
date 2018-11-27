package model

import spock.lang.Specification

class LivesChangeActionSpec extends Specification {
    def "Execute"(int currentLives, int change, Status expectedStatus) {
        given:
        Game game = new Game(new GameState(currentLives, 0, 0, 0, 0, 0), Mock(Board))
        Action action = new LivesChangeAction(change)

        when:
        Status status = action.execute(game)

        then:
        game.gameState.numberOfLives == currentLives + change
        status == expectedStatus

        where:
        currentLives | change | expectedStatus
        3            | -1     | Status.RESTART_GAME
        0            | -1     | Status.GAME_OVER
        3            | 1      | Status.CONTINUE_GAME
    }
}
