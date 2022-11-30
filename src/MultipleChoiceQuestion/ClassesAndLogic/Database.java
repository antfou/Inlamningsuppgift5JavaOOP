package MultipleChoiceQuestion.ClassesAndLogic;
//Feature Branch

import MultipleChoiceQuestion.Server.PlayerServer;

import java.io.File;
import java.util.ArrayList;

public class Database {
    private final ArrayList<Player> playerNameList = new ArrayList<>();

    public void addPlayerToList(Player player) {
        playerNameList.add(new Player(player.getUserName()));
        //Ska vara utkommenterat tills vidare.
        /*File file = new File("src/MultipleChoiceQuestion/Server/Database/PlayerList.txt");    //Om vi vill ha en login
        try {
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write("\n" + player.getUserName());
            myWriter.close();
            System.out.println("Sparade användaren: " + player.getUserName() + "till databasen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    public ArrayList<Player> getListOfPlayers(){
        return playerNameList;
    }

    public Player searchPlayer(String searchName){
        Player playerFound = null;                 //om koden används, lägg in "if null blablabla"
        for (Player players : playerNameList){
            if (players.getUserName().replaceAll("\s", "" ).equalsIgnoreCase(searchName)){
                System.out.println("Hittade Spelare: " + players);
                return players;
            }
        }
        return playerFound;
    }
}