package com.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javafx.scene.input.MouseEvent.*;

public class TicTacToeApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Actions actions = new Actions();

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();

        //set size of text
        /* Text textX = new Text("X");
        textX.setFont(Font.font(72));*/

        Scene scene = new Scene(grid,450,450);
        primaryStage.setResizable(false);
        for (int i=0; i<3; i++) {
            for (int n=0; n<3; n++) {
                Button button = new Button();
                button.setPrefSize(150,150);
                grid.add(button,i,n,1,1);
                button.setOnMouseClicked(event -> {
                    if (!actions.isPlayerWin() && !actions.isComputerWin()) {
                        if (button.getText().isEmpty()) {
                            button.setText("X");
                            actions.addCounterMove();
                            actions.checkIfItsEnd();
                            if (actions.getCounterMove() < 9 && !actions.playerWin) {
                                actions.computerMove();
                                actions.addCounterMove();
                                actions.checkIfItsEnd();
                            }
                        }
                    }
                });
                actions.buttonList.add(button);
            }
        }
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
