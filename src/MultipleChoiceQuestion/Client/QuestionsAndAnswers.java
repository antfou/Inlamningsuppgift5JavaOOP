package MultipleChoiceQuestion.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionsAndAnswers {

    //TODO skapa GUI för första sidan där användaren får välja kategori

    ArrayList<JButton> listOfCategories() {
        ArrayList<JButton> list = new ArrayList<>();
        list.add(new JButton("Sport"));
        list.add(new JButton("Historia"));
        list.add(new JButton("Matematik"));
        list.add(new JButton("Människokroppen"));
        list.add(new JButton("Java"));
        Collections.shuffle(list);
        return list;
    }
    public String sportQuestions(){
        ArrayList<String>sport = new ArrayList<>();
        sport.add("Vilket lag har färgerna svart och gul i sin logga?");
        sport.add("Vem är snabbast i världen?");
        shuffleList(sport);
        return sport.get(0);
    }
    public String historyQuestions(){
        ArrayList<String>history = new ArrayList<>();
        history.add("När startade första världskriget?");
        history.add("Hur dog Hitler?");
        shuffleList(history);
        return history.get(0);
    }
    public String mathQuestions(){
        ArrayList<String>math = new ArrayList<>();
        math.add("Vad är 3*3");
        math.add("Vilket tecken anger modulus");
        shuffleList(math);
        return math.get(0);
    }
    public String humanBodyQuestions(){
        ArrayList<String>human=new ArrayList<>();
        human.add("Hur många ben har en människa?");
        human.add("Vad är det latinska ordet för kropp?");
        shuffleList(human);
        return human.get(0);
    }
    public String javaQuestions(){
        ArrayList<String>java=new ArrayList<>();
        java.add("Hur ser logotypen för java-språket ut?");
        java.add("Hur adderar du kommentarer till din java-kod?");
        shuffleList(java);
        return java.get(0);

    }
    public void shuffleList(ArrayList list){
        Collections.shuffle(list);
    }




}
