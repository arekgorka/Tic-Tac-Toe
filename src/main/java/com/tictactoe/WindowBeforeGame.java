package com.tictactoe;

import com.sun.javafx.scene.control.InputField;
import com.sun.javafx.scene.control.IntegerField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Scanner;

public class WindowBeforeGame {

    static int amountOfWinningRound = 1;

    public static void displayWindowBeforeGame(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(150);
        window.setResizable(false);
        Label label = new Label();
        label.setText(message);


        TextField textField = new TextField();
        textField.setMaxWidth(150);

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("change listener");
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });

        Button letsplayButton = new Button("Let's play!");

        letsplayButton.setOnAction(event -> {
            /*if(textField.getText().isEmpty()) {
                textField.setText("Input integer!");
            } else {
                try {*/
                    int scannedAmount = Integer.parseInt(textField.getText());
                    if (scannedAmount > 0) {
                        amountOfWinningRound = scannedAmount;
                    window.close();
                    } /*else {
                        textField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                        textField.setText("positive integer!");
                    }
                } catch (NumberFormatException e) {
                    textField.setText("Input integer!");
                }
            }*/
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, textField, letsplayButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public int getAmountOfWinningRound() {
        return amountOfWinningRound;
    }
}
