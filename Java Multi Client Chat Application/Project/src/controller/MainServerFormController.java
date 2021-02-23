package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServerFormController {
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    public TextArea messagebox;
    public Label lblstatus;
    public Label lblconnection;
    ServerSocket serverSocket;
    Socket socket;
    public void initialize(){
        lblstatus.setVisible(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket=new ServerSocket(8889);
                    while (true){
                        lblstatus.setVisible(true);
                        lblconnection.setText("Waiting for a Client....");
                        socket=serverSocket.accept();
                        messagebox.appendText("( "+socket+" ): Server Connect to a Client...\n");
                        ClientHandler clientHandler=new ClientHandler(socket,clients);
                        clients.add(clientHandler);
                        clientHandler.start();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();


    }
}
