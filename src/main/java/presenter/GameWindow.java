package presenter;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;


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
        //Add elements
        final Dalek dalek1 = new Dalek(nativeBoardWidth/tileWidth);
        GridPane.setRowIndex(dalek1, 4);
        GridPane.setColumnIndex(dalek1, 5);
        gridPane.getChildren().add(dalek1);

        final Dalek dalek2 = new Dalek(nativeBoardWidth/tileWidth);
        GridPane.setRowIndex(dalek2, 3);
        GridPane.setColumnIndex(dalek2, 3);
        gridPane.getChildren().add(dalek2);

        final Dalek dalek3 = new Dalek(nativeBoardWidth/tileWidth);
        GridPane.setRowIndex(dalek3, 6);
        GridPane.setColumnIndex(dalek3, 9);
        gridPane.getChildren().add(dalek3);

        final Dalek dalek4 = new Dalek(nativeBoardWidth/tileWidth);
        GridPane.setRowIndex(dalek4, 4);
        GridPane.setColumnIndex(dalek4, 2);
        gridPane.getChildren().add(dalek4);

        final Tardis tardis = new Tardis(nativeBoardWidth/tileWidth);
        GridPane.setRowIndex(tardis, 1);
        GridPane.setColumnIndex(tardis, 3);
        gridPane.getChildren().add(tardis);

        final Heart heart = new Heart(nativeBoardWidth/tileWidth);
        GridPane.setRowIndex(heart, 8);
        GridPane.setColumnIndex(heart, 4);
        gridPane.getChildren().add(heart);


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




    //NOT GOOD CODING
    private class Dalek extends Pane {
        private Dalek(int size) {
            Image icon = new Image("../../../images/dalek/43043-200.png");
            double scale = icon.getWidth() / icon.getHeight();

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(size);
            imageView.setFitWidth(size*scale);
            this.getChildren().add(imageView);
        }
    }

    private class Heart extends Pane {
        private Heart(int size) {
            Image icon = new Image("../../../images/powerup/148836.png");
            double scale = icon.getWidth() / icon.getHeight();

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(size);
            imageView.setFitWidth(size*scale);
            this.getChildren().add(imageView);
        }
    }

    private class Tardis extends Pane {
        private Tardis(int size) {
            Image icon = new Image("../../../images/powerup/1472711-84.png");
            double scale = icon.getWidth() / icon.getHeight();

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(size);
            imageView.setFitWidth(size*scale);
            this.getChildren().add(imageView);
        }
    }
}