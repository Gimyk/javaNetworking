package networking;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Serializable{

    public static void sock(login data) {

        try {
            System.out.println("SOmething is happening");
            Socket client = new Socket("localhost", 8080);

            ObjectOutputStream toServer = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(client.getInputStream());
            
            toServer.writeObject(data);
            
            String notification = (String) fromServer.readObject();
            System.out.println("The data from the server :: " + notification );
            

        } catch (Exception ex) {
            System.out.println("An error happened in the client \n" + ex);
        }
    }

    
    
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        System.out.print("Please neter Username:");
        String username = inp.next();
        System.out.print("Please neter password:");
        String password = inp.next();
        
        login data = new login(username, password);
        
        sock(data);
    }
}
