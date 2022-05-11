package com.tictactoe;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    int counterPlayerWin = 0;
    int counterComputerWin = 0;


    WindowAfterGame windowAfterGame = new WindowAfterGame();
    WindowBeforeGame windowBeforeGame = new WindowBeforeGame();
    WindowAfterRound windowAfterRound = new WindowAfterRound();


    public void computerMove() {
        boolean end = false;
        Random random = new Random();
        while(!end && !windowAfterGame.yesAnswer) {
            Button button = buttonList.get(random.nextInt(buttonList.size()-1));
            if (button.getText().equals("")) {
                button.setText("O");
                button.setStyle("-fx-font-size: 60");
                button.setBackground(Background.fill(Color.DARKSALMON));
                end = true;
                counterMove++;
            }
        }
    }

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
            counterPlayerWin++;
            runWindowAfterGame();
            methodAfterYesOrNo();
            runWindowAfterRound();
            newRound();
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
            counterComputerWin++;
            runWindowAfterGame();
            methodAfterYesOrNo();
            runWindowAfterRound();
            newRound();
        }
        if (counterMove == 9 && !playerWin && !computerWin) {
            System.out.println("REMIS");
            runWindowAfterGame();
            methodAfterYesOrNo();
            runWindowAfterRound();
            newRound();
        }
    }
    public void methodAfterYesOrNo() {
        if (windowAfterGame.yesAnswer) {
            newGame();
        }
        if (windowAfterGame.noAnswer) {
            System.exit(0);
        }
    }

    public void runWindowAfterGame() {
        if (windowBeforeGame.getAmountOfWinningRound() == counterPlayerWin ||
                windowBeforeGame.getAmountOfWinningRound() == counterComputerWin) {
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
    }

    public void runWindowAfterRound() {
        if (counterMove != 0) {
            if (playerWin) {
                windowAfterRound.displayWindowAfterRound("Endround window", "You win round!");
            }
            if (computerWin) {
                windowAfterRound.displayWindowAfterRound("Endround window", "You lose round!");
            }
            if (!playerWin && !computerWin) {
                windowAfterRound.displayWindowAfterRound("Endround window", "Draw...");
            }
        }
    }

    public void newRound() {
        counterMove = 0;
        for (Button button: buttonList) {
            button.setText("");
            button.setBackground(Background.fill(Color.GRAY));
        }
        if (playerWin) {
            playerWin = false;
        }
        if (computerWin) {
            computerWin = false;
        }
    }

    public void newGame() {
        counterPlayerWin = 0;
        counterComputerWin = 0;
        newRound();
    }

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
    public int getCounterPlayerWin() {
        return counterPlayerWin;
    }
    public int getCounterComputerWin() {
        return counterComputerWin;
    }

}
