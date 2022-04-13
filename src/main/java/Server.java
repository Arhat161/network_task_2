import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int port = 8081;

        String clientMessage = "";
        String clientName = "";
        String isChildAnswer = "";

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server ready ");
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("New connection accepted : " + clientSocket.getInetAddress() + ": " + clientSocket.getPort());

            out.println("Hello! Please, write your name in format #username : ");

            while (!clientMessage.equals("exit")) {

                clientMessage = in.readLine();
                System.out.println("client: " + clientMessage);

                if (clientName.equals("")) {
                    if (clientMessage.contains("#")) {
                        clientName = clientMessage;
                        out.println("Hello, " + clientName + "!");
                    } else {
                        out.println("Write your name in format #username : ");
                    }
                } else if (isChildAnswer.equals("")) { // no isChildAnswer
                    if (clientMessage.toLowerCase().contains("yes")) {
                        isChildAnswer = "yes";
                        out.println("Welcome to the kids area, " + clientName + "! Let's play!");
                    } else if (clientMessage.toLowerCase().contains("no")) {
                        isChildAnswer = "no";
                        out.println("Welcome to the adult zone, " + clientName + "! Have a good rest, or a good working day!");
                    } else {
                        out.println("Are you child? (yes/no)");
                    }
                } else {
                    out.println("Write your message : ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
