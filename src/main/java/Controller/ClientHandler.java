package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


// This is a class to manage clients
public class ClientHandler implements Runnable {

    Servers server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public ClientHandler(Servers server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg;
        try{
            while ((msg=in.readUTF())!= null){
                server.broadCastMessage(msg,this);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        try{
            out.writeUTF(message);
            out.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
