package Program.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame implements ActionListener {
    int port=4444;
    InetAddress inetAddress = InetAddress.getLocalHost();
    String fromServer;
    JTextArea area = new JTextArea(20,30);
    JTextField textField = new JTextField(30);
    JPanel panel = new JPanel();
    String name = "Ange användarnamn";
    String userName;
    PrintWriter printWriter;

    public Client() throws UnknownHostException {
        userName = JOptionPane.showInputDialog(name);
        panel.setLayout(new BorderLayout());
        panel.add(area,BorderLayout.NORTH);
        panel.add(textField,BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
        pack();
        area.setEditable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textField.addActionListener(this);

        try{Socket socket = new Socket(inetAddress,port);
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((fromServer=bufferedReader.readLine())!=null){
                area.append("\n"+ fromServer);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) throws UnknownHostException {
        new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==textField){
           printWriter.println ( userName + ": "+ textField.getText());
            textField.setText("");
        }
    }
}
