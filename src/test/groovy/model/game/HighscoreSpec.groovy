package model.game

import controller.HighscoreManager
import spock.lang.Shared
import spock.lang.Specification

class HighscoreSpec extends Specification {

    @Shared
    String path = "test"

    def "handles game highscore #score properly"(Integer score) {

        given:
        HighscoreManager highscoreManager = new HighscoreManager(path)

        when:
        highscoreManager.setHighScore(score);

        then:
        highscoreManager.getHighScore() == score

        where:
        score | _
        1     | _
        2     | _

    }

    def cleanupSpec() {
        File file = new File(path);
        file.delete()
    }
}
