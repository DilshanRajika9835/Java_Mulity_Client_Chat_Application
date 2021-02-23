package controller;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private  ArrayList<ClientHandler>clients;
    private  Socket socket;
    private  BufferedReader bufferedReader;
    private   PrintWriter printWriter;

    public ClientHandler(@NotNull Socket socket, ArrayList<ClientHandler> clients) {
        try {
            this.socket=socket;
            this.clients=clients;
            this.bufferedReader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter=new PrintWriter(this.socket.getOutputStream(),true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
        String message;
        while ((message = bufferedReader.readLine() )!= null) {
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
            for (ClientHandler clh : clients) {
                clh.printWriter.println(message);
                System.out.println("Client "+message);
            }
        }
    }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            try {
                socket.close();
                bufferedReader.close();
                printWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
