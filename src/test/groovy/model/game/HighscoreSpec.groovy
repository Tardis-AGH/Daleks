package model.game

import controller.HighscoreManager
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HighscoreSpec extends Specification {

    @Shared
    String path = "test"

    @Unroll
    def "handles game highscore #score properly"(Integer score) {

        given:
        HighscoreManager highscoreManager = new HighscoreManager(path)

        when:
        highscoreManager.setHighScore(score)

        then:
        highscoreManager.getHighScore() == score

        where:
        score | _
        1     | _
        2     | _

    }

    def cleanupSpec() {
        File file = new File(path)
        file.delete()
    }
}
