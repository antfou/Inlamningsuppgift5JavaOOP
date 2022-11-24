package ChattProgram.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServer extends Thread {
    Socket socket;
    String input;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    MultiUsers multiUser;


    ThreadServer(Socket socket, MultiUsers multiUser) {
        this.socket = socket;
        this.multiUser = multiUser;
    }

    public void run() {
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            multiUser.addUser(printWriter);

            while (true) {
                input = bufferedReader.readLine();

            for (PrintWriter writer :multiUser.getUsers()){
                writer.println(input);
                if(input == null){
                    socket.close();
                }
            }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
