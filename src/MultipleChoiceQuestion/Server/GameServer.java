package MultipleChoiceQuestion.Server;

public class GameServer {
    int correctAnswer = 2;
    //TODO Counter för antal rounds spelade.
    //TODO Couner för antal poäng från varje spelare.
    //TODO använda de counters till att räkna ut vem som vunnit och slänga in de nedanstående metoderna.

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public boolean hasWinner(){
        return false;
    }
    public boolean isTie(){
        return false;
    }
    public boolean isCorrect(int i){
        return  (i == correctAnswer);
    }
}
