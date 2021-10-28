package Server;

import ControllerInterface.Controller;

import java.nio.channels.SocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Server {

    private Socket clientSocket; // socket used by client to send and recieve data from server
    private BufferedReader in;   // object to read data from socket
    private PrintWriter out;     // object to write data into socket
    private Scanner sc = new Scanner(System.in); // object to read data from user's keybord

    private SocketChannel client;
    private Controller controller;

    public void setDataTransmissionObject(DataTransmissionObject dataTransmissionObject) {
        this.dataTransmissionObject = dataTransmissionObject;
    }

    public DataTransmissionObject getDataTransmissionObject() {
        return dataTransmissionObject;
    }

    private DataTransmissionObject dataTransmissionObject;

    private Sender sender;

    private Receiver receiver;

    public void clientClose() {
        try {
            System.out.println("Server out of service");

            this.out.close();

            this.clientSocket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() {

        setDataTransmissionObject(new DataTransmissionObject());

        Controller controller = new Controller(getDataTransmissionObject());

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
        Server server = new Server();
    }

    public class Sender extends Thread {
        private CyclicBarrier cyclicBarrier;
        private String message;

        public void sendMessage(String message) {
            this.message = message;

            System.out.println(message);

            try {
                this.cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("o trecut de send messaage cb");
        }

        @Override
        public void run() {
            while (true) {

                System.out.println("inainte de run cb");

                try {
                    this.cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("dupa run cb");

                out.print(message);
                out.flush();

                System.out.println("dupa out print");
            }
        }

        public Sender() {
            this.cyclicBarrier = new CyclicBarrier(2);
        }
    }

    public class Receiver extends Thread {
        private String message;

        public void receiveMessage(String message) {
            dataExchange.receiveMessageThroughExchange(message);
        }

        @Override
        public void run() {
            try {

                message = in.readLine();

                while (message != null) {
                    System.out.println("Server : " + message + "mue");

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

