package Controller;

import java.io.IOException;
import java.time.LocalDateTime;



public class ServerController {

    private String client;

    public static void main(String[] args) {

        // looging in the server command
        System.out.println("Server Started..");

        // Start Server connection using Threads
       new Thread(()->{
          try {
             Servers instance = Servers.getInstance();
             instance.makeSocket();
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
        }).start();

        // Show current date and time
        System.out.println(LocalDateTime.now());

        // Implement By HELP command
        System.out.println("Please use command line to give your message and you can close your connection using cmd or your running platform is closed");
    }

    public void setName(String client){
        this.client=client;
        System.out.println(client+" is close the connection gracefully.");
    }
}

//    public ServerController(){
//        new Thread(()->{
//            try {
//                Controller.Servers instance = Controller.Servers.getInstance();
//                instance.makeSocket();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }).start();
//    }
//}
