package Client;

import ControllerInterface.Controller;
import Data.DataBackFromController;

import java.nio.channels.SocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Client {

    private Socket clientSocket; // socket used by client to send and recieve data from server
    private BufferedReader in;   // object to read data from socket
    private PrintWriter out;     // object to write data into socket
    private Scanner sc = new Scanner(System.in); // object to read data from user's keybord

    private SocketChannel client;
    private Controller controller;


    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    private Sender sender;

    private Receiver receiver;


    private void setDataBackFromController(DataBackFromController dataBackFromController) {
        this.dataBackFromController = dataBackFromController;
    }

    public DataBackFromController getDataBackFromController() {
        return dataBackFromController;
    }

    private DataBackFromController dataBackFromController;


    public boolean isExist() {
        return exist;
    }

    private boolean exist = false;


    public void clientClose() {
        try {
            System.out.println("Server out of service");

            this.out.close();

            this.clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Client() {}

    public Client(String real) {

        this.exist = true;

        setDataBackFromController(new DataBackFromController(this));

        this.controller = new Controller(getDataBackFromController());

        try {
            this.clientSocket = new Socket("127.0.0.1", 8089);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            this.sender = new Sender();

            sender.start();

            this.receiver = new Receiver();

            receiver.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("real");
    }

    public class Sender extends Thread {
        private CyclicBarrier cyclicBarrier;
        private String message;

        public void sendMessage(String message) {
            this.message = message;

            try {
                this.cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            while (true) {

                try {
                    this.cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                out.println(message);
                out.flush();
            }
        }

        public Sender() {
            this.cyclicBarrier = new CyclicBarrier(2);
        }
    }

    public class Receiver extends Thread {
        private String message;

        public void receiveMessage(String message) {
            controller.messageToController(message);
        }

        @Override
        public void run() {
            try {

                message = in.readLine();

                while (message != null) {

                    receiveMessage(message);

                    message = in.readLine();
                }

                clientClose();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Receiver() {
        }
    }

}