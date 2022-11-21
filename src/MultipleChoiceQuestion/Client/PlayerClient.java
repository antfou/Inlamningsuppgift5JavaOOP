package MultipleChoiceQuestion.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerClient extends JFrame implements ActionListener {
    String userName;
    //TODO Lägg in faktiska svar istället för message.
    private String message1 = "1";
    private String message2 =  "2";
    private String message3 = "3";
    private String message4 = "4";

    private JFrame frame = new JFrame("QUIZ");
    JPanel boardPanel;
    private JLabel messageLabel = new JLabel("");
    private JButton button1 = new JButton(message1);
    private JButton button2 = new JButton(message2);
    private JButton button3 = new JButton(message3);
    private JButton button4 = new JButton(message4);

    private static int PORT = 9123;
    private Socket socket;
    private BufferedReader inputHandler;
    private PrintWriter outputHandler;

    public PlayerClient(String serverAddress){
        try{
            socket = new Socket(serverAddress, PORT);
            inputHandler = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            outputHandler = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        frame.getContentPane().add(messageLabel,"South");
        setLabelText("FRÅGA");

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

    public void play(){
        String serverResponse;
        try{
            serverResponse = inputHandler.readLine();
            if(serverResponse.startsWith("WELCOME")) {
                userName = serverResponse.substring(8);
                frame.setTitle("Spelare: " + userName);
            }
            while(true){
                serverResponse = inputHandler.readLine();
                if (serverResponse.startsWith("KORREKT")){
                    setLabelText("Rätt svar! Bra jobbat");
                }else if(serverResponse.startsWith("INKORREKT")){
                    setLabelText("Fel svar!");
                }else if(serverResponse.startsWith("VINST")){
                    setLabelText("GRATTIS! DU VANN");
                    break;
                }else if(serverResponse.startsWith("FÖRLUST")){
                    setLabelText("Du förlorade.");
                    break;
                }else if(serverResponse.startsWith("OAVGJORT")){
                    setLabelText("Oavgjort");
                    break;
                }else if(serverResponse.startsWith("START")){
                    JOptionPane.showMessageDialog(null,
                            "Hittat motståndare, snart börjar matchen.");
                }
            }
            outputHandler.println("AVSLUT");
        } catch (IOException e) {
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
        if(e.getSource() == button1){
            outputHandler.println("BUTTON 1");
        }else if(e.getSource() == button2){
            outputHandler.println("BUTTON 2");
        }else if(e.getSource() == button3){
            outputHandler.println("BUTTON 3");
        }else if(e.getSource() == button4){
            outputHandler.println("BUTTON 4");
        }
    }

    public static void main(String[] args) {
        while (true){
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            PlayerClient playerClient = new PlayerClient(serverAddress);
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