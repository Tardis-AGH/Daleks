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
    def "changes score from #currentScore by #change and updates high score #currentHighscore to #expectedHighscore"(
            int currentScore, int change, int currentHighscore, int expectedHighscore) {
        given:
        Game game = new Game(new ConcreteBoardFactory(FXCollections.observableSet(), Mock(Doctor), 10, 10))
        game.gameState.currentScore = currentScore
        game.gameState.highestScore = currentHighscore
        Action action = new ScoreChangeAction(change)

        when:
        Status status = action.execute(game)

        then:
        game.gameState.currentScore == currentScore + change
        game.gameState.highestScore == expectedHighscore
        status == Status.CONTINUE_GAME

        where:
        currentScore | change | currentHighscore | expectedHighscore
        3            | 1      | 6                | 6
        3            | 1      | 4                | 4
        3            | 3      | 2                | 6
    }
}
