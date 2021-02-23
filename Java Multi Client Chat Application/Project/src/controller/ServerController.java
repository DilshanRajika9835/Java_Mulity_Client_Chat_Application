package controller;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerController {
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket=new ServerSocket(8889);
            while (true){
                System.out.println("Server Started....");
                System.out.println("Waiting for a Client....");
               socket=serverSocket.accept();
                System.out.println("( "+socket+" ): Server Connect to a Client...");
                ClientHandler clientHandler=new ClientHandler(socket,clients);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null,e.getMessage());
        }
    }
}
