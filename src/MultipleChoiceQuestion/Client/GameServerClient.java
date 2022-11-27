package MultipleChoiceQuestion.Client;

import MultipleChoiceQuestion.ClassesAndLogic.Database;
import MultipleChoiceQuestion.Server.GameServer;
import MultipleChoiceQuestion.Server.PlayerServer;

import java.io.IOException;
import java.net.ServerSocket;
//Feature Branch

public class GameServerClient {
    Database db = new Database();
    public GameServerClient() throws IOException {
        ServerSocket listener = new ServerSocket(9123);
        System.out.println("Servern är igång");
        try{
            while (true){
                GameServer game = new GameServer();

                PlayerServer player1 = new PlayerServer(listener.accept(),game,db,1,0);
                PlayerServer player2 = new PlayerServer(listener.accept(),game,db,2,0);

                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.setCurrentPlayer(player1);

                player1.start();
                player2.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            listener.close();
        }
    }
    public static void main(String[] args) throws IOException {
        new GameServerClient();
    }
}
