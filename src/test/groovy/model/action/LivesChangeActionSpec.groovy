package model.action

import model.board.Board
import model.game.Game
import model.game.GameState
import model.game.Status
import spock.lang.Specification
import spock.lang.Unroll

class LivesChangeActionSpec extends Specification {

    @Unroll
    def "changes lives count #currentLives to #expectedStatus with #change"(int currentLives, int change,
            Status expectedStatus) {
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
        3            | -1     | Status.RESTART_LEVEL
        0            | -1     | Status.GAME_OVER
        3            | 1      | Status.CONTINUE_GAME
    }
}
