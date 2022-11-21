package MultipleChoiceQuestion.Server;

public class GameServer {
    int correctAnswer = 2;

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
