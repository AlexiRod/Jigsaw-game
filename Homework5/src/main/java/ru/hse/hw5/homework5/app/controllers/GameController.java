package ru.hse.hw5.homework5.app.controllers;

import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.*;

import ru.hse.hw5.homework5.app.configure.*;
import ru.hse.hw5.homework5.classes.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public GridPane gridPane;
    public GridPane figurePane;
    public VBox vBox;
    public Button bStartGame;
    public Button bEndGame;
    public Button bExitGame;
    public Button figurePlace;
    public Label lTime;

    private final FigureUtility util = new FigureUtility();
    private final Light.Point point = new Light.Point();
    private final Grid grid = new Grid(Configure.GAME_FIELD_WIDTH);
    private final Figure figure = util.getFigure();

    private int placedCount = 0, gameTime;

    Timeline timeline = new Timeline (
            new KeyFrame (
                    Duration.millis(1000),
                    ae -> {
                        gameTime++;
                        lTime.setText("Время игры: " + (gameTime / 60) + "m " + (gameTime % 60) + "s");
                    }
            )
    );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeGridPane();
        initializeVBox();
        initializeFigurePane();

        figurePane.setDisable(true);
        bEndGame.setDisable(true);
        timeline.setCycleCount(-1);
    }

    private void initializeGridPane() {
        for (int i = 0; i < Configure.GAME_FIELD_WIDTH; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(Configure.SQUARE_SIZE));
            gridPane.getRowConstraints().add(new RowConstraints(Configure.SQUARE_SIZE));
        }

       fillGridPane();
    }

    private void initializeVBox() {
        vBox.setLayoutX(gridPane.getLayoutX() + gridPane.getPrefWidth() + 15);
        vBox.setLayoutY(gridPane.getLayoutY());
        vBox.setPrefWidth(Configure.SCENE_WIDTH - vBox.getLayoutX() - 15);
        vBox.setPrefHeight(gridPane.getPrefHeight());

        double width = vBox.getPrefWidth() - 10;
        bStartGame.setPrefWidth(width);
        bEndGame.setPrefWidth(width);
        bExitGame.setPrefWidth(width);

        figurePlace.setPrefWidth(Configure.SQUARE_SIZE * Configure.CELL_WIDTH);
        figurePlace.setPrefHeight(Configure.SQUARE_SIZE * Configure.CELL_HEIGHT);
    }

    private void initializeFigurePane() {
        for (int i = 0; i < Configure.CELL_WIDTH; i++) {
            figurePane.getColumnConstraints().add(new ColumnConstraints(Configure.SQUARE_SIZE));
            figurePane.getRowConstraints().add(new RowConstraints(Configure.SQUARE_SIZE));
        }

        for (int i = 0; i < Configure.CELL_HEIGHT; i++) {
            for (int j = 0; j < Configure.CELL_WIDTH; j++) {
                Button cell = new Button();
                cell.setStyle("-fx-background-color: transparent;");
                cell.setMaxHeight(Double.MAX_VALUE);
                cell.setMaxWidth(Double.MAX_VALUE);
                cell.setOnMousePressed(this::onFigurePanePressed);
                cell.setOnMouseDragged(this::onFigurePaneDragged);
                cell.setOnMouseReleased(this::onFigurePaneReleased);
                figurePane.add(cell, i, j);
            }
        }
        //drawFigurePane();
    }


    private void fillGridPane() {
        gridPane.getChildren().clear();
        for (int i = 0; i < Configure.GAME_FIELD_HEIGHT; i++) {
            for (int j = 0; j < Configure.GAME_FIELD_WIDTH; j++) {
                Button cell = new Button();
                if ((i / Configure.CELL_WIDTH + j / Configure.CELL_HEIGHT) % 2 == 0) {
                    cell.setStyle("-fx-background-color: white; -fx-border-color: black;");
                } else {
                    cell.setStyle("-fx-background-color: lightBlue; -fx-border-color: black;");
                }
                cell.setMaxHeight(Double.MAX_VALUE);
                cell.setMaxWidth(Double.MAX_VALUE);
                gridPane.add(cell, i, j);
            }
        }
    }

    private void drawFigurePane() {
        figurePane.setPrefWidth(figure.getWidth() * Configure.SQUARE_SIZE);
        figurePane.setPrefHeight(figure.getHeight() * Configure.SQUARE_SIZE);

        for (int i = 0; i < Configure.CELL_HEIGHT; i++) {
            for (int j = 0; j < Configure.CELL_WIDTH; j++) {
                Button cell = (Button) figurePane.getChildren().get(j * Configure.CELL_WIDTH + i);
                cell.setStyle("-fx-background-color: transparent;");
            }
        }

        for (int i = 0; i < figure.getHeight(); i++) {
            for (int j = 0; j < figure.getWidth(); j++) {
                Button cell = (Button) figurePane.getChildren().get(j * Configure.CELL_WIDTH + i);
                if (figure.isFilled(i, j)) {
                    cell.setStyle("-fx-background-color: blue; -fx-border-color: black;");
                }
            }
        }
    }


    public void onStartGameButtonClick(MouseEvent mouseEvent) {
        bStartGame.setDisable(true);
        bEndGame.setDisable(false);
        placedCount = gameTime = 0;

        fillGridPane();
        figurePane.setDisable(false);
        figure.replace(util.getFigure());
        drawFigurePane();

        grid.resize(Configure.GAME_FIELD_WIDTH);
        timeline.play();
    }

    public void onEndGameButtonClick(MouseEvent mouseEvent) {
        figurePane.setDisable(true);
        timeline.stop();
        bEndGame.setDisable(true);
        bStartGame.setDisable(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация об игре");
        alert.setHeaderText("Игра завершена");
        alert.setContentText(lTime.getText() + "\nВы сделали: " + placedCount + " ходов");
        alert.showAndWait();
    }

    public void onExitGameButtonClick(MouseEvent mouseEvent) {
        ((Stage) bExitGame.getScene().getWindow()).close();
    }


    public void onFigurePanePressed(MouseEvent mouseEvent) {
        point.setX(figurePane.getLayoutX() - mouseEvent.getSceneX());
        point.setY(figurePane.getLayoutY() - mouseEvent.getSceneY());
    }

    public void onFigurePaneDragged(MouseEvent mouseEvent) {
        figurePane.setLayoutX(point.getX() + mouseEvent.getSceneX());
        figurePane.setLayoutY(point.getY() + mouseEvent.getSceneY());
    }

    public void onFigurePaneReleased(MouseEvent mouseEvent) {
        int coordX = (int) Math.round((figurePane.getLayoutX() - gridPane.getLayoutX()) / Configure.SQUARE_SIZE);
        int coordY = (int) Math.round((figurePane.getLayoutY() - gridPane.getLayoutY()) / Configure.SQUARE_SIZE);

        if (grid.isFilled(figure, coordX, coordY)) {
            figurePane.setLayoutX(figurePlace.getLayoutX() + vBox.getLayoutX());
            figurePane.setLayoutY(figurePlace.getLayoutY() + vBox.getLayoutY());
            return;
        }

        placedCount++;
        grid.placeFigure(figure, coordX, coordY);
        for (int i = coordY; i < coordY + figure.getHeight(); ++i) {
            for (int j = coordX; j < coordX + figure.getWidth(); ++j) {
                if (figure.isFilled(i - coordY, j - coordX)) {
                    int number = j * Configure.GAME_FIELD_WIDTH + i;
                    gridPane.getChildren().get(number)
                            .setStyle("-fx-background-color: blue; -fx-border-color: black;");
                }
            }
        }
        figure.replace(util.getFigure());
        drawFigurePane();
        figurePane.setLayoutX(figurePlace.getLayoutX() + vBox.getLayoutX());
        figurePane.setLayoutY(figurePlace.getLayoutY() + vBox.getLayoutY());
    }
}