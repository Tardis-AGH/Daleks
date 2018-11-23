package model;

public class TeleportersChangeAction implements Action {

    private boolean teleporterGained;

    public TeleportersChangeAction(int teleporterChange) {
        this.teleporterGained = teleporterChange >= 0;
    }

    @Override
    public Status execute(Game game) {
        if (teleporterGained)
            game.getGameState().setNumberOfTeleporters(game.getGameState().getNumberOfTeleporters() + 1);
        else
            game.getGameState().setNumberOfTeleporters(game.getGameState().getNumberOfTeleporters() + 1);

        return Status.CONTINUE_GAME;
    }
}
