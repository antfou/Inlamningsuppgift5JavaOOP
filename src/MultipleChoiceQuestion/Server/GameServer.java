package MultipleChoiceQuestion.Server;
//Feature Branch

import MultipleChoiceQuestion.Client.GameServerClient;

public class GameServer {
        PlayerServer currentPlayer;

    public PlayerServer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerServer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    //TODO Counter för antal rounds spelade.
    //TODO Couner för antal poäng från varje spelare.
    //TODO använda de counters till att räkna ut vem som vunnit och slänga in de nedanstående metoderna.
    public boolean hasWinner(){
        return false;
    }
    public boolean isTie(){
        return false;
    }



}
