package ControllerInterface;

import Data.DataBackFromController;
import GraphicInterface.UserInteraction;

public class Controller {

    private UserInteraction privateInterface;

    private DataBackFromController dataBackFromController;

    public Controller(DataBackFromController dataBackFromController) {

        this.dataBackFromController = dataBackFromController;

        this.privateInterface = new UserInteraction(this);

        this.privateInterface.getImageSetterForUser().blankImageSetterForUser();
        this.privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("welcome");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new checkThread().start();
    }

    private void newGame() {

        opponentChoice = "";
        userChoice = "";

        privateInterface.getImageSetterForOpponent().stringImageSetterForOpponent("blank");

        privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("choose");

        privateInterface.getImageSetterForUser().optionImageSetterForUser();

        setButtonsActive();
    }


    public boolean isButtonsActive() {
        return buttonsActive;
    }

    private void setButtonsInactive() {
        buttonsActive = false;
    }

    private void setButtonsActive() {
        buttonsActive = true;
    }

    private boolean buttonsActive = true;


    private void gameMessageForTransmission(String message) {
        dataBackFromController.dataBackFromControllerFunction("##" + message);
    }


    public void gameMessageController(String message) {
        if (isButtonsActive()) {

            if (message.equalsIgnoreCase("rock")) {
                userChoice = "rock";
                gameMessageForTransmission("R");
            }

            if (message.equalsIgnoreCase("paper")) {
                userChoice = "paper";
                gameMessageForTransmission("P");
            }

            if (message.equalsIgnoreCase("scissors")) {
                userChoice = "scissors";
                gameMessageForTransmission("S");
            }

            setButtonsInactive();
        }
    }

    private String opponentChoice = "";
    private String userChoice = "";

    private void decideWinner() {
        if (opponentChoice.equalsIgnoreCase(userChoice)) {
            if (userChoice.equalsIgnoreCase("paper")) {
                showResults("draw");
            }
            if (userChoice.equalsIgnoreCase("scissors")) {
                showResults("draw");
            }
            if (userChoice.equalsIgnoreCase("rock")) {
                showResults("draw");
            }

            return;
        }

        switch (opponentChoice) {
            case "rock":
                if (userChoice.equalsIgnoreCase("paper")) {
                    showResults("win");
                } else if (userChoice.equalsIgnoreCase("scissors")) {
                    showResults("lose");
                }
                break;

            case "paper":
                if (userChoice.equalsIgnoreCase("rock")) {
                    showResults("lose");
                } else if (userChoice.equalsIgnoreCase("scissors")) {
                    showResults("win");
                }
                break;

            case "scissors":
                if (userChoice.equalsIgnoreCase("rock")) {
                    showResults("win");
                } else if (userChoice.equalsIgnoreCase("paper")) {
                    showResults("lose");
                }
                break;
        }
    }

    private void showResults(String result) {
        switch (result) {
            case "draw":
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("draw");
                break;
            case "win":
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("win");
                break;
            case "lose":
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("lose");
                break;
        }
    }


    public void messageToController(String message) {
        if (message.charAt(0) == '%' && message.charAt(1) == '%') {
            message = message.substring(2); // cuts first ##

            messageToUser(message);

            return;
        }

        if (message.charAt(0) == '#' && message.charAt(1) == '#') {
            if (message.charAt(2) == 'R') {
                this.opponentChoice = "rock";
            }

            if (message.charAt(2) == 'P') {
                this.opponentChoice = "paper";
            }

            if (message.charAt(2) == 'S') {
                this.opponentChoice = "scissors";
            }
        }
    }

    public void messageFromChat(String message) {
        dataBackFromController.dataBackFromControllerFunction("%%" + message);
    }

    public void windowClosed() {
    }

    public void messageToUser(String message) {
        privateInterface.addMessageReceivedFromPeer(message + "\n");
    }


    private class checkThread extends Thread {
        @Override
        public void run() {

            newGame();
            while (true) {

                System.out.println("opponent: " + opponentChoice);
                System.out.println("user: " + userChoice);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!opponentChoice.isEmpty() && !userChoice.isEmpty()) {
                    privateInterface.getImageSetterForOpponent().stringImageSetterForOpponent(opponentChoice);

                    decideWinner();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    newGame();
                }
            }
        }
    }
}
