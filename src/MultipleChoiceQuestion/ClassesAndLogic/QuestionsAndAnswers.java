package MultipleChoiceQuestion.ClassesAndLogic;
//Feature Branch

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionsAndAnswers {

    //TODO Gör att det är questionLists och gör en getcurrentquestion som använder getcurrentcategory för att hidda den
    Question question1;
    Question question2;
    String currentCategory;

        public ArrayList<JButton> listOfCategories () {
            ArrayList<JButton> list = new ArrayList<>();
            list.add(new JButton("Sport"));
            list.add(new JButton("Historia"));
            list.add(new JButton("Film"));
            list.add(new JButton("Människokroppen"));
            list.add(new JButton("Java"));
            Collections.shuffle(list);
            return list;
        }
        public ArrayList<Question> sportQuestions () {
            ArrayList<Question> sport = new ArrayList<>();
            sport.add(question1 = new Question("Vilket lag har färgerna svart och gul i sin logga?", sportAnswers1().get(0),
                    sportAnswers1().get(1), sportAnswers1().get(2), sportAnswers1().get(3)));
            sport.add(question2 = new Question("Vem fick guldbollen 2021?", sportAnswers1().get(0), sportAnswers1().get(1),
                    sportAnswers1().get(2), sportAnswers1().get(3)));
            shuffleList(sport);
            return sport;
        }
        public ArrayList<String> sportAnswers1 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("DIF");
            answer.add("MFF");
            answer.add("HIF");
            answer.add("AIK");
            return answer;
        }
        public ArrayList<String> sportAnswers2 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("Dejan Kulusevski");
            answer.add("Alexander Isak");
            answer.add("Zlatan Ibrahimovic");
            answer.add("Emil Forsberg");
            return answer;
        }
        public ArrayList<Question> historyQuestions () {
            ArrayList<Question> history = new ArrayList<>();
            history.add(question1 = new Question("När startades första världskriget?", historyAnswers1().get(0)
                    , historyAnswers1().get(1), historyAnswers1().get(2), historyAnswers1().get(3)));
            history.add(question2 = new Question("Hur dog Hitler?", historyAnswers2().get(0), historyAnswers2().get(1)
                    , historyAnswers2().get(2), historyAnswers2().get(3)));
            shuffleList(history);
            return history;
        }

        public ArrayList<String> historyAnswers1 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("1912");
            answer.add("1913");
            answer.add("1915");
            answer.add("1914");
            return answer;
        }
        public ArrayList<String> historyAnswers2 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("Sköt sig själv");
            answer.add("Blev skjuten");
            answer.add("Hängde sig");
            answer.add("Förgiftade sig själv");
            return answer;
        }
        public ArrayList<Question> movieQuestions () {
            ArrayList<Question> math = new ArrayList<>();
            math.add(question1 = new Question("Vilken film vann Leonardo Dicaprio en Oscar för?", movieAnswers1().get(0)
                    , movieAnswers1().get(1), movieAnswers1().get(2), movieAnswers1().get(3)));
            math.add(question2 = new Question("Från vilken film kommer citatet  \" Say hello to my little friend \"  ",
                    movieAnswers2().get(0), movieAnswers2().get(1), movieAnswers2().get(2), movieAnswers2().get(3)));
            shuffleList(math);
            return math;
        }

        public ArrayList<String> movieAnswers1 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("The Wolf Of Wall Street");
            answer.add("Shutter Island");
            answer.add("The Great Gatsby");
            answer.add("Revenant");
            return answer;
        }
        public ArrayList<String> movieAnswers2 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("Den nakna pistolen");
            answer.add("Stuart Little");
            answer.add("The Big Lebowski");
            answer.add("Scarface");
            return answer;
        }
        public ArrayList<Question> humanBodyQuestions () {
            ArrayList<Question> human = new ArrayList<>();
            human.add(question1 = new Question("Vilket ben är människokroppens längsta ben?", bodyAnswers1().get(0)
                    , bodyAnswers1().get(1), bodyAnswers1().get(2), bodyAnswers1().get(3)));
            human.add(question2 = new Question("Vilken funktion fyller amygdala", bodyAnswers2().get(0)
                    , bodyAnswers2().get(1), bodyAnswers2().get(2), bodyAnswers2().get(3)));
            shuffleList(human);
            return human;
        }

        public ArrayList<String> bodyAnswers1 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("Ryggraden");
            answer.add("Skenbenet");
            answer.add("Armbågsbenet");
            answer.add("Lårbenet");
            return answer;
        }
        public ArrayList<String> bodyAnswers2 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("Konsekvenstänk");
            answer.add("Logiskt tänkande");
            answer.add("Balans och lokalsinne");
            answer.add("Känslor");
            return answer;
        }
        public ArrayList<Question> javaQuestions () {
            ArrayList<Question> java = new ArrayList<>();
            java.add(question1 = new Question("Vilket tecken anger modulus", javaAnswers1().get(0),
                    javaAnswers1().get(1), javaAnswers1().get(2), javaAnswers1().get(3)));
            java.add(question2 = new Question("Hur adderar du kommentarer till din java-kod?", javaAnswers2().get(0),
                    javaAnswers2().get(1), javaAnswers2().get(2), javaAnswers2().get(3)));
            shuffleList(java);
            return java;
        }
        public ArrayList<String> javaAnswers1 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("§");
            answer.add("½");
            answer.add("?");
            answer.add("%");
            return answer;
        }
        public ArrayList<String> javaAnswers2 () {
            ArrayList<String> answer = new ArrayList<>();
            answer.add("##");
            answer.add("||");
            answer.add("**");
            answer.add("//");
            return answer;
        }
        public void shuffleList (ArrayList list){
            Collections.shuffle(list);
        }
        public Question getQuestion (String currentCategory){
            this.currentCategory = currentCategory;
            if (currentCategory.equals("Sport")) {
                question1 = sportQuestions().get(0);
            } else if (currentCategory.equals("Historia")) {
                question1 = historyQuestions().get(0);
            } else if (currentCategory.equals("Film")) {
                question1 = movieQuestions().get(0);
            } else if (currentCategory.equals("Människokroppen")) {
                question1 = humanBodyQuestions().get(0);
            } else {
                question1 = javaQuestions().get(0);
            }
            return question1;
        }
    }

