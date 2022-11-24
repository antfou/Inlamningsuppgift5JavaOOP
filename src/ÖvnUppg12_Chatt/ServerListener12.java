package ÖvnUppg12_Chatt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/


public class ServerListener12 {

    private static MultiWriter12 multiWriter = new MultiWriter12();

    public static void main(String[] args) {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(12345);){
                final Socket socketToClient = serverSocket.accept();
                Handler12 clientHandler = new Handler12(socketToClient, multiWriter);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}