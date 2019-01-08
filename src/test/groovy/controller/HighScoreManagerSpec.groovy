package controller

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HighScoreManagerSpec extends Specification {

    @Shared
    private static final String PATH = 'test'

    @Unroll
    def "handles game highscore #score properly"(Integer score) {
        given:
        HighScoreManager highScoreManager = new HighScoreManager(PATH)

        when:
        highScoreManager.highScore = score

        then:
        highScoreManager.highScore == score

        where:
        score | _
        1     | _
        2     | _
    }

    def cleanupSpec() {
        File file = new File(PATH)
        file.delete()
    }
}
