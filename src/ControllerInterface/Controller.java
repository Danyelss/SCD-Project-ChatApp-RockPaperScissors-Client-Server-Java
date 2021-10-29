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

        newGame();
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

    private boolean buttonsActive = false;


    private void gameMessageForTransmission(String message) {
        dataBackFromController.dataBackFromControllerFunction("##" + message);
    }


    private void waitOrResult() {
        if (opponentChoice.equalsIgnoreCase("")) {
            privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("wait");
        } else {
            decideWinner();
        }
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

            System.out.println(message + " - buttons inactive");

            waitOrResult();
        }
    }

    private String opponentChoice = "";
    private String userChoice = "";

    private void decideWinner() {
        if (opponentChoice.equalsIgnoreCase(userChoice)) {
            if (userChoice.equalsIgnoreCase("paper")) {
                showResults("draw", "P");
            }
            if (userChoice.equalsIgnoreCase("scissors")) {
                showResults("draw", "S");
            }
            if (userChoice.equalsIgnoreCase("rock")) {
                showResults("draw", "R");
            }

            return;
        }

        switch (opponentChoice) {
            case "rock":
                if (userChoice.equalsIgnoreCase("paper")) {
                    showResults("win", "P");
                } else if (userChoice.equalsIgnoreCase("scissors")) {
                    showResults("lose", "S");
                }
                break;

            case "paper":
                if (userChoice.equalsIgnoreCase("rock")) {
                    showResults("win", "R");
                } else if (userChoice.equalsIgnoreCase("scissors")) {
                    showResults("lose", "S");
                }
                break;

            case "scissors":
                if (userChoice.equalsIgnoreCase("rock")) {
                    showResults("win", "R");
                } else if (userChoice.equalsIgnoreCase("paper")) {
                    showResults("lose", "P");

                }
                break;
        }
    }

    private void showResults(String result, String user) {
        switch (result) {
            case "draw":
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("draw");
                dataBackFromController.dataBackFromControllerFunction("&&" + "D" + user);
                break;
            case "win":
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("win");
                dataBackFromController.dataBackFromControllerFunction("&&" + "L" + user);
                break;
            case "lose":
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("lose");
                dataBackFromController.dataBackFromControllerFunction("&&" + "W" + user);
                break;
        }

        afterResult();
    }

    private void afterResult() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        newGame();
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

            return;
        }

        if (message.charAt(0) == '&' && message.charAt(1) == '&') {
            if (message.charAt(2) == 'D') {
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("draw");
            }

            if (message.charAt(2) == 'W') {
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("win");
            }

            if (message.charAt(2) == 'L') {
                privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("win");
            }

            switch (message.charAt(3)) {
                case 'R':
                    privateInterface.getImageSetterForOpponent().stringImageSetterForOpponent("rock");
                    break;
                case 'P':
                    privateInterface.getImageSetterForOpponent().stringImageSetterForOpponent("paper");
                    break;
                case 'S':
                    privateInterface.getImageSetterForOpponent().stringImageSetterForOpponent("scissors");
                    break;
            }

            afterResult();
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
}
