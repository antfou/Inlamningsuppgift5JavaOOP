package MultipleChoiceQuestion.ClassesAndLogic;
//Feature Branch
import java.io.Serializable;

public class Answer implements Serializable {

    private String answerText;
    private boolean isCorrect = false;

    public Answer(String answerText){
        this.answerText = answerText;
    }

    public String getAnswerText(){
        return answerText;
    }
    public boolean checkIfCorrect(){
        return isCorrect;
    }
    public void setCorrect(Boolean correct){
        isCorrect = correct;
    }
}
