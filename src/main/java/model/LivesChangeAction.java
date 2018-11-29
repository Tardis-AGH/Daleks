package model;

public class LivesChangeAction implements Action {

    private int livesChange;

    LivesChangeAction(int livesChange) {
        this.livesChange = livesChange;
    }

    @Override
    public Status execute(Game game) {
        //TODO
        return null;
    }


    public int getLivesChange() {
        return livesChange;
    }

    public void setLivesChange(int livesChange) {
        this.livesChange = livesChange;
    }
}
