package ChattProgram.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {
    int port = 4444;

    ServerSocket serverSocket;

    MultiUsers users = new MultiUsers();
    MainServer(){
        try{ serverSocket = new ServerSocket(port);


            while (true){
                Socket socket = serverSocket.accept();
                ThreadServer threadServer = new ThreadServer(socket,users);
                threadServer.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new MainServer();
    }




    }


