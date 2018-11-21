package presenter;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.BoardElement;


public class GameWindow extends Application {

    private Parent createContent() {

        //presenter window resolution
        final int nativeBoardWidth = 600;
        final int nativeHeight = 800;

        final int navigationButtonWidth = 70;

        //number of tiles in a row/column
        final int tileWidth = 10;

        VBox root = new VBox();
        root.setPrefSize(nativeBoardWidth, nativeHeight);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        int prefferedTileSize = nativeBoardWidth / tileWidth;

        for (int i = 0; i < tileWidth; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(prefferedTileSize);

            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(prefferedTileSize);

            gridPane.getRowConstraints().addAll(row);
            gridPane.getColumnConstraints().addAll(col);
        }

        root.getChildren().add(gridPane);

        //Controls
        GridPane controls = new GridPane();
        Button up = new Button();
        up.setTranslateX(((double)nativeBoardWidth/2)-((double)navigationButtonWidth/2));
        up.setPrefWidth(navigationButtonWidth);
        up.setPrefHeight(navigationButtonWidth);
        up.setText("^");

        Button down = new Button();
        down.setTranslateX(((double)nativeBoardWidth/2)-((double)navigationButtonWidth/2));
        down.setTranslateY(navigationButtonWidth);
        down.setPrefWidth(navigationButtonWidth);
        down.setPrefHeight(navigationButtonWidth);
        down.setText("v");

        Button left = new Button();
        left.setTranslateX(  (int)((nativeBoardWidth/2)-((double)(3*navigationButtonWidth/2)) ));
        left.setTranslateY(((double)navigationButtonWidth/2));
        left.setPrefWidth(navigationButtonWidth);
        left.setPrefHeight(navigationButtonWidth);
        left.setText("<");

        Button right = new Button();
        right.setTranslateX(((double)nativeBoardWidth/2)+((double)navigationButtonWidth/2));
        right.setTranslateY(((double)navigationButtonWidth/2));
        right.setPrefWidth(navigationButtonWidth);
        right.setPrefHeight(navigationButtonWidth);
        right.setText(">");

        controls.getChildren().add(up);
        controls.getChildren().add(down);
        controls.getChildren().add(left);
        controls.getChildren().add(right);

        //Restart button
        Button restart = new Button();
        restart.setText("Restart");
        restart.setTranslateX(50);
        restart.setTranslateY(50);
        controls.getChildren().add(restart);

        root.getChildren().add(controls);


        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Daleks");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private class PaneElement extends Pane {

        private PaneElement(BoardElement boardElement, int tileSize){
            Image icon = boardElement.image;
            double scale = icon.getWidth() / icon.getHeight();

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(tileSize);
            imageView.setFitWidth(tileSize*scale);
            this.getChildren().add(imageView);

        }
    }

}