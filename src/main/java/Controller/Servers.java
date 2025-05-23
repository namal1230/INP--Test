package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servers {
    public static Servers server;
    private ArrayList<Controller.ClientHandler> clients=new ArrayList<>();

    private Servers(){}

    // This getInsatnce method not duplicates same Objects
    public static Servers getInstance(){
        if (server == null){
            server = new Servers();
        }
        return server;
    }

    // This is a method create Socket
    public void makeSocket() throws IOException {
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(5006);
                Socket socket = serverSocket.accept();
                System.out.println("Server Started");
                Controller.ClientHandler clientHandler = new Controller.ClientHandler(this, socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();


    }

    // check message is given by two clients or same client
    public void broadCastMessage(String message, Controller.ClientHandler clientHandler){
        for (Controller.ClientHandler client:clients){
            if (client != clientHandler){
                client.sendMessage(message);
            }
        }
    }
}
