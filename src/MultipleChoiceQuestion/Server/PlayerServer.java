package MultipleChoiceQuestion.Server;
//Feature Branch

import MultipleChoiceQuestion.ClassesAndLogic.Answer;
import MultipleChoiceQuestion.ClassesAndLogic.Database;
import MultipleChoiceQuestion.ClassesAndLogic.Player;
import MultipleChoiceQuestion.Client.GameServerClient;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class PlayerServer extends Thread{

    Database db;
    String name;
    GameServer game;
    PlayerServer opponent;
    Socket socket;
    ObjectInputStream inputHandler;
    ObjectOutputStream outputHandler;
    int player;
    int score;

    public PlayerServer(Socket socket, GameServer game,Database db, int player, int score) throws IOException {
        this.db = db;
        System.out.println("PlayerServer är igång");
        this.game = game;
        this.socket = socket;
        this.player = player;
        this.score=score;
        name = JOptionPane.showInputDialog("Vem vill spela?");
        this.db.addPlayerToList(new Player(name));
        for (Player p : db.getListOfPlayers()) {
            System.out.println(p.getUserName() + " tillagd i listan 'online spelare'");
        }
        outputHandler = new ObjectOutputStream(socket.getOutputStream());
        inputHandler = new ObjectInputStream(socket.getInputStream());
            outputHandler.writeObject("WELCOME " + name);
        }


    public int scoreCounter(int player,String answer){
        if(player==1 && answer.equals("K")){
            return score=score+1;
        } else if (player==2 && answer.equals("K")) {
            return score=score+1;
        }else if(player == 1){
            return score;
        } else if (player==2) {
            return score;
        }
        return score;
    }
    public void printScore(String answer){
        System.out.println("Poäng för spelare " + name + " = " + scoreCounter(player,answer));
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
            Object userCommand;
            while(true){
                 userCommand = inputHandler.readObject();
                System.out.println("userCommand "+userCommand);
                //boolean isCorrect = false;
                if(userCommand instanceof Answer clickedAnswer){
                    if (clickedAnswer.checkIfCorrect()){
                        outputHandler.writeObject("KORREKT");
                        printScore("K");
                    }else if (!( clickedAnswer).checkIfCorrect()){
                        outputHandler.writeObject("INKORREKT");
                        printScore("");
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
