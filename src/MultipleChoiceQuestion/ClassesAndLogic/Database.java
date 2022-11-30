package MultipleChoiceQuestion.ClassesAndLogic;
//Feature Branch

import MultipleChoiceQuestion.Server.PlayerServer;

import java.io.File;
import java.util.ArrayList;

public class Database {
    private final ArrayList<String> playerNameList = new ArrayList<>();

    public void addPlayerToList(String player) {
        playerNameList.add(player);
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
    public ArrayList<String> getListOfPlayers(){
        return playerNameList;
    }

    public String searchPlayer(String searchName){
        String playerFound = "";                 //om koden används, lägg in "if null blablabla"
        for (String players : playerNameList){
            if (players.replaceAll("\s", "" ).equalsIgnoreCase(searchName)){
                System.out.println("Hittade Spelare: " + players);
                return players;
            }
        }
        return playerFound;
    }
}