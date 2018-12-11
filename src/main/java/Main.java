import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.board.generator.BoardGenerator;
import model.game.Game;
import model.game.GameState;


public class Main extends Application {
    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {
    }

    @Override
    public void start(final Stage primaryStage) {
        GameState gameState = new GameState(3, 3, 0, 1, 1, 5);
        BoardGenerator boardGenerator = new BoardGenerator();
        final Scene scene = new Scene(new GameController(new Game(gameState, boardGenerator.generateNewBoard(1)), primaryStage).getGameWindow());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Daleks");
        primaryStage.show();
    }
}
