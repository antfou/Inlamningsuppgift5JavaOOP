package MultipleChoiceQuestion.Server;

import MultipleChoiceQuestion.ClassesAndLogic.Answer;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class PlayerServer extends Thread{
    String name;
    GameServer game;
    PlayerServer opponent;
    Socket socket;
    ObjectInputStream inputHandler;
    ObjectOutputStream outputHandler;
    public PlayerServer(Socket socket, GameServer game) throws IOException {
        System.out.println("PlayerServer är igång");
        this.game = game;
        this.socket = socket;
        name = JOptionPane.showInputDialog("Vem vill spela?");
        outputHandler = new ObjectOutputStream(socket.getOutputStream());
        inputHandler = new ObjectInputStream(socket.getInputStream());
            outputHandler.writeObject("WELCOME " + name);
        }

    public void setOpponent (PlayerServer opponent){this.opponent = opponent;}

    public PlayerServer getOpponent(){return opponent;}

    public void otherPlayerAction(String buttonClicked) throws IOException{
        outputHandler.writeObject("OPPONENT_ACTION " + buttonClicked);
        if(game.hasWinner()){
            outputHandler.writeObject("FÖRLUST");
        }else if(game.isTie()){
            outputHandler.writeObject("OAVGJORT");
        }
    }

    public void run(){
        try{
            System.out.println("före skickar start");
            outputHandler.writeObject("START");
            System.out.println("har skickar start");
            while(true){
                Object userCommand = inputHandler.readObject();
                System.out.println("userCommand "+userCommand);
                //boolean isCorrect = false;
                if(userCommand instanceof Answer clickedAnswer){
                    if (clickedAnswer.checkIfCorrect()){
                        outputHandler.writeObject("KORREKT");
                    }else {
                        outputHandler.writeObject("INKORREKT");
                    }
                    if(game.hasWinner()){
                        outputHandler.writeObject("VINST");
                    }else if(game.isTie()){
                        outputHandler.writeObject("OAVGJORT");
                    }
                }else if(userCommand.equals("AVSLUT")){
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
