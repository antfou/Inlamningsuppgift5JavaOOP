package MultipleChoiceQuestion.Client;

import MultipleChoiceQuestion.ClassesAndLogic.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class PlayerClient extends JFrame implements ActionListener {
    String userName;
    private JFrame frame = new JFrame("QUIZ");
    JPanel boardPanel;
    private JLabel messageLabel = new JLabel("");
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JButton button4 = new JButton();

    private static int PORT = 9123;
    private Socket socket;
    private ObjectInputStream inputHandler;
    private ObjectOutputStream outputHandler;
    Question question;
    public PlayerClient(String serverAddress, Question question){
        this.question = question;
        try{
            socket = new Socket(serverAddress, PORT);
            outputHandler = new ObjectOutputStream(socket.getOutputStream());
            inputHandler = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        frame.getContentPane().add(messageLabel,"South");

        //TODO label ska slumpa fram fråga från användarens valda kategori
        //Bara för test nu.
        setLabelText(question.getQuestionText());
        button1.setText(question.getAnswerList().get(0).getAnswerText());
        button2.setText(question.getAnswerList().get(1).getAnswerText());
        button3.setText(question.getAnswerList().get(2).getAnswerText());
        button4.setText(question.getAnswerList().get(3).getAnswerText());
        boardPanel = new JPanel();
        JPanel test = new JPanel();
        test.setLayout(new GridLayout(0,1));
        boardPanel.setLayout(new GridLayout(2,2,2,2));
        for(int i = 1; i <= 4; i++){
            final int j = i;
            switch (i){
                case 1: boardPanel.add(button1);
                    button1.addActionListener( this);
                case 2: boardPanel.add(button2);
                    button2.addActionListener( this);
                case 3: boardPanel.add(button3);
                    button3.addActionListener( this);
                case 4: boardPanel.add(button4);
                    button4.addActionListener( this);
            }
            test.add(messageLabel);
            test.add(boardPanel);
            frame.getContentPane().add(test,"Center");
        }
    }

    public void play() throws IOException{
        Object serverResponse;
        try{
            /*serverResponse = inputHandler.readObject();

            if (serverResponse instanceof String stringResponse){
                System.out.println("client reading "+stringResponse);
                if(stringResponse.startsWith("WELCOME")) {
                    userName = stringResponse.substring(8);
                    frame.setTitle("Spelare: " + userName);
                }
            }*/
            while(true) {
                serverResponse = inputHandler.readObject();
                if (serverResponse instanceof String stringResponse) {
                    System.out.println("client reading "+stringResponse);
                    if(stringResponse.startsWith("WELCOME")) {
                        userName = stringResponse.substring(8);
                        frame.setTitle("Spelare: " + userName);
                    }
                    else if (stringResponse.startsWith("KORREKT")) {
                        setLabelText("Rätt svar! Bra jobbat");
                    } else if (stringResponse.startsWith("INKORREKT")) {
                        setLabelText("Fel svar!");
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
                    }
                }//TODO: Lista ut vad du vill göra med detta.
                //outputHandler.writeObject("AVSLUT");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private boolean rematch(){
        int userResponse = JOptionPane.showConfirmDialog(frame,
                "Vill du spela en till?",
                "Quiz Kampen",JOptionPane.YES_NO_OPTION);
        frame.dispose();
        return userResponse == JOptionPane.YES_OPTION;
    }
    public void setLabelText(String string){
        messageLabel.setText(string);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        if(e.getSource() == button1){
                outputHandler.writeObject(question.getAnswerList().get(0));
        }else if(e.getSource() == button2){
            outputHandler.writeObject(question.getAnswerList().get(1));
        }else if(e.getSource() == button3){
            outputHandler.writeObject(question.getAnswerList().get(2));
        }else if(e.getSource() == button4){
            outputHandler.writeObject(question.getAnswerList().get(3));
        } } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) throws IOException {
        while (true){
            System.out.println("Client är igång");
            Question question = new Question("Vad är Sveriges huvudstad?","Göteborg","Malmö","" +
                    "Sälen", "Stockholm");
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            PlayerClient playerClient = new PlayerClient(serverAddress, question);
            playerClient.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            playerClient.frame.setSize(400, 200);
            playerClient.frame.setVisible(true);
            playerClient.play();
            if(!playerClient.rematch()){
                break;
            }
        }
    }
}