package Data;

import Client.Client;
import Server.Server;

public class DataBackFromController {

    private Client client;
    private Server server;

    private void serverDataBackFromControllerFunction(String message) {
        this.server.getSender().sendMessage(message);
    }

    private void clientDataBackFromControllerFunction(String message) {
        this.client.getSender().sendMessage(message);
    }

    public void dataBackFromControllerFunction(String message) {
        if(this.client != null) {
            clientDataBackFromControllerFunction(message);
        }

        if(this.server != null) {
            serverDataBackFromControllerFunction(message);
        }
    }

    public DataBackFromController(Client client) {
        this.client = client;
    }

    public DataBackFromController(Server server) {
        this.server = server;
    }

}
