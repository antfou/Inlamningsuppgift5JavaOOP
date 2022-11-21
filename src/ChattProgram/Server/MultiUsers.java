package ChattProgram.Server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MultiUsers {
    ArrayList<PrintWriter> users = new ArrayList<>();

    void addUser(PrintWriter user){
        users.add(user);

    }
    List<PrintWriter> getUsers(){
        return users;
    }
}
