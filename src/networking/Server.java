package networking;

import java.io.*;
import java.net.*;
import java.sql.*;

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

                String  rt =  db(data);
                
                toClient.writeObject(rt);
                
            }
        } catch (Exception ex) {
            System.out.println("An error happened in the serve \n" + ex);
        }
    }

    public static void main(String[] args) {
        new Server();
    }
    
    public static String db(login data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
            Statement stm = conn.createStatement();
            
            String query = "select * from users where name = '"
                    +data.getUsername()+
                    "' AND password = '"+data.getPassword()+"'";
            
//            "select * from login where name = 'Jane' AND password = 'pass123' "
            
            ResultSet rs = stm.executeQuery(query);
            
            System.out.println("Query =- " + query);
            
            if(rs.next()){
                System.out.println("Has data");
                return "True";
            }else{
                System.out.println("Has no data");
                return "False1";
            }
            
        }catch(Exception ex){
            System.out.println("Error in server db " + ex);
        }
        return "False2";
    }

}


//create database login;
//use login;
//create table users(
//    id int(11) AUTO_INCREMENT PRIMARY KEY,
//    name varchar(100) not null,
//    password varchar(100) not null
//);

//insert into users (name, password) values
//('Jane', 'pass123');