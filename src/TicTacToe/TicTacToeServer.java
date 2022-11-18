package TicTacToe;
import java.net.ServerSocket;

public class TicTacToeServer {

    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Tic Tac Toe Server is Running");
        try {
            while (true) {
                ServerSideGame game = new ServerSideGame();

                ServerSidePlayer playerX
                        = new ServerSidePlayer(listener.accept(), 'X', game);
                ServerSidePlayer playerO
                        = new ServerSidePlayer(listener.accept(), 'O', game);

                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();

                //ALternativ approach
                //Game2 game2 = new Game2(socket1, socket2);

            }
        } finally {
            listener.close();
        }
    }
}