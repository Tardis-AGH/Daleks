import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Board;
import model.Game;
import model.GameState;

public class Main extends Application {
    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        GameState gameState = new GameState(3, 3, 0, 1, 1, 5);
        Board board = new Board();
        final Scene scene = new Scene(new GameController(new Game(gameState, board), primaryStage).getGameWindow());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Daleks");
        primaryStage.show();
    }
}
