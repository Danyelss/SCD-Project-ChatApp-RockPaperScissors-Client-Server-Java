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

    public void messageFromUser(String message) {
        dataBackFromController.dataBackFromControllerFunction(message);
    }

    public void windowClosed(){

    }

    public void messageToUser(String message){
        privateInterface.addMessageReceivedFromPeer(message);
    }

    // controller for messages

    public void messageToInterfaceChat(String message) {
        privateInterface.addMessageReceivedFromPeer(message);
    }



}
