import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Full Daleks game application.
 */
public class Daleks extends Application {

    /**
     * Game launcher.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {
    }

    @Override
    public void start(final Stage primaryStage) {

        final Scene scene = new Scene(new GameController(primaryStage).getGameWindow());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Daleks");
        primaryStage.show();
    }
}
