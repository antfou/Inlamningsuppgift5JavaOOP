package MultipleChoiceQuestion.Client;

import MultipleChoiceQuestion.Server.GameServer;
import MultipleChoiceQuestion.Server.PlayerServer;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServerClient {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9123);
        System.out.println("Servern är igång");
        try{
            while (true){
                GameServer game = new GameServer();

                PlayerServer player1 = new PlayerServer(listener.accept(),game);
                PlayerServer player2 = new PlayerServer(listener.accept(),game);

                player1.setOpponent(player2);
                player2.setOpponent(player1);
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
}
