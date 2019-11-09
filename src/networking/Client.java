package networking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

public class Client implements Serializable, ActionListener {

    JFrame fr = new JFrame("Login");
    Panel pn = new Panel();
    Label lbl_user = new Label("Username : ");
    Label lbl_pass = new Label("Password : ");

    TextField txt_user = new TextField(20);
    TextField txt_pass = new TextField(20);
    Button btn_submit = new Button("Login");

    public Client() {
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pn.add(lbl_user);
        pn.add(txt_user);
        pn.add(lbl_pass);
        pn.add(txt_pass);
        pn.add(btn_submit);

        fr.add(pn);
        btn_submit.addActionListener(this);

    }

    public static void sock(login data) {

        try {
            System.out.println("SOmething is happening");
            Socket client = new Socket("localhost", 8080);

            ObjectOutputStream toServer = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(client.getInputStream());

            toServer.writeObject(data);

            String notification = (String) fromServer.readObject();
            System.out.println("The data from the server ::> " + notification);

        } catch (Exception ex) {
            System.out.println("An error happened in the client \n" + ex);
        }
    }

    public static void main(String[] args) {
//        Scanner inp = new Scanner(System.in);

//        System.out.print("Please neter Username:");
//        String username = inp.next();
//        System.out.print("Please neter password:");
//        String password = inp.next();
//        login data = new login(username, password);
//        sock(data);
        new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_submit) {
            String name = txt_user.getText();
            String pass = txt_pass.getText();

            login data = new login(name, pass);
            sock(data);
        }

    }
}

