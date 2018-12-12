package model.action.gamestate

import javafx.collections.FXCollections
import model.action.Action
import model.board.factory.ConcreteBoardFactory
import model.element.dynamicelement.Doctor
import model.game.Game
import model.game.Status
import spock.lang.Specification
import spock.lang.Unroll

class ScoreChangeActionSpec extends Specification {

    @Unroll
    def "changes score from #currentScore and updates #currentHighscore to #expectedHighscore with #change"(
            int currentScore, int change, Status expectedStatus, int currentHighscore,
            int expectedHighscore) {
        given:
        Game game = new Game(new ConcreteBoardFactory(FXCollections.observableSet(), Mock(Doctor), 10, 10))
        game.gameState.currentScore = currentScore
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