package MultipleChoiceQuestion.ClassesAndLogic;
//Feature Branch

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable {

    private String questionText;
    private String correctAnswer;
    private List<Answer> answerList = new ArrayList<>();
    private Answer answer1;
    private Answer answer2;
    private Answer answer3;
    private Answer answer4;

    public Question(String questionText, String answerText1, String answerText2,
                    String answerText3, String correctAnswer){
        this.questionText = questionText;
        answer1 = new Answer(answerText1);
        answer2 = new Answer(answerText2);
        answer3 = new Answer(answerText3);
        answer4 = new Answer(correctAnswer);

        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);

        Collections.shuffle(answerList);

        for (Answer a : answerList){
            if(a.getAnswerText().equals(correctAnswer)){
                a.setCorrect(true);
            }
        }
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
