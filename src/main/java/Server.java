import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        int port = 8081;

        final String pleaseWriteYourName = "Hello! Please, write your name : ";
        final String areYouChild = "Are you child? (yes/no)";

        String welcomeMessage;

        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {

            System.out.println("New connection accepted : " + clientSocket.getInetAddress() + ": " + clientSocket.getPort());

            out.println(pleaseWriteYourName);

            System.out.print(pleaseWriteYourName + " ");

            final String clientName = in.readLine();

            System.out.println("'" + clientName + "'");

            out.println(areYouChild);

            System.out.print(areYouChild + " : ");

            final String isChildAnswer = in.readLine();

            System.out.println("'" + isChildAnswer + "'");

            if (isChildAnswer.equals("yes")) {
                welcomeMessage = String.format("Welcome to the kids area, %s Let's play!", clientName);
            } else {
                welcomeMessage = String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", clientName);
            }

            out.println(welcomeMessage);
            System.out.println(welcomeMessage);

            String nameAndPortAnswer = String.format("%s, your port is %d", clientName, clientSocket.getPort());

            out.println(nameAndPortAnswer);
            System.out.println(nameAndPortAnswer);
        }
    }
}
