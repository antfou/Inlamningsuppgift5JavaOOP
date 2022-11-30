package MultipleChoiceQuestion.Client;
//Feature Branch

import MultipleChoiceQuestion.ClassesAndLogic.Answer;
import MultipleChoiceQuestion.ClassesAndLogic.Database;
import MultipleChoiceQuestion.ClassesAndLogic.Question;
import MultipleChoiceQuestion.ClassesAndLogic.QuestionsAndAnswers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class PlayerClient extends JFrame implements ActionListener {
    int counter = 0;
    String userName;
    ArrayList<String> playerListAtClient;
    boolean hasClicked = false;
    String currentCategory;
    private JButton jcomp1 = new JButton ("Log In");
    private JButton jcomp3 = new JButton ("Play as Guest");
    private JButton jcomp2 = new JButton ("New User");
    private JButton newGame = new JButton("NYTT SPEL");
    private JButton giveUp = new JButton("Ge upp");
    private JFrame frame = new JFrame("QUIZ");
    JPanel boardPanel;
    JPanel mainPanel;
    JPanel buttonPanel;
    private JLabel messageLabel = new JLabel("");
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JButton button4 = new JButton();
    private JButton[] buttons;
    private JButton findPlayerButton = new JButton("hitta spelare");
    private JButton randomPlayerButton = new JButton("slumpa spelare");
    private static int PORT = 9123;
    private Socket socket;
    private ObjectInputStream inputHandler;
    private ObjectOutputStream outputHandler;
    private ArrayList<JButton> categories = new ArrayList<>();
    private ArrayList<JButton> answerButtonList = new ArrayList<>();
    private int frameSize = 100;
    QuestionsAndAnswers questionsAndAnswers = new QuestionsAndAnswers();
    Color background = new Color(51,154,255);
    Color purple = new Color(178,102,255);
    Color button = new Color(0,204,0);
    ImageIcon img = new ImageIcon("src/MultipleChoiceQuestion/ClassesAndLogic/template.png");
    JLabel jLabel = new JLabel();

    Question question;
    public PlayerClient(String serverAddress) {
        try {
            socket = new Socket(serverAddress, PORT);
            outputHandler = new ObjectOutputStream(socket.getOutputStream());
            inputHandler = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void StartMenu(){

        frame.getContentPane().add(messageLabel,BorderLayout.NORTH);

        //TODO label ska slumpa fram fråga från användarens valda kategori
        //Bara för test nu.
        setLabelText("Quizkampen");

        boardPanel = new JPanel();
        mainPanel = new JPanel();
        mainPanel.add(newGame);
        /*mainPanel.setBackground(background);
        newGame.setBackground(button);
        newGame.setForeground(Color.white);
        mainPanel.setLayout(new GridLayout(3,1));
        */
        mainPanel.add(jLabel);
        jLabel.setIcon(img);
        frame.setPreferredSize (new Dimension (frameSize*4, frameSize*6));
        mainPanel.setLayout(null);
        jcomp1.setBounds (frameSize*4/3, frameSize*2, 155, 55);
        jcomp2.setBounds (frameSize*4/3, frameSize*6/2, 155, 55);
        jcomp3.setBounds (frameSize*4/3, frameSize*5, 155, 55);
        mainPanel.add(jcomp1);
        mainPanel.add(jcomp2);
        mainPanel.add(jcomp3);
        frame.add(mainPanel);
        frame.setResizable(false);
        //mainPanel.add(messageLabel);
        newGame.addActionListener(this);
        frame.pack();
        paint(getGraphics());

    }

    public void gameState() {
        if (counter < 2) {
            removeAll();
            boardPanel.setBackground(background);
            hasClicked = false;
            System.out.println("hämtar frågor, counter "+counter);
            if(counter>0) {
                question = questionsAndAnswers.getQuestion(currentCategory, counter, false);
            }else{
                question = questionsAndAnswers.getQuestion(currentCategory, counter, true);
            }
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(0, 5));
            buttonPanel.add(giveUp);
            giveUp.addActionListener(this);
            giveUp.setBackground(Color.white);
            buttonPanel.setBackground(background);
            setLabelText(question.getQuestionText());
            System.out.println("Hämtat från databas: " + question.getQuestionText());
            boardPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(0, 1));
            boardPanel.setLayout(new GridLayout(2, 2, 2, 2));
            button1.setText(question.getAnswerList().get(0).getAnswerText());
            System.out.println("Hämtat från databas: " + question.getAnswerList().get(0).getAnswerText());
            button2.setText(question.getAnswerList().get(1).getAnswerText());
            System.out.println("Hämtat från databas: " + question.getAnswerList().get(1).getAnswerText());
            button3.setText(question.getAnswerList().get(2).getAnswerText());
            System.out.println("Hämtat från databas: " + question.getAnswerList().get(2).getAnswerText());
            button4.setText(question.getAnswerList().get(3).getAnswerText());
            System.out.println("Hämtat från databas: " + question.getAnswerList().get(3).getAnswerText());
            for (int i = 1; i <= 4; i++) {
                switch (i) {
                    case 1 -> {
                        boardPanel.add(button1);
                        button1.setBackground(Color.ORANGE);
                        button1.addActionListener(this);
                        answerButtonList.add(button1);
                    }
                    case 2 -> {
                        boardPanel.add(button2);
                        button2.setBackground(Color.ORANGE);
                        button2.addActionListener(this);
                        answerButtonList.add(button2);
                    }
                    case 3 -> {
                        boardPanel.add(button3);
                        button3.setBackground(Color.ORANGE);
                        button3.addActionListener(this);
                        answerButtonList.add(button3);
                    }
                    case 4 -> {
                        boardPanel.add(button4);
                        button4.setBackground(Color.ORANGE);
                        button4.addActionListener(this);
                        answerButtonList.add(button4);
                    }
                }
                mainPanel.add(messageLabel);
                mainPanel.add(boardPanel);
                frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
                frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
                pack();
                //TODO paint(getGraphics()); TESTA DENNA!!!!!
            }
        }else{
            System.out.println("Slut på frågor");
        }
    }

    void chooseCategory(){
        mainPanel.remove(newGame);
        mainPanel.remove(findPlayerButton);
        mainPanel.remove(randomPlayerButton);
        mainPanel.repaint();
        setLabelText("Välj kategori");
        categories = questionsAndAnswers.listOfCategories();
        mainPanel.setLayout(new GridLayout(4,1));
        mainPanel.add(messageLabel);
        for(JButton button: categories){
            button.addActionListener(this);
        }
        categories.get(0).setBackground(Color.ORANGE); categories.get(0).setForeground(Color.white);
        categories.get(1).setBackground(purple); categories.get(1).setForeground(Color.white);
        categories.get(2).setBackground(Color.GREEN.darker()); categories.get(2).setForeground(Color.white);
        mainPanel.add(categories.get(0));
        mainPanel.add(categories.get(1));
        mainPanel.add(categories.get(2));
    }

    void chooseRandomPlayerOrFindPlayer(){
        mainPanel.remove(newGame);
        mainPanel.repaint();
        setLabelText("");
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(findPlayerButton);
        mainPanel.add(randomPlayerButton);
        findPlayerButton.addActionListener(this);
        randomPlayerButton.addActionListener(this);

    }

    void findPlayerButtonPressed(){
        playerListAtClient.forEach(System.out::println);
        //buttons = new JButton[db.getListOfPlayers().size()];
        mainPanel.remove(newGame);
        mainPanel.repaint();
        setLabelText("");
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            mainPanel.add(buttons[i]);
        }

    }

        public void play () throws IOException, InterruptedException {
            Object serverResponse;
            try {
                while (true) {
                    serverResponse = inputHandler.readObject();
                    if (serverResponse instanceof String stringResponse) {
                        System.out.println("client reading " + stringResponse);
                        if (stringResponse.startsWith("WELCOME")) {
                            userName = stringResponse.substring(8);
                            frame.setTitle("Spelare: " + userName);
                            //outputHandler.writeObject("");
                            //High risk cast


                        } else if (stringResponse.startsWith("KORREKT")) {
                            setLabelText("Rätt svar! Bra jobbat");
                            hasClicked = true;
                            colorButtons();
                            counter = counter +1;
                            System.out.println("Counter är " + counter);
                            Thread.sleep(3000);
                            gameState();
                        } else if (stringResponse.startsWith("INKORREKT")) {
                            setLabelText("Fel svar!");
                            hasClicked = true;
                            colorButtons();
                            counter = counter +1;
                            System.out.println("Counter är " + counter);
                            Thread.sleep(3000);
                            gameState();
                        } else if (stringResponse.startsWith("VINST")) {
                            setLabelText("GRATTIS! DU VANN");
                            break;
                        } else if (stringResponse.startsWith("FÖRLUST")) {
                            setLabelText("Du förlorade.");
                            break;
                        } else if (stringResponse.startsWith("OAVGJORT")) {
                            setLabelText("Oavgjort");
                            break;
                        } else if (stringResponse.startsWith("START")) {
                            JOptionPane.showMessageDialog(null,
                                    "Hittat motståndare, snart börjar matchen.");
                            StartMenu();
                        } else if(stringResponse.startsWith("CATEGORY")){
                            chooseCategory();
                            outputHandler.writeObject("category recieved");
                            outputHandler.reset();
                        }else if(stringResponse.contains("VÄNTA")){
                            System.out.println("Din motståndare väljer kategori.");
                            //Thread.sleep(10000);
                            outputHandler.writeObject("vänta recieved");
                            outputHandler.reset();
                        }
                        else if(stringResponse.startsWith("KÖR")){
                            mainPanel.remove(categories.get(0));mainPanel.remove(categories.get(1));mainPanel.remove(categories.get(2));
                            gameState();
                            if(stringResponse.contains("SPORT")){
                                currentCategory = "Sport";
                            }else if(stringResponse.contains("HISTORIA")){
                                currentCategory = "Historia";
                            }else if(stringResponse.contains("FILM")){
                                currentCategory = "Film";
                            }else if(stringResponse.contains("JAVA")){
                                currentCategory = "Java";
                            }else if(stringResponse.contains("MÄNNISKOKROPPEN")){
                                currentCategory = "Människokroppen";
                            }
                        }
                    }  if (serverResponse instanceof ArrayList<?> playerList) {
                        playerListAtClient = (ArrayList<String>) playerList;
                    }//TODO: Lista ut vad du vill göra med detta.
                    //outputHandler.writeObject("AVSLUT");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        private boolean rematch () {
            int userResponse = JOptionPane.showConfirmDialog(frame,
                    "Vill du spela en till?",
                    "Quizkampen", JOptionPane.YES_NO_OPTION);
            frame.dispose();
            return userResponse == JOptionPane.YES_OPTION;
        }
        public void setLabelText (String string){
            messageLabel.setText(string);
            messageLabel.setForeground(Color.white);
        }
        @Override
        public void actionPerformed (ActionEvent e){
            try {
                if (hasClicked == false) {
                    if (e.getSource() == button1) {
                        outputHandler.writeObject(question.getAnswerList().get(0));
                    } else if (e.getSource() == button2) {
                        outputHandler.writeObject(question.getAnswerList().get(1));
                    } else if (e.getSource() == button3) {
                        outputHandler.writeObject(question.getAnswerList().get(2));
                    } else if (e.getSource() == button4) {
                        outputHandler.writeObject(question.getAnswerList().get(3));
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (e.getSource() == newGame) {
                chooseRandomPlayerOrFindPlayer();
                //chooseCategory();
            }
            if (e.getSource() == randomPlayerButton) {
                try {
                    outputHandler.writeObject("NEW_GAME");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //chooseCategory();
            }
            if (e.getSource() == findPlayerButton) {
                findPlayerButtonPressed();
            }
            for (JButton button : categories) {
                if (e.getSource() == button) {
                    if (button.getText().equals("Sport")) {
                        currentCategory = "Sport";
                        //mainPanel.remove(categories.get(0));mainPanel.remove(categories.get(1));mainPanel.remove(categories.get(2));
                       // gameState();
                    } else if (button.getText().equals("Historia")) {
                        currentCategory = "Historia";
                      //  mainPanel.remove(categories.get(0));mainPanel.remove(categories.get(1));mainPanel.remove(categories.get(2));
                      //  gameState();
                    } else if (button.getText().equals("Film")) {
                        currentCategory = "Film";
                      //  mainPanel.remove(categories.get(0));mainPanel.remove(categories.get(1));mainPanel.remove(categories.get(2));
                      //  gameState();
                    } else if (button.getText().equals("Människokroppen")) {
                        currentCategory = "Människokroppen";
                      //  mainPanel.remove(categories.get(0));mainPanel.remove(categories.get(1));mainPanel.remove(categories.get(2));
                      //  gameState();
                    } else {
                        currentCategory = "Java";
                       // mainPanel.remove(categories.get(0));mainPanel.remove(categories.get(1));mainPanel.remove(categories.get(2));
                      //  gameState();
                    }
                    try {
                        outputHandler.writeObject(currentCategory);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            if (e.getSource() == giveUp) {
                int reply = JOptionPane.showConfirmDialog(null, "Vill du ge upp?", userName, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
                    try {
                        socket.close(); //?
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //TODO meddela motståndare att spelare avslutat.
                } else {
                    return;
                }
            }
        }
        public String getCurrentCategory () {
            return currentCategory;
        }
        public void colorButtons () {
            for (JButton button : answerButtonList) {
                for (Answer answer : question.getAnswerList()) {
                    if (button.getText().equals(answer.getAnswerText()) && answer.checkIfCorrect()) {
                        button.setBackground(Color.GREEN);
                    } else if (button.getText().equals(answer.getAnswerText())) {
                        button.setBackground(Color.RED);
                    }
                }
            }
        }
    public static void main (String[]args) throws IOException, InterruptedException {
        System.out.println("Client är igång");
        String serverAddress = "LocalHost";
        PlayerClient playerClient = new PlayerClient(serverAddress);
        playerClient.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerClient.frame.setSize(400, 200);
        playerClient.frame.setVisible(true);
        playerClient.play();

    }
}

