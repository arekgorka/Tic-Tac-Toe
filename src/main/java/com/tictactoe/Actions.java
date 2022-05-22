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
    //4 = button5 - middle
    //5 = button6
    //6 = button7
    //7 = button8
    //8 = button9
    int counterMove = 0;
    boolean playerWin = false;
    boolean computerWin = false;
    int counterPlayerWin = 0;
    int counterComputerWin = 0;
    boolean isNewGame = false;


    public void computerMove() {
        if (WindowBeforeGame.isEasyMode()) {
            computerEasyModeMove();
        }
        if (WindowBeforeGame.isMediumMode()) {
            computerMediumModeMove();
        }
        if (WindowBeforeGame.isHardMode()) {
            computerHardModeMove();
        }
        counterMove++;
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
            System.out.println("DRAW");
            runWindowAfterGame();
            methodAfterYesOrNo();
            runWindowAfterRound();
            newRound();
        }
    }
    public void methodAfterYesOrNo() {
        if (WindowAfterGame.yesAnswer) {
            newGame();
        }
        if (WindowAfterGame.noAnswer) {
            System.exit(0);
        }
    }

    public void runWindowBeforeGame() {
        WindowBeforeGame.displayWindowBeforeGame("Staring window","To how many victories do you want to play?");
    }

    public void runWindowAfterGame() {
        if (WindowBeforeGame.getAmountOfWinningRound() == counterPlayerWin ||
                WindowBeforeGame.getAmountOfWinningRound() == counterComputerWin) {
            if (playerWin) {
                WindowAfterGame.displayWindowAfterGame("Endgame window", "WIN!!! Play again?");
            }
            if (computerWin) {
                WindowAfterGame.displayWindowAfterGame("Endgame window", "You lose! Play again?");
            }
            if (!playerWin && !computerWin) {
                WindowAfterGame.displayWindowAfterGame("Endgame window", "Draw... Play again?");
            }
        }
    }

    public void runWindowAfterRound() {
        if (counterMove != 0) {
            if (playerWin) {
                WindowAfterRound.displayWindowAfterRound("End round window", "You win round!");
            }
            if (computerWin) {
                WindowAfterRound.displayWindowAfterRound("End round window", "You lose round!");
            }
            if (!playerWin && !computerWin) {
                WindowAfterRound.displayWindowAfterRound("End round window", "Draw...");
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
        isNewGame = true;
    }

    public void endSetting() {
        WindowAfterGame.yesAnswer = false;
        WindowAfterGame.noAnswer = false;
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

    public void computerEasyModeMove() {
        boolean end = false;
        Random random = new Random();
        while(!end && !WindowAfterGame.yesAnswer) {
            Button button = buttonList.get(random.nextInt(buttonList.size()-1));
            if (button.getText().equals("")) {
                button.setText("O");
                button.setStyle("-fx-font-size: 60");
                button.setBackground(Background.fill(Color.DARKSALMON));
                end = true;
            }
        }
    }

    public void computerMediumModeMove() {
        if (buttonList.get(4).getText().equals("") && (buttonList.get(0).getText().equals("X") || buttonList.get(1).getText().equals("X")
                || buttonList.get(2).getText().equals("X") || buttonList.get(3).getText().equals("X") || buttonList.get(5).getText().equals("X")
                || buttonList.get(6).getText().equals("X") || buttonList.get(7).getText().equals("X") || buttonList.get(8).getText().equals("X"))) {
            System.out.println("check");
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(2).getText().equals("")){
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(6).getText().equals("")){
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(8).getText().equals("")){
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else {
            computerEasyModeMove();
        }
    }

    public void computerHardModeMove() {
        //Moves provide to win computer
        if (buttonList.get(0).getText().equals("O") && buttonList.get(2).getText().equals("O") && buttonList.get(1).getText().equals("")) {
            Button button = buttonList.get(1);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(3).getText().equals("O") && buttonList.get(5).getText().equals("O") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(6).getText().equals("O") && buttonList.get(8).getText().equals("O") && buttonList.get(7).getText().equals("")) {
            Button button = buttonList.get(7);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("O") && buttonList.get(6).getText().equals("O") && buttonList.get(3).getText().equals("")) {
            Button button = buttonList.get(3);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(1).getText().equals("O") && buttonList.get(7).getText().equals("O") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("O") && buttonList.get(8).getText().equals("O") && buttonList.get(5).getText().equals("")) {
            Button button = buttonList.get(5);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("O") && buttonList.get(8).getText().equals("O") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("O") && buttonList.get(6).getText().equals("O") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("O") && buttonList.get(1).getText().equals("O") && buttonList.get(2).getText().equals("")) {
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(1).getText().equals("O") && buttonList.get(2).getText().equals("O") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(3).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(5).getText().equals("")) {
            Button button = buttonList.get(5);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("O") && buttonList.get(5).getText().equals("O") && buttonList.get(3).getText().equals("")) {
            Button button = buttonList.get(3);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(6).getText().equals("O") && buttonList.get(7).getText().equals("O") && buttonList.get(8).getText().equals("")) {
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(7).getText().equals("O") && buttonList.get(8).getText().equals("O") && buttonList.get(6).getText().equals("")) {
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("O") && buttonList.get(3).getText().equals("O") && buttonList.get(6).getText().equals("")) {
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(3).getText().equals("O") && buttonList.get(6).getText().equals("O") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(1).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(7).getText().equals("")) {
            Button button = buttonList.get(7);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("O") && buttonList.get(7).getText().equals("O") && buttonList.get(1).getText().equals("")) {
            Button button = buttonList.get(1);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("O") && buttonList.get(5).getText().equals("O") && buttonList.get(8).getText().equals("")) {
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(5).getText().equals("O") && buttonList.get(8).getText().equals("O") && buttonList.get(2).getText().equals("")) {
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(8).getText().equals("")) {
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("O") && buttonList.get(8).getText().equals("O") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("O") && buttonList.get(4).getText().equals("O") && buttonList.get(6).getText().equals("")) {
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("O") && buttonList.get(6).getText().equals("O") && buttonList.get(2).getText().equals("")) {
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        }
        // Moves against player
        else if (buttonList.get(0).getText().equals("X") && buttonList.get(2).getText().equals("X") && buttonList.get(1).getText().equals("")){
            Button button = buttonList.get(1);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(3).getText().equals("X") && buttonList.get(5).getText().equals("X") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(6).getText().equals("X") && buttonList.get(8).getText().equals("X") && buttonList.get(7).getText().equals("")) {
            Button button = buttonList.get(7);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("X") && buttonList.get(6).getText().equals("X") && buttonList.get(3).getText().equals("")) {
            Button button = buttonList.get(3);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(1).getText().equals("X") && buttonList.get(7).getText().equals("X") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("X") && buttonList.get(8).getText().equals("X") && buttonList.get(5).getText().equals("")) {
            Button button = buttonList.get(5);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("X") && buttonList.get(8).getText().equals("X") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("X") && buttonList.get(6).getText().equals("X") && buttonList.get(4).getText().equals("")) {
            Button button = buttonList.get(4);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("X") && buttonList.get(1).getText().equals("X") && buttonList.get(2).getText().equals("")) {
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(1).getText().equals("X") && buttonList.get(2).getText().equals("X") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(3).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(5).getText().equals("")) {
            Button button = buttonList.get(5);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(5).getText().equals("X") && buttonList.get(3).getText().equals("")) {
            Button button = buttonList.get(3);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(6).getText().equals("X") && buttonList.get(7).getText().equals("X") && buttonList.get(8).getText().equals("")) {
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(7).getText().equals("X") && buttonList.get(8).getText().equals("X") && buttonList.get(6).getText().equals("")) {
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("X") && buttonList.get(3).getText().equals("X") && buttonList.get(6).getText().equals("")) {
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(3).getText().equals("X") && buttonList.get(6).getText().equals("X") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(1).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(7).getText().equals("")) {
            Button button = buttonList.get(7);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(7).getText().equals("X") && buttonList.get(1).getText().equals("")) {
            Button button = buttonList.get(1);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("X") && buttonList.get(5).getText().equals("X") && buttonList.get(8).getText().equals("")) {
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(5).getText().equals("X") && buttonList.get(8).getText().equals("X") && buttonList.get(2).getText().equals("")) {
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(0).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(8).getText().equals("")) {
            Button button = buttonList.get(8);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(8).getText().equals("X") && buttonList.get(0).getText().equals("")) {
            Button button = buttonList.get(0);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(2).getText().equals("X") && buttonList.get(4).getText().equals("X") && buttonList.get(6).getText().equals("")) {
            Button button = buttonList.get(6);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else if (buttonList.get(4).getText().equals("X") && buttonList.get(6).getText().equals("X") && buttonList.get(2).getText().equals("")) {
            Button button = buttonList.get(2);
            button.setText("O");
            button.setStyle("-fx-font-size: 60");
            button.setBackground(Background.fill(Color.DARKSALMON));
        } else {
            computerMediumModeMove();
        }
    }
}
