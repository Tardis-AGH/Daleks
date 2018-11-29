package model;

public class ScoreChangeAction implements Action {
    private int scoreChange;

    ScoreChangeAction(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    @Override
    public Status execute(Game game) {
        //TODO
        return null;
    }

    public int getScoreChange() {
        return scoreChange;
    }

    public void setScoreChange(int scoreChange) {
        this.scoreChange = scoreChange;
    }
}
