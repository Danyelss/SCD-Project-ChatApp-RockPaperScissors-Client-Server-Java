package ControllerInterface;

import GraphicInterface.UserInteraction;

public class Controller {

    private UserInteraction privateInterface;
    private DataTransmissionObject dataTransmissionObject;


    public Controller() throws InterruptedException {

        this.dataTransmissionObject = new DataTransmissionObject();

        this.privateInterface =  new UserInteraction(this.dataTransmissionObject);

        this.privateInterface.getImageSetterForUser().blankImageSetterForUser();
        this.privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("welcome");

        Thread.sleep(5000);

        privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("choose");


    }

    public static void main(String[] args) throws InterruptedException {
        Controller controller = new Controller();
        //Controller controller2 = new Controller();

    }

    public class DataTransmissionObject {
        public boolean optionAccepted(String option) {
            System.out.println(option);

            return true;
        }

        public void sendToPeer(String message) {
            System.out.println(message);
        }

        public void receiveFromPeer(String message) {
            System.out.println(message);
        }
    }
}
