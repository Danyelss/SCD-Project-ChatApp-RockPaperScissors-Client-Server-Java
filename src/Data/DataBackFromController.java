package Data;

import Client.Client;
import Server.Server;

public class DataBackFromController {

    private Client client = new Client();
    private Server server = new Server();

    private void serverDataBackFromControllerFunction(String message) {
        this.server.getSender().sendMessage(message);
    }

    private void clientDataBackFromControllerFunction(String message) {
        this.client.getSender().sendMessage(message);
    }

    public void dataBackFromControllerFunction(String message) {
        if(this.client.isExist()) {
            clientDataBackFromControllerFunction(message);
        }

        if(this.server.isExist()) {
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
