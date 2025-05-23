package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientController {

    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        // Start Client Side to make connection with another clients
        new Thread(()->{
            try {
                socket = new Socket("localhost", 5006);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                while (socket.isConnected()) {
                    String message = in.readUTF();
                    System.out.println(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        sendMessage();
    }

    // print messages for given by clients
    private static void reciveMesssage(String message) {
        System.out.println(message);
    }

    // send messages by given clients
    private static void sendMessage() {
        try {
            while(true){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input Your Message: ");
                String s = scanner.nextLine();

                if (s != null) {
                    out.writeUTF(s);
                    out.flush();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
