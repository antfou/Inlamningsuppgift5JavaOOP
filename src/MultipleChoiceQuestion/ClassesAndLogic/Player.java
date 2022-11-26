package MultipleChoiceQuestion.ClassesAndLogic;
//Feature Branch

public class Player {
    protected String userName;
    protected int score;

    public Player(String userName, int score) {
        this.userName = userName;
        this.score=score;
    }

    public String getUserName() {
        return userName;
    }
    public int getScore(){return score;}


}