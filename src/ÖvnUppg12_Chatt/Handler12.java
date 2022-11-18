package ÖvnUppg12_Chatt;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Handler12 extends Thread{

    private Socket socket;
    private MultiWriter12 multiWriter12;

    public Handler12(Socket socket, MultiWriter12 multiWriter12){
        this.socket = socket;
        this.multiWriter12 = multiWriter12;
    }

    public void run(){

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  //true for autoflush
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){

            //Vi lägger in vår printWriter i multiWriters lista
            multiWriter12.addWriter(out);

            while(true){
                String input = in.readLine();
                if (input == null) {
                    // multiWriter.removeWriter(out);
                    //socket.close();
                    return;
                }
                for (PrintWriter writer : multiWriter12.getWriters()) {
                    writer.println(input);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



}