package ControllerInterface;

import Data.DataBackFromController;
import GraphicInterface.UserInteraction;

public class Controller {

    private UserInteraction privateInterface;

    private DataBackFromController dataBackFromController;

    public Controller(DataBackFromController dataBackFromController) {

        this.dataBackFromController = dataBackFromController;

        this.privateInterface =  new UserInteraction(this);

        this.privateInterface.getImageSetterForUser().blankImageSetterForUser();
        this.privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("welcome");

        try {
        Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("choose");

    }


    public boolean isButtonsActive() {
        return buttonsActive;
    }

    private void setButtonsInactive(){
        buttonsActive = false;
    }

    private boolean buttonsActive = false;


    private void gameMessageForTransmission(String message) {
        dataBackFromController.dataBackFromControllerFunction("##" + message);
    }

    public void gameMessageController(String message) {
        if(isButtonsActive()) {
            if(message.equalsIgnoreCase("rock")){
                gameMessageForTransmission("R");
                setButtonsInactive();
                return;
            }

            if(message.equalsIgnoreCase("paper")){
                gameMessageForTransmission("P");
                setButtonsInactive();
                return;
            }

            if(message.equalsIgnoreCase("scissors")){
                gameMessageForTransmission("S");
                setButtonsInactive();
                return;
            }
        }
    }

    private String opponentChoice = "";

    public void messageToController(String message) {
        if(message.charAt(0) == '%' && message.charAt(1) == '%') {
            messageToUser(message);

            return;
        }

        if(message.charAt(0) == '#' && message.charAt(1) == '#') {
            if(message.charAt(2) == 'R') {
                this.opponentChoice = "rock";
                return;
            }

            if(message.charAt(2) == 'P') {
                this.opponentChoice = "paper";
                return;
            }

            if(message.charAt(2) == 'S') {
                this.opponentChoice = "scissors";
                return;
            }
        }
    }

    public void messageFromChat(String message) {
        dataBackFromController.dataBackFromControllerFunction("%%" + message);
    }

    public void windowClosed(){
    }

    public void messageToUser(String message){
        privateInterface.addMessageReceivedFromPeer(message+"\n");
    }
}
