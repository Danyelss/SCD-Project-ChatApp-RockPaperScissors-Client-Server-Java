package ControllerInterface;

import Client.Client;
import GraphicInterface.UserInteraction;

public class Controller {

    private UserInteraction privateInterface;

    public DataTransmissionObject getDataTransmissionObject() {
        return dataTransmissionObject;
    }

    private DataTransmissionObject dataTransmissionObject;

    private Client.DataExchange dataExchange;


    public Controller(Client.DataExchange dataExchange) {

        this.dataTransmissionObject = new DataTransmissionObject();

        this.dataExchange = dataExchange;

        this.privateInterface =  new UserInteraction(this.dataTransmissionObject);

        this.privateInterface.getImageSetterForUser().blankImageSetterForUser();
        this.privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("welcome");

        try {
        Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        privateInterface.getSetInstructionalLabel().messageSetInstructionalLabel("choose");

    }

    public class DataTransmissionObject {
        public boolean optionAccepted(String option) {
            dataExchange.receiveMessageThroughExchange(option);

            return true;
        }

        public void sendToPeer(String message) {
            dataExchange.receiveMessageThroughExchange(message);
        }

        public void receiveFromPeer(String message) {
            System.out.println(message);
        }

        public void windowClosed(){
            dataExchange.closeClient();
        }
    }
}
