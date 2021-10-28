package Data;

import ControllerInterface.Controller;
import Client.Client;

public class DataExchange {
    private Client.Client.Sender sender;
    private Client.Receiver receiver;
    private Controller controller;
    private Client client;

    public void receiveMessageThroughExchange(String message) {
        sender.sendMessage(message);
    }

    public void sendMessageThroughExchange(String message) {
        controller.getDataTransmissionObject().receiveFromPeer(message);
    }

    public void closeClient() {
        client.clientClose();
    }

    public DataExchange(Client.Sender sender, Client.Receiver receiver, Controller controller, Client client) {
        this.sender = sender;
        this.receiver = receiver;
        this.controller = controller;
        this.client = client;
    }
}
