package ControllerInterface;

import GraphicInterface.UserInteraction;

public class Controller {

    private UserInteraction privateInterface;

    public Controller() {

        this.privateInterface =  new UserInteraction();

        this.privateInterface.getImageSetterForUser().blankImageSetterForUser();
        this.privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("welcome");

        try {
        Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("choose");

    }


    // controller for messages

    public void messageToInterfaceChat(String message) {
        privateInterface.addMessageReceivedFromPeer(message);
    }



}
