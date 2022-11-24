package MultipleChoiceQuestion.Client;
//Feature Branch

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionsAndAnswers {

    //TODO skapa GUI för första sidan där användaren får välja kategori

    ArrayList<JButton> listOfCategories() {
        ArrayList<JButton> list = new ArrayList<>();
        list.add(new JButton("Sport"));
        list.add(new JButton("Historia"));
        list.add(new JButton("Film"));
        list.add(new JButton("Människokroppen"));
        list.add(new JButton("Java"));
        Collections.shuffle(list);
        return list;
    }
    public String sportQuestions(){
        ArrayList<String>sport = new ArrayList<>();
        sport.add("Vilket lag har färgerna svart och gul i sin logga?");
        sport.add("Vem fick guldbollen 2021?");
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
    public String movieQuestions(){
        ArrayList<String>math = new ArrayList<>();
        math.add("Vilken film vann Leonardo Dicaprio en Oscar för?");
        math.add("Från vilken film kommer citatet  \" Say hello to my little friend \"  ");
        shuffleList(math);
        return math.get(0);
    }
    public String humanBodyQuestions(){
        ArrayList<String>human=new ArrayList<>();
        human.add("Vilket är kroppens längsta ben?");
        human.add("Vilken funktion fyller amygdala");
        shuffleList(human);
        return human.get(0);
    }
    public String javaQuestions(){
        ArrayList<String>java=new ArrayList<>();
        java.add("Vilket tecken anger modulus");
        java.add("Hur adderar du kommentarer till din java-kod?");
        shuffleList(java);
        return java.get(0);

    }
    public ArrayList<String> sportAnswers1(){
        ArrayList<String> answer = new ArrayList<>();
        answer.add("DIF");
        answer.add("AIK");
        answer.add("MFF");
        answer.add("HIF");
        return answer;
    }
    public ArrayList<String>sportAnswers2(){
        ArrayList<String> answer = new ArrayList<>();
        answer.add("Emil Forsberg");
        answer.add("Dejan Kulusevski");
        answer.add("Alexander Isak");
        answer.add("Zlatan Ibrahimovic");
        return answer;
    }

    public ArrayList<String>historyAnswers1(){
        ArrayList<String> answer = new ArrayList<>();
        answer.add("1912");
        answer.add("1913");
        answer.add("1914");
        answer.add("1915");
        return answer;
    }
    public ArrayList<String>historyAnswers2(){
        ArrayList<String> answer = new ArrayList<>();
        answer.add("Sköt sig själv");
        answer.add("Blev skjuten");
        answer.add("Blev förgiftad");
        answer.add("Hängde sig");
        return answer;
    }
    public ArrayList<String>movieAnswers1(){
        ArrayList<String>answer = new ArrayList<>();
        answer.add("The Revenant");
        answer.add("The Wolf Of Wall Street");
        answer.add("Shutter Island");
        answer.add("The Great Gatsby");
        return answer;
    }
    public ArrayList<String>movieAnswers2(){
        ArrayList<String>answer = new ArrayList<>();
        answer.add("Den nakna pistolen");
        answer.add("Scarface");
        answer.add("Stuart Little");
        answer.add("The Big Lebowski");
        return answer;
    }
    public ArrayList<String>bodyAnswers1(){
        ArrayList<String>answer = new ArrayList<>();
        answer.add("Ryggraden");
        answer.add("Lårbenet");
        answer.add("Skenbenet");
        answer.add("Armbågsbenet");
        return answer;
    }
    public ArrayList<String>bodyAnswers2(){
        ArrayList<String>answer = new ArrayList<>();
        answer.add("Konsekvenstänk");
        answer.add("Logiskt tänkande");
        answer.add("Balans och lokalsinne");
        answer.add("Känslor");
        return answer;
    }
    public ArrayList<String>javaAnswers1(){
        ArrayList<String>answer = new ArrayList<>();
        answer.add("§");
        answer.add("%");
        answer.add("½");
        answer.add("?");
        return answer;
    }
    public ArrayList<String>javaAnswers2(){
        ArrayList<String>answer = new ArrayList<>();
        answer.add("//");
        answer.add("##");
        answer.add("||");
        answer.add("**");
        return answer;
    }
    public void shuffleList(ArrayList list){
        Collections.shuffle(list);
    }




}
