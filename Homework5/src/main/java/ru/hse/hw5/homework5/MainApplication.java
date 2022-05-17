package ru.hse.hw5.homework5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hse.hw5.homework5.app.configure.Configure;
import ru.hse.hw5.homework5.app.controllers.GameController;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Configure.SCENE_WIDTH, Configure.SCENE_HEIGHT);
        stage.setTitle("Jigsaw Tetris");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        GameController controller = fxmlLoader.getController();
        controller.figurePane.setLayoutX(controller.figurePlace.getLayoutX() + controller.vBox.getLayoutX());
        controller.figurePane.setLayoutY(controller.figurePlace.getLayoutY() + controller.vBox.getLayoutY());
     }


    public static void main(String[] args) {
        launch();
    }
}