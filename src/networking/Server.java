package networking;

import java.io.*;
import java.net.*;

public class Server implements Serializable{

    public Server() {

        try {
            System.out.println("Server is running on port . . . .8080 ");
            ServerSocket sock = new ServerSocket(8080);

            while (true) {
                Socket conn = sock.accept();

                ObjectOutputStream toClient = new ObjectOutputStream(conn.getOutputStream());
                ObjectInputStream fromClient = new ObjectInputStream(conn.getInputStream());

                login data = (login) fromClient.readObject();
                System.out.println("Username => " + data.getUsername());
                System.out.println("Password => " + data.getPassword());

                toClient.writeObject("Data received");

            }
        } catch (Exception ex) {
            System.out.println("An error happened in the serve \n" + ex);
        }
    }

    public static void main(String[] args) {
        new Server();
    }

}
