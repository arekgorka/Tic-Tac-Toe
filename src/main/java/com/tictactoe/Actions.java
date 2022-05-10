package com.tictactoe;

import com.sun.security.jgss.GSSUtil;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class Actions {

    List<Button> buttonList = new ArrayList<>();
    //0 = button1
    //1 = button2
    //2 = button3
    //3 = button4
    //4 = button5
    //5 = button6
    //6 = button7
    //7 = button8
    //8 = button9
    int counterMove = 0;
    boolean playerWin = false;
    boolean computerWin = false;
    WindowAfterGame windowAfterGame = new WindowAfterGame();


    public void computerMove() {
        boolean end = false;
        Random random = new Random();
        while(!end && !windowAfterGame.yesAnswer) {
            Button button = buttonList.get(random.nextInt(buttonList.size()-1));
            if (button.getText().equals("")) {
                button.setText("O");
                end = true;
                counterMove++;
            }
        }
    }

    /*public void setupListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click");
            }
        };
        for (Button button: buttonList) {
            button.addActionListener(listener);
        }
    }*/

    public void addCounterMove() {
        counterMove++;
    }

    public void checkIfItsEnd() {
        //row
        if (buttonList.get(0).getText().equals("X") && buttonList.get(1).getText().equals("X") && buttonList.get(2).getText().equals("X") ||
                buttonList.get(3).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(5).getText().equals("X") ||
                buttonList.get(6).getText().equals("X") && buttonList.get(7).getText().equals("X") && buttonList.get(8).getText().equals("X") ||
                //columns
                buttonList.get(0).getText().equals("X") && buttonList.get(3).getText().equals("X") && buttonList.get(6).getText().equals("X") ||
                buttonList.get(1).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(7).getText().equals("X") ||
                buttonList.get(2).getText().equals("X") && buttonList.get(5).getText().equals("X") && buttonList.get(8).getText().equals("X") ||
                //diagonal
                buttonList.get(0).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(8).getText().equals("X") ||
                buttonList.get(2).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(6).getText().equals("X")) {
            System.out.println("PLAYER WIN!!!");
            playerWin = true;
            runWindowAfterGame();
            methodAfterYesOrNo();

            //finished();

        }
        if (buttonList.get(0).getText().equals("O") && buttonList.get(1).getText().equals("O") && buttonList.get(2).getText().equals("O") ||
                buttonList.get(3).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(5).getText().equals("O") ||
                buttonList.get(6).getText().equals("O") && buttonList.get(7).getText().equals("O") && buttonList.get(8).getText().equals("O") ||
                //columns
                buttonList.get(0).getText().equals("O") && buttonList.get(3).getText().equals("O") && buttonList.get(6).getText().equals("O") ||
                buttonList.get(1).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(7).getText().equals("O") ||
                buttonList.get(2).getText().equals("O") && buttonList.get(5).getText().equals("O") && buttonList.get(8).getText().equals("O") ||
                //diagonal
                buttonList.get(0).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(8).getText().equals("O") ||
                buttonList.get(2).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(6).getText().equals("O")) {
            System.out.println("COMPUTER WIN!!!");
            computerWin = true;
            runWindowAfterGame();
            methodAfterYesOrNo();
            //finished();
        }
        if (counterMove == 9 && !playerWin && !computerWin) {
            System.out.println("REMIS");
            runWindowAfterGame();
            methodAfterYesOrNo();

            //finished();
        }
    }
    public void methodAfterYesOrNo() {
        if (windowAfterGame.yesAnswer) {
            newGame();
        }
        if (windowAfterGame.noAnswer) {

        }
    }

    public void runWindowAfterGame() {
        if (playerWin) {
            windowAfterGame.displayWindowAfterGame("Endgame window", "WIN!!! Play again?");
        }
        if (computerWin) {
            windowAfterGame.displayWindowAfterGame("Endgame window", "You lose! Play again?");
        }
        if (!playerWin && !computerWin) {
            windowAfterGame.displayWindowAfterGame("Endgame window", "Draw... Play again?");
        }
    }

    /*public void setPopup() {
        Text textPopup = new Text();
        Popup popup = new Popup();
        popup.setX(150);
        popup.setY(150);

        if (playerWin) {
            textPopup.setText("Wygrałeś!!!");
        }
        if (computerWin) {
            textPopup.setText("Przegrałeś :(");
        }
        if (!playerWin && !computerWin) {
            textPopup.setText("REMIS");
        }
    }*/

    public void newGame() {
        counterMove = 0;
        for (Button button: buttonList) {
            button.setText("");
        }
        if (playerWin) {
            playerWin = false;
        }
        if (computerWin) {
            computerWin = false;
        }
    }

    /*public void finished() {
        for (Button button: buttonList) {
            button.setOnMouseEntered(null);
        }
    }*/

    public void endSetting() {
        windowAfterGame.yesAnswer = false;
        windowAfterGame.noAnswer = false;
    }

    public int getCounterMove() {
        return counterMove;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public boolean isComputerWin() {
        return computerWin;
    }

}
