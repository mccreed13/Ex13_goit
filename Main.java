import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Exercise1.sendGET();
        Exercise1.sendPOST("user.json");
        Exercise1.sendPUT(10);
        Exercise1.sendDEL(10);
        Exercise1.getUserById(1);
        Exercise1.getUserByUsername("Bret");

        /*Exercise2*/
        GetUserLastPostCommentsService.getUserLastPostComments("Bret");

        /*Exercise3*/
        Exercise3.allTodos(3);
    }
}

