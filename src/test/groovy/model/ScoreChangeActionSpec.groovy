package model

import spock.lang.Specification
import spock.lang.Unroll

class ScoreChangeActionSpec extends Specification {
    @Unroll
    def "Currenct score #currentScore is changed by #change and previous highscore #currentHighscore now equals to #expectedHighscore"(int currentScore, int change, Status expectedStatus, int currentHighscore, int expectedHighscore) {
        given:
        Game game = new Game(new GameState(0, 0, currentScore, 0, 0, 0), Mock(Board))
        Action action = new ScoreChangeAction(change)

        when:
        Status status = action.execute(game)

        then:
        game.gameState.currentScore == currentScore + change
        status == expectedStatus

        where:
        currentScore | change | expectedStatus       | currentHighscore | expectedHighscore
        3            | 1      | Status.CONTINUE_GAME | 6                | 6
        3            | 1      | Status.CONTINUE_GAME | 4                | 4
        3            | 3      | Status.CONTINUE_GAME | 2                | 4
    }
}