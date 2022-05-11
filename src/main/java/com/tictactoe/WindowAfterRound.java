package com.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowAfterRound {

    public void displayWindowAfterRound(String title, String message) {

        Stage windowAfterRound = new Stage();
        windowAfterRound.initModality(Modality.APPLICATION_MODAL);
        windowAfterRound.setTitle(title);
        windowAfterRound.setMinWidth(200);
        windowAfterRound.setMinHeight(150);
        windowAfterRound.setResizable(false);
        Label label = new Label();
        label.setText(message);

        Button okButton = new Button("OK");

        okButton.setOnAction(event -> {
            windowAfterRound.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        windowAfterRound.setScene(scene);
        windowAfterRound.showAndWait();
    }
}
