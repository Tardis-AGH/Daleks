package model.action

import model.board.Board
import model.Game
import model.GameState
import model.Status
import spock.lang.Specification
import spock.lang.Unroll

class EnemyCountChangeActionSpec extends Specification {

    @Unroll
    def "changes enemy count #currentCount to #expectedStatus with #change"(int currentCount, int change,
            Status expectedStatus) {
        given:
        Game game = new Game(new GameState(0, 0, 0, 0, 0, currentCount), Mock(Board))
        Action action = new EnemyCountChangeAction(change)

        when:
        Status status = action.execute(game)

        then:
        game.gameState.enemyCount == currentCount + change
        status == expectedStatus

        where:
        currentCount | change | expectedStatus
        10           | -5     | Status.CONTINUE_GAME
        10           | -10    | Status.LEVEL_UP
        1            | 2      | Status.CONTINUE_GAME
    }
}
