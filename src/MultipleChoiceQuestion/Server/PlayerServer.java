package MultipleChoiceQuestion.Server;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class PlayerServer extends Thread{
    //Might not need mark.
    String name;
    GameServer game;
    PlayerServer opponent;
    Socket socket;
    BufferedReader inputHandler;
    PrintWriter outputHandler;

    public PlayerServer(Socket socket, GameServer game){
        this.game = game;
        this.socket = socket;
        name = JOptionPane.showInputDialog("Vem vill spela?");
        try{inputHandler = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            outputHandler = new PrintWriter(socket.getOutputStream(), true);
            outputHandler.println("WELCOME " + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setOpponent (PlayerServer opponent){this.opponent = opponent;}

    public PlayerServer getOpponent(){return opponent;}

    public void otherPlayerAction(String buttonClicked){
        outputHandler.println("OPPONENT_ACTION " + buttonClicked);

        if(game.hasWinner()){
            outputHandler.println("FÖRLUST");
        }else if(game.isTie()){
            outputHandler.println("OAVGJORT");
        }
    }

    public void run(){
        try{
            outputHandler.println("START");
            while(true){
                String userCommand = inputHandler.readLine();
                boolean isCorrect = false;
                if(userCommand.startsWith("BUTTON")){
                    if (userCommand.contains("1")){
                        isCorrect = game.isCorrect(1);
                    }else if(userCommand.contains("2")){
                        isCorrect = game.isCorrect(2);
                    }else if(userCommand.contains("3")){
                        isCorrect = game.isCorrect(3);
                    }else if(userCommand.contains("4")){
                        isCorrect = game.isCorrect(4);
                    }
                    if(isCorrect){
                        outputHandler.println("KORREKT");
                    }else{
                        outputHandler.println("INKORREKT");
                    }
                    if(game.hasWinner()){
                        outputHandler.println("VINST");
                    }else if(game.isTie()){
                        outputHandler.println("OAVGJORT");
                    }
                }else if(userCommand.equals("AVSLUT")){
                    return;
                    //System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
