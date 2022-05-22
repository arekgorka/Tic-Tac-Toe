package com.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class WindowBeforeGame {

    static int amountOfWinningRound = 1;
    static boolean easyMode = true;
    static boolean mediumMode = false;
    static boolean hardMode = false;

    public static boolean isEasyMode() {
        return easyMode;
    }

    public static boolean isMediumMode() {
        return mediumMode;
    }

    public static boolean isHardMode() {
        return hardMode;
    }

    public static void displayWindowBeforeGame(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);
        window.setResizable(false);
        Label label = new Label();
        label.setText(message);


        TextField textField = new TextField();
        textField.setMaxWidth(150);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("change listener");
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]",""));
            }
        });

        Button playButton = new Button("Let's play!");

        playButton.setOnAction(event -> {
            if(textField.getText().isEmpty()) {
                textField.setText("Input integer!");
            } else {
                try {
                    int scannedAmount = Integer.parseInt(textField.getText());
                    if (scannedAmount > 0) {
                        amountOfWinningRound = scannedAmount;
                    window.close();
                    } else {
                        textField.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                        textField.setText("positive integer!");
                    }
                } catch (NumberFormatException e) {
                    textField.setText("Input integer!");
                }
            }
        });

        Button easyButton = new Button("Easy");
        easyButton.setOnAction(event -> {
            easyMode = true;
            mediumMode = false;
            hardMode = false;
            System.out.println("pressed Easy Button");
        });
        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(event -> {
            easyMode = false;
            mediumMode = true;
            hardMode = false;
            System.out.println("pressed Medium Button");
        });
        Button hardButton = new Button("Hard");
        hardButton.setOnAction(event -> {
            easyMode = false;
            mediumMode = false;
            hardMode = true;
            System.out.println("pressed Hard Button");
        });

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(easyButton,mediumButton,hardButton);
        hBox.setAlignment(Pos.CENTER);

        Label labelLevel = new Label("Select a level:");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, textField, playButton, labelLevel, hBox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static int getAmountOfWinningRound() {
        return amountOfWinningRound;
    }
}
