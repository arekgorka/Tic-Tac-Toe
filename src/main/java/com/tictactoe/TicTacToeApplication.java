package com.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Actions actions = new Actions();

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid,450,600);
        primaryStage.setResizable(false);

        Label initialLabel = new Label();
        initialLabel.setText("Player " + actions.getCounterPlayerWin() + " VS " + actions.getCounterComputerWin() + " Computer");
        initialLabel.setStyle("-fx-font-size: 30");

        for (int i=0; i<3; i++) {
            for (int n=0; n<3; n++) {
                Button button = new Button();
                button.setBackground(Background.fill(Color.GRAY));
                button.setBorder(Border.stroke(Color.BLACK));
                button.setPrefSize(150,150);
                grid.add(button,i,n,1,1);
                button.setOnMouseClicked(event -> {
                    if (!actions.isPlayerWin() && !actions.isComputerWin()) {
                        if (button.getText().isEmpty()) {
                            button.setBackground(Background.fill(Color.DARKSEAGREEN));
                            button.setText("X");
                            button.setStyle("-fx-font-size: 60");
                            actions.addCounterMove();
                            actions.checkIfItsEnd();
                            if (actions.getCounterMove() < 9 && !actions.playerWin && !actions.isNewGame) {
                                actions.computerMove();
                                actions.checkIfItsEnd();
                            }
                            if (actions.isNewGame) {
                                actions.isNewGame = false;
                            }
                        }
                    }
                    actions.endSetting();
                    initialLabel.setText("Player " + actions.getCounterPlayerWin() + " VS " + actions.getCounterComputerWin() + " Computer");
                });

                actions.buttonList.add(button);
            }
        }


        VBox layoutVBox = new VBox(10);
        layoutVBox.setPrefSize(450,150);
        layoutVBox.setBackground(Background.fill(Color.LIGHTGREY));
        layoutVBox.setBorder(Border.stroke(Color.BLACK));
        layoutVBox.setAlignment(Pos.CENTER);
        grid.add(layoutVBox,0,3,3,1);

        layoutVBox.getChildren().addAll(initialLabel);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();

        actions.runWindowBeforeGame();
    }
}
